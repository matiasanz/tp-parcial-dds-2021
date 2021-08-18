package Dominio.Usuarios.Categorias;

import Dominio.Pedidos.Pedido;
import Dominio.Usuarios.Cliente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("h")
public class Habitual extends CategoriaCliente {

    private int pedidosHechos = 0;

    @Transient
    public static int cadaCuantosDescuento = 5;

    @Override
    public double descuentoPorCategoria(double importe) {
        return tocaDescuento()? importe: 0;
    }

    private boolean tocaDescuento(){
        return pedidosHechos % cadaCuantosDescuento == 0;
    }

    @Override
    public void notificarPedido(Pedido pedido, Cliente cliente){
        pedidosHechos++;
    }

    public void setPedidosHechos(int cuantos) {
        this.pedidosHechos=cuantos;
    }

    public int getPedidosHechos(){
        return pedidosHechos;
    }
}