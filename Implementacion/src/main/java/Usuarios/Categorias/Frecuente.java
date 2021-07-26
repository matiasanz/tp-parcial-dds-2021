package Usuarios.Categorias;

import Pedidos.Carrito;
import Pedidos.Pedido;
import Usuarios.Cliente;
import Utils.ProveedorDeNotif;

public class Frecuente extends CategoriaCliente{

    int pedidosParaCambio = 30;
    int pedidosHechos = 0;
    private int cadaCuantosDescuento = 15;

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

        if (cliente.getCantidadComprasHechas() > pedidosParaCambio) {
            cambiarDeCategoria(cliente, siguienteCategoria());
        }
    }

}
