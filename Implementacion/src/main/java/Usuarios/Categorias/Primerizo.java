package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Cliente;
import Utils.ProveedorDeNotif;

public class Primerizo extends CategoriaCliente {
    String nombre = "primerizo";
    Ocasional ocasional;

    @Override
    public double calcularTotal(Pedido pedido,  Cliente cliente) {
        return 0;
    }

    public void notificarPedido(Pedido pedido, Cliente cliente){
        cliente.setCategoria(ocasional);
        cliente.notificar(ProveedorDeNotif.notificacionAscensoDeCategoria(cliente, ocasional));
    }
}
