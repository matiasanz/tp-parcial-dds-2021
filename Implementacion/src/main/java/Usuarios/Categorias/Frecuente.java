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
    static int pedidosParaCambio = 25;
    @Transient
    static int cadaCuantosDescuento = 15;

    int pedidosHechos = 0;

    @Override
    public String getNombre(){
        return "Frecuente";
    }

    @Override
    public double descuentoPorCategoria(double importe, Cliente cliente) {
        return leTocaDescuento(cliente)? importe: 0;
    }

    private boolean leTocaDescuento(Cliente cliente){
        return pedidosHechos % cadaCuantosDescuento == 0;
    }

    public CategoriaCliente siguienteCategoria(){
        return new Habitual();
    }

    public void notificarPedido(Pedido pedido, Cliente cliente) {
        pedidosHechos++ ;

        if (cliente.getPedidosRealizados().size() > pedidosParaCambio) {
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
