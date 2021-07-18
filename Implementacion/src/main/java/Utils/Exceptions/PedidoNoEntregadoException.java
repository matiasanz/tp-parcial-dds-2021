package Utils.Exceptions;
import Pedidos.Pedido;

public class PedidoNoEntregadoException extends RuntimeException{
    public PedidoNoEntregadoException(Pedido pedido){
        super("El pedido "+pedido.getId()+" aun no fue entregado");
    }
}