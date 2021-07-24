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

public class CarritoController {

    public CarritoController(RepoLocales repoLocales, Autenticador<Cliente> autenticador){
        this.repoLocales = repoLocales;
        this.autenticadorClientes = autenticador;
        this.repoPedidos = repoPedidos;
    }

    private RepoLocales repoLocales;
    private Autenticador<Cliente> autenticadorClientes;
    private RepoPedidos repoPedidos;
    private String ERROR_TOKEN = "error";

    public ModelAndView getLocal(Request req, Response res){
        try{
            Local local = repoLocales.getLocal(parseId("id", req));
            Cliente cliente = autenticadorClientes.getUsuario(req);
            Carrito carrito = cliente.getCarrito(local);

            String mensaje = req.session().attribute(ERROR_TOKEN);
            req.session().removeAttribute(ERROR_TOKEN);

            Modelo modelo = parseModel(local)
                .con(parseModel(carrito))
                .con("direcciones", cliente.getDireccionesConocidas())
                .con(ERROR_TOKEN, mensaje);

            return new ModelAndView(modelo, Templates.LOCAL_INDIVIDUAL);

        } catch (LocalInexistenteException | NumberFormatException e) {
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.LOCALES);
            return null;
        }
    }

    public ModelAndView getPlato(Request req, Response res) {
        Long idLocal = null;
        try{
            idLocal = parseId("id", req);
            Local local = repoLocales.getLocal(idLocal);
            Plato plato = local.getPlato(parseId("idPlato", req));
            return new ModelAndView(parseModel(plato).con("idLocal", idLocal), Templates.PLATO);
        } catch (PlatoInexistenteException | NumberFormatException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.LOCAL(idLocal));
            return null;
        }
    }

    //TODO: unifique dos paginas y esta parte me quedo rara ************************************************
    //Ver de reemplazar el findCarrito por findlocal

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

    public ModelAndView finalizarPedido(Request request, Response response) {
        String idLocal = request.queryParams("idLocal");

        findCarrito(idLocal, request, response).ifPresent(carrito -> {
            try {
                Pedido pedido = carrito
                    .conDireccion(getDireccion(request))
                    .build();
                autenticadorClientes.getUsuario(request).agregarPedido(pedido);
                response.redirect(URIs.PEDIDO(pedido.getId()));
            } catch (PedidoIncompletoException e){
                request.session().attribute(ERROR_TOKEN, e.getMessage());
                response.status(HttpURLConnection.HTTP_BAD_REQUEST);
                Long id = Long.parseLong(idLocal);
                response.redirect(URIs.LOCAL(id));
            }
        });

        return null;
    }

    private Optional<Carrito> findCarrito(String local, Request req, Response res){
        Carrito carrito = null;
        try{
            long idLocal = Long.parseLong(local);
            Cliente cliente = autenticadorClientes.getUsuario(req);
            carrito = cliente.getCarrito(repoLocales.getLocal(idLocal));

        } catch (NumberFormatException | LocalInexistenteException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.HOME);
        }

        return Optional.ofNullable(carrito);
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

    private Long parseId(String id, Request req){
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
