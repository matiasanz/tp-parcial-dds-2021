package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Local.Local;
import Pedidos.Carrito;
import Pedidos.Direccion;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Plato;
import Repositorios.RepoLocales;
import Repositorios.RepoPedidos;
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
import java.util.stream.Collectors;

public class LocalController {

    public LocalController(RepoLocales repoLocales, Autenticador<Cliente> autenticador){
        this.repoLocales = repoLocales;
        this.autenticadorClientes = autenticador;
    }

    private RepoLocales repoLocales;
    private Autenticador<Cliente> autenticadorClientes;
    private String ERROR_TOKEN = "error";

    public ModelAndView getLocal(Request req, Response res){
        Optional<Local> local = findLocal(req.params("id"), req, res);

        if(!local.isPresent()){
            return null;
        }

        Cliente cliente = autenticadorClientes.getUsuario(req);
        Carrito carrito = cliente.getCarrito(local.get());

        String mensaje = req.session().attribute(ERROR_TOKEN);
        req.session().removeAttribute(ERROR_TOKEN);

        Modelo modelo = parseModel(local.get())
            .con(parseModel(carrito))
            .con("direcciones", cliente.getDireccionesConocidas())
            .con(ERROR_TOKEN, mensaje);

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

        return null;
    }

    public ModelAndView eliminarItem(Request request, Response response) {

        findCarrito(request.params("idLocal"), request, response).ifPresent(
            carrito -> {
                try {
                    int numero = Integer.parseInt(request.params("item"));
                    carrito.sacarItem(numero);
                    response.status(HttpURLConnection.HTTP_OK);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    request.session().attribute(ERROR_TOKEN, "Se produjo un error al intentar eliminar item");
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
                Pedido pedido = carrito.conDireccion(getDireccion(request)).build();
                cliente.agregarPedido(pedido);
                int id = cliente.getPedidosRealizados().size() - 1;
                response.redirect(URIs.PEDIDO((long) id));
                carrito.vaciar();
            } catch (PedidoIncompletoException e){
                request.session().attribute(ERROR_TOKEN, e.getMessage());
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

    //TODO: Modelos ************************************************

    private Modelo parseModel(Local local){
        return new Modelo("nombre", local.getNombre())
            .con("idLocal", local.getId())
            .con("categorias", local.getCategorias())
            .con("Platos", local.getMenu().stream().map(this::parseModel).collect(Collectors.toList()))
            .con("Direccion", local.getDireccion().getCalle());
    }

    private Modelo parseModel(Plato plato){
        return new Modelo("nombre", plato.getNombre())
            .con("precio", plato.getPrecio())
            .con("idPlato", plato.getId());
    }

    private Long parseIdFromParams(String id, Request req){
        return Long.parseLong(req.params(id));
    }

    private Modelo parseModel(Carrito carrito){
        Direccion direccion = carrito.getDireccion();

        return new Modelo("local", carrito.getLocal().getNombre())
            .con("precio", carrito.getPrecio())
            .con("idLocal", carrito.getLocal().getId())
            .con("direccion", (direccion==null)? null : direccion.getCalle())
            .con("items", carrito.getItems().stream().map(this::parseModel).collect(Collectors.toList()));
    }

    Modelo parseModel(Item item){
        return new Modelo("plato", item.getPlato().getNombre())
            .con("aclaraciones", item.getAclaraciones())
            .con("cantidad", item.getCantidad());
    }

    private Direccion getDireccion(Request req){
        try{
            List<Direccion> direcciones = autenticadorClientes.getUsuario(req)
                .getDireccionesConocidas();
            Direccion direccion = direcciones.get(Integer.parseInt(req.queryParams("direccion")));
            direcciones.remove(direccion);
            direcciones.add(0, direccion);
            return direccion;
        } catch (NumberFormatException e){
            throw new PedidoIncompletoException("direccion");
        }
    }
}
