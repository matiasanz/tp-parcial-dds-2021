package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("o")
public class Ocasional extends CategoriaCliente{
    @Transient
    static int pedidosParaCambio = 10;
    @Transient
    static float porcentajeDescuento = 0.1f;

    private int pedidosHechos = 0;

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
        pedidosHechos++;
        if (pedidosHechos > pedidosParaCambio) {
            cambiarDeCategoria(cliente, siguienteCategoria());
        }
    }

    private int getPedidosHechos(){
        return pedidosHechos;
    }

    private void setPedidosHechos(int pedidosHechos){
        this.pedidosHechos = pedidosHechos;
    }
}
