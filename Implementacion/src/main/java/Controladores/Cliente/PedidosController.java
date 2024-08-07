package Controladores.Cliente;

import Controladores.Templates.Autenticador;
import Controladores.Utils.*;
import Dominio.Pedidos.EstadoPedido;
import Dominio.Pedidos.Pedido;
import Dominio.Usuarios.Cliente;
import Dominio.Utils.Exceptions.PuntuacionInvalidaException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.net.HttpURLConnection;

import java.util.List;

import static Controladores.Utils.Modelos.parseModel;

public class PedidosController implements Transaccional{
    private Autenticador<Cliente> autenticador;
    private ErrorHandler errorHandler = new ErrorHandler();

    public PedidosController(Autenticador<Cliente> autenticador){
        this.autenticador =autenticador;
    }

    public ModelAndView getPedido(Request req, Response res){
        Cliente cliente = autenticador.getUsuario(req);

        try{
            int id = Integer.parseInt(req.params("id"));
            Pedido pedido = cliente.getPedidosRealizados().get(id-1);
            Modelo modelo = parseModel(pedido)
                .con("numero", id)
                .con("mensaje", errorHandler.getMensaje(req));
            return new ModelAndView(modelo, Templates.PEDIDO);

        } catch (NumberFormatException | IndexOutOfBoundsException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.PEDIDOS);
            return null;
        }
    }

    public ModelAndView getPedidos(Request req, Response res){
        List<Pedido> pedidos = autenticador.getUsuario(req)
            .getPedidosRealizados();

        return new ModelAndView(new Modelo("pedidos", parseModel(pedidos)), Templates.PEDIDOS);
    }

    public ModelAndView notificarEntregaPedido(Request req, Response res){
        Cliente cliente = autenticador.getUsuario(req);
        try {
            int nro = Integer.parseInt(req.queryParams("numero"));
            Pedido pedido = cliente.getPedidosRealizados().get(nro - 1);

            res.redirect(URIs.PEDIDO(nro));

            Float puntuacion = req.queryMap("puntuacion").floatValue();

            validarPuntuacion(puntuacion);

            withTransaction(() -> {
                pedido.setEstado(EstadoPedido.ENTREGADO);
                pedido.setPuntuacion(puntuacion);
                pedido.setDetallePuntuacion(req.queryParams("critica"));
            });

            res.status(200);
        } catch (PuntuacionInvalidaException e){
            handleError(req, res, e.getMessage());
        } catch (NumberFormatException | IndexOutOfBoundsException e){
            handleError(req, res, "Se recibieron datos invalidos");
        }

        return null;
    }

    private void handleError(Request req, Response res, String mensaje){
        errorHandler.setMensaje(req, mensaje);
        res.redirect(URIs.PEDIDOS);
        res.status(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    private void validarPuntuacion(Float puntuacion){
        if(puntuacion==null || puntuacion<1 || puntuacion>5)
            throw new PuntuacionInvalidaException(puntuacion);
    }
}