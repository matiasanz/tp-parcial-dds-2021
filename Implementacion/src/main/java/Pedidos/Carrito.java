package Pedidos;

import Local.Local;
import Pedidos.Descuentos.Descuento;
import Pedidos.Descuentos.SinDescuento;
import Usuarios.Cliente;
import Utils.Exceptions.PedidoIncompletoException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Carrito {
    static Long idPedido = 0L; //TODO: Esto se tiene que ir

    private Cliente cliente;
    private Local local;
    private List<Item> items = new LinkedList<>();
    private Direccion direccion;
    private Descuento descuento = new SinDescuento();

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
        double pf = getSubtotal() - descuento.calcularSobre(getSubtotal());
        return redondear(pf);
    }

    public Double getSubtotal(){
        double st = getPrecioBase() - cliente.descuentoPorCategoria(getPrecioBase());
        return redondear(st);
    }

    public Double getPrecioBase(){
        double pb = items.stream().mapToDouble(Item::getPrecio).sum();
        return redondear(pb);
    }

    private Double redondear(double precio){
        BigDecimal bd = BigDecimal.valueOf(precio);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

}
