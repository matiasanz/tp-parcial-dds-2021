package Dominio.Usuarios.Categorias;

import Dominio.Pedidos.Pedido;
import Dominio.Usuarios.Cliente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("o")
public class Ocasional extends CategoriaCliente{

    @Transient
    public static int pedidosParaCambio = 15;
    @Transient
    static float porcentajeDescuento = 0.15f;

    @Transient
    public static double precioMinimoDescuento = 1500.0;

    private int pedidosHechos = 0;

    @Override
    public double descuentoPorCategoria(double importe) {
        return porcentajeDescuento(importe)*importe;
    }

    private float porcentajeDescuento(double importe){
        return importe>=precioMinimoDescuento? porcentajeDescuento: 0f;
    }

    public CategoriaCliente siguienteCategoria(){
        return new Frecuente();
    }

    public void notificarPedido(Pedido pedido, Cliente cliente) {
        pedidosHechos++;
        if (pedidosHechos >= pedidosParaCambio) {
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
