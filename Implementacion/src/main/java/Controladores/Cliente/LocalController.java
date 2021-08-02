package Controladores.Cliente;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.Local;
import Pedidos.Carrito;
import Pedidos.Cupones.CuponDescuento;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Plato;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Utils.Exceptions.LocalInexistenteException;
import Utils.Exceptions.PedidoIncompletoException;
import Utils.Exceptions.PlatoInexistenteException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static Controladores.Utils.Modelos.parseModel;

public class LocalController implements Transaccional {

    public LocalController(RepoLocales repoLocales, Autenticador<Cliente> autenticador){
        this.repoLocales = repoLocales;
        this.autenticadorClientes = autenticador;
    }

    private ErrorHandler errorHandler = new ErrorHandler();
    private RepoLocales repoLocales;
    private Autenticador<Cliente> autenticadorClientes;

    public ModelAndView getLocal(Request req, Response res){
        Optional<Local> local = findLocal(req.params("id"), req, res);

        if(!local.isPresent()){
            return null;
        }

        Cliente cliente = autenticadorClientes.getUsuario(req);
        Carrito carrito = cliente.getCarrito(local.get());

        Modelo modelo = parseModel(local.get())
            .con(parseModel(carrito))
            .con("categoria", cliente.getCategoria().getNombre())
            .con("direcciones", cliente.getDireccionesConocidas())
            .con("descuentos", cliente.getCupones())
            .con("error", errorHandler.getMensaje(req));

        return new ModelAndView(modelo, Templates.LOCAL_INDIVIDUAL);
    }

    public ModelAndView getPlato(Request req, Response res) {
        Long idLocal = null;
        try{
            idLocal = parseIdFromParams("id", req);
            Local local = repoLocales.getLocal(idLocal);
            Plato plato = local.getPlato(parseIdFromParams("idPlato", req));
            return new ModelAndView(parseModel(plato).con("idLocal", idLocal), Templates.PLATO);
        } catch (PlatoInexistenteException | NumberFormatException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.LOCAL(idLocal));
            return null;
        }
    }

    public ModelAndView agregarItem(Request request, Response response) {
        withTransaction(()->{
            findCarrito(request.params("idLocal"), request, response).ifPresent(
                carrito -> {
                    Local local = carrito.getLocal();
                    Plato plato = local.getPlato(Long.parseLong(request.queryParams("idPlato")));
                    int cantidad = Integer.parseInt(request.queryParams("cantidad"));
                    String aclaraciones = request.queryParams("aclaraciones");
                    carrito.conItem(new Item(plato, cantidad, aclaraciones));
                    response.redirect(URIs.LOCAL(local.getId()));
                }
            );
        });

        return null;
    }

    public ModelAndView eliminarItem(Request request, Response response) {

        findCarrito(request.params("idLocal"), request, response).ifPresent(
            carrito -> {
                try {
                    int numero = Integer.parseInt(request.params("item"));
                    withTransaction(()->carrito.sacarItem(numero));
                    response.status(HttpURLConnection.HTTP_OK);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    errorHandler.setMensaje(request, "Se produjo un error al intentar eliminar item");
                    response.status(HttpURLConnection.HTTP_BAD_REQUEST);
                } finally {
                    response.redirect(URIs.LOCAL(carrito.getLocal().getId()));
                }
            }
        );

        return null;
    }

    public ModelAndView finalizarPedido(Request request, Response response) {
        String idLocal = request.queryParams("idLocal");

        findLocal(idLocal, request, response).ifPresent(local -> {
            try {
                Cliente cliente = autenticadorClientes.getUsuario(request);
                Carrito carrito = cliente.getCarrito(local);
                CuponDescuento descuento = leerCupon(request);

                withTransaction(()-> {
                    Pedido pedido = carrito.conDireccion(leerDireccion(request))
                        .conCupon(descuento)
                        .build();

                    cliente.agregarPedido(pedido);

                    int numeroDePedido = cliente.getPedidosRealizados().size();
                    response.redirect(URIs.PEDIDO((long) numeroDePedido));
                    carrito.vaciar();
                });
            } catch (PedidoIncompletoException e){
                errorHandler.setMensaje(request, e.getMessage());
                response.status(HttpURLConnection.HTTP_BAD_REQUEST);
                Long id = Long.parseLong(idLocal);
                response.redirect(URIs.LOCAL(id));
            }
        });

        return null;
    }

//TODO: Auxiliares ************************************************


    private Optional<Local> findLocal(String idLocalString, Request req, Response res){
        Local local = null;

        try{
            long idLocal = Long.parseLong(idLocalString);
            local = repoLocales.getLocal(idLocal);
        } catch (NumberFormatException | LocalInexistenteException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.LOCALES);
        }

        return Optional.ofNullable(local);
    }

    private Optional<Carrito> findCarrito(String idLocal, Request req, Response res){
        return findLocal(idLocal, req, res).map(local->{
            Cliente cliente = autenticadorClientes.getUsuario(req);
            return cliente.getCarrito(local);
        });
    }

    private Long parseIdFromParams(String id, Request req){
        return Long.parseLong(req.params(id));
    }

    private String leerDireccion(Request request){
        String direccion = request.queryParams("direccion");
        Cliente cliente = autenticadorClientes.getUsuario(request);
        if(!cliente.getDireccionesConocidas().contains(direccion)){
            cliente.agregarDireccion(direccion);
        }

        return direccion;
    }

    private CuponDescuento leerCupon(Request request){
        return getAtributoDeLista(request
            , "descuento"
            , Cliente::getCupones
        );
    }

    private <T> T getAtributoDeLista(Request req, String atributo, Function<Cliente, List<T>> obtencion){
        try{
            Cliente cliente = autenticadorClientes.getUsuario(req);

            List<T> lista = obtencion.apply(cliente);

            T elem = lista.get(Integer.parseInt(req.queryParams(atributo)));
            lista.remove(elem);
            lista.add(0, elem);
            return elem;

        } catch (NumberFormatException |IndexOutOfBoundsException e){
            throw new PedidoIncompletoException(atributo);
        }
    }
}
