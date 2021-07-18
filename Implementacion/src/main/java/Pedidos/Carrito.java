package Pedidos;

import Local.Local;
import Utils.Exceptions.PedidoIncompletoException;

import java.util.LinkedList;
import java.util.List;

public class Carrito {
    private Local local;
    private List<Item> items = new LinkedList<>();
    private Direccion direccion;

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

    public Pedido build(){
        if(direccion==null) throw new PedidoIncompletoException("direccion");
        return new Pedido(direccion, local, items);
    }
}
