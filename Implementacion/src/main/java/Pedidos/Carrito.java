package Pedidos;

import Local.Local;
import Utils.Exceptions.PedidoIncompletoException;

import java.util.LinkedList;
import java.util.List;

public class Carrito {
    private Local local;
    private List<Item> items = new LinkedList<>();
    private Direccion direccion;

    public Carrito conLocal(Local local){
        this.local=local;
        return this;
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
        if(local==null) throw new PedidoIncompletoException("local");
        if(direccion==null) throw new PedidoIncompletoException("direccion");
        return new Pedido(direccion, local, items);
    }
}
