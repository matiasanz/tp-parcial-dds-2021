package Usuarios.Categorias;

import Pedidos.Pedido;
import Usuarios.Cliente;

public class Frecuente extends CategoriaCliente{

    static int pedidosParaCambio = 30;
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

        if (cliente.getCantidadComprasHechas() > pedidosParaCambio) {
            cambiarDeCategoria(cliente, siguienteCategoria());
        }
    }

}
