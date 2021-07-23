package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

public class Ocasional implements CategoriaCliente{
    int cantidadComprasParaPertenecer = 10;
    Frecuente frecuente;

    @Override
    public double calcularTotal(Pedido pedido, Cliente cliente) {
        return pedido.subtotal()-pedido.subtotal()*0.1;
    }

    public void notificarPedido(Pedido pedido, Cliente cliente) {
        if (cliente.getCantidadComprasHechas() > cantidadComprasParaPertenecer) {
            cliente.setCategoria(frecuente); //TODO: aca mandarle notificacion de q pertenece a nueva categ
        }
    }
}
