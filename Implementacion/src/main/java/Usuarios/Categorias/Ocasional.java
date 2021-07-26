package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

public class Ocasional extends CategoriaCliente{
    int pedidosParaCambio = 10;
    float porcentajeDescuento = 0.1f;

    @Override
    public String getNombre(){
        return "ocasional";
    }

    @Override
    public double descuentoPorCategoria(double precio, Cliente cliente) {
        return porcentajeDescuento*precio;
    }

    public CategoriaCliente siguienteCategoria(){
        return new Frecuente();
    }

    public void notificarPedido(Pedido pedido, Cliente cliente) {
        if (cliente.getCantidadComprasHechas() > pedidosParaCambio) {
            cambiarDeCategoria(cliente, siguienteCategoria());
        }
    }
}
