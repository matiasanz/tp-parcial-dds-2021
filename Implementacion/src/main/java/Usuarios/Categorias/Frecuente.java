package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;
import Utils.ProveedorDeNotif;

public class Frecuente extends CategoriaCliente{

    String nombre = "frecuente";
    int cantidadComprasParaPertenecer = 25;
    Habitual habitual;

    @Override
    public double calcularTotal(Pedido pedido, Cliente cliente) {
        if((cliente.getCantidadComprasHechas()%20)==0){ //cada 20 compras, un dto
            return 0;
        }
        else{
            return pedido.subtotal();
        }
    }

    public void notificarPedido(Pedido pedido, Cliente cliente) {
        if (cliente.getCantidadComprasHechas() > cantidadComprasParaPertenecer) {
            cliente.setCategoria(habitual);
            cliente.notificar(ProveedorDeNotif.notificacionAscensoDeCategoria(cliente, habitual));
        }
    }
}
