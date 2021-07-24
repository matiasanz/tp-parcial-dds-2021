package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;
import Utils.ProveedorDeNotif;

public class Ocasional extends CategoriaCliente{
    String nombre = "ocasional";
    int cantidadComprasParaPertenecer = 10;
    Frecuente frecuente;

    @Override
    public double calcularTotal(Pedido pedido, Cliente cliente) {
        return pedido.getImporte()-pedido.getImporte()*0.1;
    }

    public void notificarPedido(Pedido pedido, Cliente cliente) {
        if (cliente.getCantidadComprasHechas() > cantidadComprasParaPertenecer) {
            cliente.setCategoria(frecuente);
            cliente.notificar(ProveedorDeNotif.notificacionAscensoDeCategoria(cliente, frecuente));
        }
    }



}
