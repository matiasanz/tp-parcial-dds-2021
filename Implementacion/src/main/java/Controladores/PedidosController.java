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

import java.util.stream.Collectors;

public class PedidosController {
    private Autenticador<Cliente> autenticadorCliente;
    private LocalController carritoController = new LocalController(null, null);
    //TODO: Sacar este atributo

    public PedidosController(Autenticador<Cliente> autenticador){
        this.autenticadorCliente=autenticador;
    }

    public ModelAndView getPedido(Request req, Response res){
        Cliente cliente = autenticadorCliente.getUsuario(req);

        try{
            int id = Integer.parseInt(req.params("id"));
            Pedido pedido = cliente.getPedidosRealizados().get(id);
            return new ModelAndView(parseModel(pedido).con("id", id+1), Templates.PEDIDO);

        } catch (NumberFormatException | IndexOutOfBoundsException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.PEDIDOS);
            return null;
        }
    }

    private Modelo parseModel(Pedido pedido){
        return new Modelo("local", pedido.getLocal().getNombre())
            .con("precio", pedido.subtotal())
            .con("items", pedido.getItems().stream().map(carritoController::parseModel).collect(Collectors.toList()))
            .con("estado", pedido.getEstado())
            .con("direccion", pedido.getDireccion().getCalle());
    }
}
