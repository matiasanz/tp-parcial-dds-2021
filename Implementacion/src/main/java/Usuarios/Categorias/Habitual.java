package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

public class Habitual extends CategoriaCliente {

    String nombre= "habitual";
    int valorTope = 700;

    @Override
    public double calcularTotal(Pedido pedido, Cliente cliente) {
        if (pedido.getImporte() > valorTope) {
            return pedido.getImporte() - pedido.getImporte() * 0.5;
        } else {
            return pedido.getImporte();
        }
    }
}