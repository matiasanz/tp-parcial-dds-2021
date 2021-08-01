package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("h")
public class Habitual extends CategoriaCliente {

    static double precioMinimoDescuento = 700.0;

    @Override
    public String getNombre(){
        return "Habitual";
    }

    @Override
    public double descuentoPorCategoria(double importe, Cliente cliente) {
        return porcentajeDescuento(importe)*importe;
    }

    private float porcentajeDescuento(double importe){
        return importe>precioMinimoDescuento? 0.5f: 0f;
    }

    @Override
    public void notificarPedido(Pedido pedido, Cliente cliente){
        //Estado final
    }
}