package Pedidos;

import Local.Local;
import Repositorios.Templates.Identificado;
import Usuarios.Cliente;
import Utils.Exceptions.PedidoIncompletoException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="carritos")
public class Carrito extends Identificado {

    @OneToOne(cascade = CascadeType.ALL)
    Pedido pedido = new Pedido();

    public Carrito(Cliente cliente, Local local){
        pedido.setCliente(cliente);
        pedido.setLocal(local);
    }

    public Carrito() {}

    public Carrito conDireccion(String direccion){
        pedido.setDireccion(direccion);
        return this;
    }

    public Carrito conItem(Item item){
        pedido.agregarItem(item);
        return this;
    }

    public Pedido build(){
        validarPedido();
        pedido.setPrecioAbonado(getPrecio());
        pedido.getLocal().agregarPedido(pedido);
        pedido.getItems().forEach(Item::notificarCompra);
        return pedido;
    }

    private void validarPedido() {
        String direccion = pedido.getDireccion();
        if(pedido.getLocal()==null) throw new PedidoIncompletoException("local");
        if(direccion==null || direccion.isEmpty()) throw new PedidoIncompletoException("direccion");
        if(pedido.getItems().isEmpty()) throw new PedidoIncompletoException("items");
    }

    //TODO
    public void sacarItem(int numero) {
        pedido.getItems().remove(numero);
    }

    public Local getLocal(){
        return pedido.getLocal();
    }

    public List<Item> getItems(){
        return pedido.getItems();
    }

    public String getDireccion(){
        return pedido.getDireccion();
    }

    public Double descuentoPorCategoria(){
        return getCliente().descuentoPorCategoria(getPrecioBase());
    }

    public Cliente getCliente() {
        return pedido.getCliente();
    }

    private Double getPrecio(){
        return getPrecioBase() - descuentoPorCategoria();
    }

    public Double getPrecioBase(){
        return pedido.precioBase();
    }

}
