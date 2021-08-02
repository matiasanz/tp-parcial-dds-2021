package Controladores.Cliente;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Pedidos.Pedido;
import Usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static Controladores.Utils.Modelos.parseModel;

public class PedidosController {
    private Autenticador<Cliente> autenticadorCliente;

    public PedidosController(Autenticador<Cliente> autenticador){
        this.autenticadorCliente=autenticador;
    }

    public ModelAndView getPedido(Request req, Response res){
        Cliente cliente = autenticadorCliente.getUsuario(req);

        try{
            int id = Integer.parseInt(req.params("id"));
            Pedido pedido = cliente.getPedidosRealizados().get(id-1);
            return new ModelAndView(parseModel(pedido).con("numero", id), Templates.PEDIDO);

        } catch (NumberFormatException | IndexOutOfBoundsException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.PEDIDOS);
            return null;
        }
    }

    public ModelAndView getPedidos(Request req, Response res){
        List<Pedido> pedidos = autenticadorCliente.getUsuario(req)
            .getPedidosRealizados();

        return new ModelAndView(new Modelo("pedidos", parseModel(pedidos)), Templates.PEDIDOS);
    }
}
