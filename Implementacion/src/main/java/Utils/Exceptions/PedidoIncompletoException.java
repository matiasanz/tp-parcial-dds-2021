package Utils.Exceptions;

public class PedidoIncompletoException extends RuntimeException {
    public PedidoIncompletoException(String campo) {
        super("Le falto aclarar el campo "+campo+ " del pedido");
    }
}
