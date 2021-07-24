package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Pedidos.Pedido;
import Usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.Optional;
import java.util.stream.Collectors;

public class PedidosController {
    private Autenticador<Cliente> autenticadorCliente;
    private CarritoController carritoController = new CarritoController(null, null);
    //TODO: Sacar este atributo

    public PedidosController(Autenticador<Cliente> autenticador){
        this.autenticadorCliente=autenticador;
    }

    public ModelAndView getPedido(Request req, Response res){
        Cliente cliente = autenticadorCliente.getUsuario(req);
        long id = Long.parseLong(req.params("id"));

        Optional<Pedido> pedido = cliente.getPedidosRealizados()
            .stream().filter(p->p.matchId(id)).findAny();

        if(!pedido.isPresent()){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.PEDIDOS);
            return null;
        }

        return new ModelAndView(parseModel(pedido.get()), Templates.PEDIDO);
    }

    private Modelo parseModel(Pedido pedido){
        return new Modelo("id", pedido.getId())
            .con("local", pedido.getLocal().getNombre())
            .con("precio", pedido.subtotal())
            .con("items", pedido.getItems().stream().map(carritoController::parseModel).collect(Collectors.toList()))
            .con("estado", pedido.getEstado())
            .con("direccion", pedido.getDireccion().getCalle());
    }
}
