package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue(value = "f")
public class Frecuente extends CategoriaCliente{

    @Transient
    public static int pedidosParaCambio = 25;

    @Transient
    public static float porcentajeDescuento = 0.1f;

    private int pedidosHechos = 0;

    @Override
    public double descuentoPorCategoria(double precio) {
        return porcentajeDescuento*precio;
    }

    public CategoriaCliente siguienteCategoria(){
        return new Habitual();
    }

    @Override
    public void notificarPedido(Pedido pedido, Cliente cliente) {
        pedidosHechos++;

        if (pedidosHechos >= pedidosParaCambio) {
            cambiarDeCategoria(cliente, siguienteCategoria());
        }
    }

    private void setPedidosHechos(int pedidosHechos){
        this.pedidosHechos=pedidosHechos;
    }
    private int getPedidosHechos() {
        return pedidosHechos;
    }
}
