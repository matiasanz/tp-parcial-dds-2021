package Pedidos;

import Local.Local;
import Pedidos.Descuentos.Descuento;
import Pedidos.Descuentos.SinDescuento;
import Utils.Exceptions.PedidoIncompletoException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Carrito {
    static Long idPedido = 0L; //TODO: Esto se tiene que ir

    private Local local;
    private List<Item> items = new LinkedList<>();
    private Direccion direccion;
    private Descuento descuento = new SinDescuento();

    public Carrito(Local local){
        this.local=local;
    }

    public Carrito conDireccion(Direccion direccion){
        this.direccion = direccion;
        return this;
    }

    public Carrito conItem(Item item){
        items.add(item);
        return this;
    }

    public Carrito conDescuento(Descuento descuento){
        this.descuento = descuento;
        return this;
    }

    public void vaciar(){
        items = new ArrayList<>();
        direccion=null;
    }

    public Pedido build(){
        if(local==null) throw new PedidoIncompletoException("local");
        if(direccion==null) throw new PedidoIncompletoException("direccion");
        if(items.isEmpty()) throw new PedidoIncompletoException("items");
        Pedido pedido = new Pedido(getPrecioFinal(), direccion,  local, items);
        local.notificarPedido(pedido);
        return pedido;
    }

    public Local getLocal(){
        return local;
    }

    public List<Item> getItems(){
        return items;
    }

    public Direccion getDireccion(){
        return direccion;
    }


    public Double getPrecioFinal(){
        return getPrecioBase() - descuento.calcularSobre(getPrecioBase());
    }

    public Double getPrecioBase(){
        return items.stream().mapToDouble(Item::getPrecio).sum();
    }

    public void sacarItem(int numero) {
        items.remove(numero);
    }
}
