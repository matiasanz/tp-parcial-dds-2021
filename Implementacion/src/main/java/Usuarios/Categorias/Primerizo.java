package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

public class Primerizo extends CategoriaCliente {

    static private float porcentajeDescuento = 0.3f;

    @Override
    public String getNombre() {
        return "Primerizo";
    }

    @Override
    public double descuentoPorCategoria(double precio, Cliente cliente) {
        return porcentajeDescuento*precio;
    }

    public CategoriaCliente siguienteCategoria(){
        return new Ocasional();
    }

    public void notificarPedido(Pedido pedido, Cliente cliente){
        cambiarDeCategoria(cliente, siguienteCategoria());
    }
}
