package Controladores.Cliente;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.Local;
import Pedidos.Carrito;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Plato;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Utils.Exceptions.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.Optional;
import java.util.function.BiConsumer;

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
            .con("suscripto", local.get().esSuscriptor(cliente))
            .con(parseModel(carrito))
            .con(parseModel(cliente))
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
        findCarrito(request.params("idLocal"), request, response)
            .ifPresent(
                carrito -> {
                    Local local = carrito.getLocal();
                    Plato plato = local.getPlato(Long.parseLong(request.queryParams("idPlato")));
                    int cantidad = Integer.parseInt(request.queryParams("cantidad"));
                    String aclaraciones = request.queryParams("aclaraciones");

                    withTransaction(()->carrito.conItem(new Item(plato, cantidad, aclaraciones)));

                    response.redirect(URIs.LOCAL(local.getId()));
                }
        );

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

                withTransaction(()-> {
                    Pedido pedido = carrito
                        .conDireccion(leerDireccion(request))
                        .build();
                    cliente.agregarPedido(pedido);
                    pedido.getLocal().agregarPedido(pedido);
                    int numeroDePedido = cliente.getPedidosRealizados().size();
                    response.redirect(URIs.PEDIDO(numeroDePedido));
                    cliente.devolverCarrito(carrito);
                });
            } catch (PedidoIncompletoException | DatosInvalidosException e){
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
        Cliente cliente = autenticadorClientes.getUsuario(req);
        Optional<Local> local = findLocal(idLocal, req, res);

        if(local.isPresent()){
            withTransaction(()-> {
                cliente.getCarrito(local.get());
            });

            return local.map(cliente::getCarrito);
        }

        return Optional.empty();
    }

    private Long parseIdFromParams(String id, Request req){
        return Long.parseLong(req.params(id));
    }

    private String leerDireccion(Request request){
        String direccion = request.queryParams("direccion");
        Cliente cliente = autenticadorClientes.getUsuario(request);

        if(direccion==null){
            throw new PedidoIncompletoException("direccion");
        }

        if(!cliente.getDireccionesConocidas().contains(direccion)){
            cliente.agregarDireccion(direccion);
        }

        return direccion;
    }

    public ModelAndView suscribirmeALocal(Request req, Response res){
        setSuscripcion(req, res, (cliente, local)->local.agregarSuscriptor(cliente));
        return null;
    }

    public ModelAndView desuscribirmeALocal(Request req, Response res){
        setSuscripcion(req, res, (cliente, local)->local.eliminarSuscriptor(cliente));
        return null;
    }

    private void setSuscripcion(Request req, Response res, BiConsumer<Cliente, Local> setteo){
        Cliente cliente = autenticadorClientes.getUsuario(req);
        try{
            long idLocal = Long.parseLong(getParam("idLocal", req));

            Local local = repoLocales.getLocal(idLocal);
            withTransaction(()->{
                setteo.accept(cliente, local);
            });
            res.redirect(URIs.LOCAL(idLocal));
            res.status(200);
        } catch (NumberFormatException | LocalInexistenteException | UsuarioYaSuscritoException e){
            res.redirect(URIs.LOCALES, HttpURLConnection.HTTP_BAD_REQUEST);
        }
    }

    private String getParam(String param, Request req){
        return Optional.ofNullable(req.params(param)).orElse(req.queryParams(param));
    }
}
