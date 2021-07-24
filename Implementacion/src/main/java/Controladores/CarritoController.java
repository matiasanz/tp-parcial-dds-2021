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
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.stream.Collectors;

public class CarritoController {

    public CarritoController(RepoLocales repoLocales, Autenticador<Cliente> autenticador){
        this.repoLocales = repoLocales;
        this.autenticadorCliente = autenticador;
        this.repoPedidos = repoPedidos;
    }

    private RepoLocales repoLocales;
    private Autenticador<Cliente> autenticadorCliente;
    private RepoPedidos repoPedidos;
    private String ERROR_TOKEN = "error";

    public ModelAndView getCarrito(Request req, Response res){

        Cliente cliente = autenticadorCliente.getUsuario(req);
        Carrito carrito = findCarrito(req.params("idLocal"), req, res);

        if(carrito==null){
            return null;
        }

        String mensaje = req.session().attribute(ERROR_TOKEN);
        req.session().removeAttribute(ERROR_TOKEN);

        Modelo modelo = parseModel(carrito)
            .con("direcciones", cliente.getDireccionesConocidas())
            .con(ERROR_TOKEN, mensaje);

        return new ModelAndView(modelo, Templates.CARRITO);
    }

    public ModelAndView agregarItem(Request request, Response response) {
        Carrito carrito = findCarrito(request.params("idLocal"), request, response);

        if(carrito!=null){
            Local local = carrito.getLocal();
            Plato plato = local.getPlato(Long.parseLong(request.queryParams("idPlato")));
            int cantidad = Integer.parseInt(request.queryParams("cantidad"));
            String aclaraciones = request.queryParams("aclaraciones");
            carrito.conItem(new Item(plato, cantidad, aclaraciones));
            response.redirect(URIs.CARRITO(local.getId()));
        }

        return null;
    }

    public ModelAndView finalizarPedido(Request request, Response response) {
        String idLocal = request.queryParams("idLocal");
        Carrito carrito = findCarrito(idLocal, request, response);

        if (carrito == null) {
            return null;
        }

        try {
            Pedido pedido = carrito
                .conDireccion(getDireccion(request))
                .build();
            response.redirect(URIs.PEDIDO(pedido.getId()));
        } catch (PedidoIncompletoException e){
            request.session().attribute(ERROR_TOKEN, e.getMessage());
            response.status(HttpURLConnection.HTTP_BAD_REQUEST);
            Long id = Long.parseLong(idLocal);
            response.redirect(URIs.CARRITO(id));
        }

        return null;
    }

    private Direccion getDireccion(Request req){
        try{
            return autenticadorCliente.getUsuario(req)
                .getDireccionesConocidas()
                .get(Integer.parseInt(req.queryParams("direccion")));
        } catch (NumberFormatException e){
            throw new PedidoIncompletoException("direccion");
        }
    }

    private Carrito findCarrito(String local, Request req, Response res){
        try{
            long idLocal = Long.parseLong(local);
            Cliente cliente = autenticadorCliente.getUsuario(req);
            return cliente.getCarrito(repoLocales.getLocal(idLocal));

        } catch (NumberFormatException | LocalInexistenteException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.HOME);
            return null;
        }
    }

    private Modelo parseModel(Carrito carrito){
        Direccion direccion = carrito.getDireccion();

        return new Modelo("local", carrito.getLocal().getNombre())
            .con("idLocal", carrito.getLocal().getId())
            .con("direccion", (direccion==null)? null : direccion.getCalle())
            .con("items", carrito.getItems().stream().map(this::parseModel).collect(Collectors.toList()));
    }

    private Modelo parseModel(Item item){
        return new Modelo("plato", item.getPlato().getNombre())
            .con("aclaraciones", item.getAclaraciones())
            .con("cantidad", item.getCantidad());
    }
}
