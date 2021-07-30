package Pedidos;

import Local.Local;
import Pedidos.Cupones.CuponDescuento;
import Pedidos.Cupones.SinCupon;
import Usuarios.Cliente;
import Utils.Exceptions.PedidoIncompletoException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Carrito {
    static Long idPedido = 0L; //TODO: Esto se tiene que ir

    private Cliente cliente;
    private Local local;
    private List<Item> items = new LinkedList<>();
    private String direccion;
    private CuponDescuento cupon = new SinCupon();

    public Carrito(Cliente cliente, Local local){
        this.cliente = cliente;
        this.local=local;
    }

    public Carrito conDireccion(String direccion){
        this.direccion = direccion;
        return this;
    }

    public Carrito conItem(Item item){
        items.add(item);
        return this;
    }

    public Carrito conCupon(CuponDescuento cupon){
        this.cupon = cupon;
        return this;
    }

    public void vaciar(){
        items = new ArrayList<>();
        direccion=null;
    }

    public Pedido build(){
        validarPedido();
        Pedido pedido = new Pedido(getPrecioFinal(), direccion,  local, items, cliente);
        local.agregarPedido(pedido);
        cupon.notificarUso(cliente, this);
        return pedido;
    }

    private void validarPedido() {
        if(local==null) throw new PedidoIncompletoException("local");
        if(direccion==null) throw new PedidoIncompletoException("direccion");
        if(items.isEmpty()) throw new PedidoIncompletoException("items");
    }

    public void sacarItem(int numero) {
        items.remove(numero);
    }

    public Local getLocal(){
        return local;
    }

    public List<Item> getItems(){
        return items;
    }

    public String getDireccion(){
        return direccion;
    }

    public Double getPrecioFinal(){
        return getSubtotal() - descuentoPorCupon();
    }

    public Double getSubtotal(){
        return getPrecioBase() - descuentoPorCategoria();
    }

    public Double getPrecioBase(){
        return items.stream().mapToDouble(Item::getPrecio).sum();
    }

    public Double descuentoPorCupon(){
        return cupon.calcularSobre(getSubtotal());
    }

    public Double descuentoPorCategoria(){
        return cliente.descuentoPorCategoria(getPrecioBase());
    }


}
