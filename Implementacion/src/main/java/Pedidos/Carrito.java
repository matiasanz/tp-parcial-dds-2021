package Pedidos;

import Local.Local;
import Pedidos.Cupones.CuponDescuento;
import Pedidos.Cupones.SinDescuento;
import Usuarios.Cliente;
import Utils.Exceptions.PedidoIncompletoException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Carrito {
    static Long idPedido = 0L; //TODO: Esto se tiene que ir

    private Cliente cliente;
    private Local local;
    private List<Item> items = new LinkedList<>();
    private Direccion direccion;
    private CuponDescuento descuento = new SinDescuento();

    public Carrito(Cliente cliente, Local local){
        this.cliente = cliente;
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

    public Carrito conDescuento(CuponDescuento descuento){
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
        Pedido pedido = new Pedido(getPrecioFinal(), direccion,  local, items, cliente);
        local.notificarPedido(pedido);
        descuento.notificarUso(cliente, this);
        return pedido;
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

    public Direccion getDireccion(){
        return direccion;
    }

    public Double getPrecioFinal(){
        double pf = getSubtotal() - descuentoPorCupon();
        return redondear(pf);
    }

    public Double getSubtotal(){
        double st = getPrecioBase() - descuentoPorCategoria();
        return redondear(st);
    }

    public Double getPrecioBase(){
        double pb = items.stream().mapToDouble(Item::getPrecio).sum();
        return redondear(pb);
    }

    public Double descuentoPorCupon(){
        double dp = descuento.calcularSobre(getSubtotal());
        return redondear(dp);
    }

    public Double descuentoPorCategoria(){
        double dc = cliente.descuentoPorCategoria(getPrecioBase());
        return redondear(dc);
    }

    private Double redondear(double precio){
        BigDecimal bd = BigDecimal.valueOf(precio);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }
}
