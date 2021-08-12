package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("p")
public class Primerizo extends CategoriaCliente {

    @Transient
    public static int pedidosParaCambio = 5;

    @Override
    public String getNombre() {
        return "Primerizo";
    }

    @Override
    public double descuentoPorCategoria(double precio) {
        return 0.0;
    }

    public CategoriaCliente siguienteCategoria(){
        return new Ocasional();
    }

    public void notificarPedido(Pedido pedido, Cliente cliente){
        if(cliente.getPedidosRealizados().size()>=pedidosParaCambio){
            cambiarDeCategoria(cliente, siguienteCategoria());
        }
    }
}
