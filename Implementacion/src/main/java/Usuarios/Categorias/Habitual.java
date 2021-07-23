package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

public class Habitual extends CategoriaCliente {

    String nombre= "habitual";
    int valorTope = 700;

    @Override
    public double calcularTotal(Pedido pedido, Cliente cliente) {
        if (pedido.subtotal() > valorTope) {
            return pedido.subtotal() - pedido.subtotal() * 0.5;
        } else {
            return pedido.subtotal();
        }
    }
}