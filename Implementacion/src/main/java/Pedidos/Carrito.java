package Pedidos;

import Local.Local;
import Utils.Exceptions.PedidoIncompletoException;

import java.util.ArrayList;
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

    public void vaciar(){
        items = new ArrayList<>();
        direccion=null;
    }

    public Pedido build(){
        if(local==null) throw new PedidoIncompletoException("local");
        if(direccion==null) throw new PedidoIncompletoException("direccion");
        if(items.isEmpty()) throw new PedidoIncompletoException("items");
        Pedido pedido = new Pedido(direccion, local, items);
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
}
