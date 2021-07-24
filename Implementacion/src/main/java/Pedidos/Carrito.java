package Pedidos;

import Local.Local;
import Platos.Plato;
import Utils.Exceptions.PedidoIncompletoException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Carrito {
    static Long idPedido = 0L; //TODO: Esto se tiene que ir

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

    public void hardCodearID(Pedido pedido){
        pedido.setId(idPedido++);
        System.out.println("#Provisoriamente hardcodeo el id del pedido en el carrito.\n Esto se tiene que ir!!!");
    }

    public Pedido build(){
        if(local==null) throw new PedidoIncompletoException("local");
        if(direccion==null) throw new PedidoIncompletoException("direccion");
        if(items.isEmpty()) throw new PedidoIncompletoException("items");
        Pedido pedido = new Pedido(direccion, local, items);
        hardCodearID(pedido);
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

    public BigDecimal getPrecio(){
        return items.stream().map(Item::getPrecio).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
