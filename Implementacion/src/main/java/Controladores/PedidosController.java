package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Modelos;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Pedidos.Pedido;
import Usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static Controladores.Utils.Modelos.parseModel;

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

    public ModelAndView getPedidos(Request req, Response res){
        List<Modelo> pedidos = autenticadorCliente.getUsuario(req)
            .getPedidosRealizados()
            .stream()
            .map(Modelos::parseModel)
            .collect(Collectors.toList());

        int[] numero = {pedidos.size()}; //Hice esto para que me dejara usarlo en la lambda
        pedidos.forEach(p->p.con("numero", numero[0]--).con("id", numero[0]));

        return new ModelAndView(new Modelo("pedidos", pedidos), Templates.PEDIDOS);
    }
}
