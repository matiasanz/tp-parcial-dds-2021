package Pedidos;

import Local.Local;
import Pedidos.Cupones.CuponDescuento;
import Pedidos.Cupones.SinCupon;
import Repositorios.Templates.Identificado;
import Usuarios.Cliente;
import Utils.Exceptions.PedidoIncompletoException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="carritos")
public class Carrito extends Identificado {

    //Pedido pedido = new Pedido() //TODO y vaciar hace =new Pedido()

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente cliente;
    @ManyToOne(cascade = CascadeType.ALL)
    private Local local;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="carrito")
    private List<Item> items = new LinkedList<>();
    @Transient
    private String direccion;
    @Transient
    private CuponDescuento cupon = new SinCupon();

    public Carrito(Cliente cliente, Local local){
        this.cliente = cliente;
        this.local=local;
    }

    public Carrito() {
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
        if(direccion==null || direccion.isEmpty()) throw new PedidoIncompletoException("direccion");
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
        return subtotal() - descuentoPorCupon();
    }

    public Double descuentoPorCategoria(){
        return cliente.descuentoPorCategoria(getPrecioBase());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double descuentoPorCupon(){
        return cupon.calcularSobre(subtotal());
    }

    private Double subtotal(){
        return getPrecioBase() - descuentoPorCategoria();
    }

    public Double getPrecioBase(){
        return items.stream().mapToDouble(Item::getPrecio).sum();
    }

}
