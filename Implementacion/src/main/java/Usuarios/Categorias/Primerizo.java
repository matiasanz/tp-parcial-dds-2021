package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Cliente;

public class Primerizo implements CategoriaCliente {
    Ocasional ocasional;

    @Override
    public double calcularTotal(Pedido pedido,  Cliente cliente) {
        return 0;
    }

    public void notificarPedido(Pedido pedido, Cliente cliente){
        cliente.setCategoria(ocasional); //TODO: aca mandarle notificacion de q pertenece a nueva categ
    }
}
