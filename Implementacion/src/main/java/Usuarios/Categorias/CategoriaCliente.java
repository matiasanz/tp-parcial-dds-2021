package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

public interface CategoriaCliente {

    public double calcularTotal(Pedido pedido, Cliente cliente);
}
