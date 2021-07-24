package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Local.Local;
import Pedidos.Pedido;
import Usuarios.Cliente;
import com.sun.org.apache.xpath.internal.operations.Mod;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.time.LocalDateTime;
import java.util.List;
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

    public ModelAndView getPedidos(Request req, Response res){
        List<Modelo> pedidos = autenticadorCliente.getUsuario(req)
            .getPedidosRealizados()
            .stream()
            .map(this::parseModel)
            .collect(Collectors.toList());

        int[] numero = {pedidos.size()}; //Hice esto para que me dejara usarlo en la lambda
        pedidos.forEach(p->p.con("numero", numero[0]--));

        return new ModelAndView(new Modelo("pedidos", pedidos), Templates.PEDIDOS);
    }

    private Modelo parseModel(Pedido pedido){
        return new Modelo("local", pedido.getLocal().getNombre())
            .con("importe", pedido.subtotal())
            .con("items", pedido.getItems().stream().map(carritoController::parseModel).collect(Collectors.toList()))
            .con("estado", pedido.getEstado())
            .con(parseModel(pedido.getFechaInicio()))
            .con("direccion", pedido.getDireccion().getCalle());
    }

    private Modelo parseModel(LocalDateTime fechaHora){
        return new Modelo("fecha", fechaHora.getDayOfMonth()+"/"+fechaHora.getMonthValue()+"/"+fechaHora.getYear());
    }
}
