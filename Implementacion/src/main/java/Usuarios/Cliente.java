package Usuarios;

import Local.Local;
import Pedidos.Carrito;
import Pedidos.Cupones.CuponDescuento;
import Pedidos.Cupones.SinCupon;
import Pedidos.Pedido;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Categorias.Primerizo;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("c")
public class Cliente extends Usuario {
    public Cliente(String usuario, String contrasenia, String nombre, String apellido, String mail, String direccion){
        super(usuario, contrasenia, nombre, apellido, mail);
        direccionesConocidas.add(direccion);
    }

    @Transient
    private Map<Long, Carrito> carritos = new HashMap<>();

    @ElementCollection
    @JoinTable(name="DireccionXCliente", joinColumns=@JoinColumn(name="cliente"))
    private List<String> direccionesConocidas = new ArrayList<>();
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private List<Pedido> pedidosRealizados = new LinkedList<>();

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="categoria")
    public CategoriaCliente categoria = new Primerizo();

    @OneToMany (cascade = CascadeType.ALL)
    private List<CuponDescuento> cupones = new ArrayList<>();

    public void setCupones(List<CuponDescuento> cupones) {
        this.cupones = cupones;
    }

    public Cliente() {}

    public Carrito getCarrito(Local local) {
        Carrito carrito = carritos.getOrDefault(local.getId(), new Carrito(this, local));
        carritos.put(local.getId(), carrito);
        return carrito;
    }

    public List<String> getDireccionesConocidas() {
        return direccionesConocidas;
    }

    public CategoriaCliente getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCliente categoria) {
        this.categoria = categoria;
    }

    public List<Pedido> getPedidosRealizados() {
        return pedidosRealizados;
    }

    public void agregarPedido(Pedido pedido) {
        pedidosRealizados.add(pedido);
        categoria.notificarPedido(pedido, this);
    }

    public List<CuponDescuento> getCupones(){
        return cupones;
    }

    public void agregarCupon(CuponDescuento descuento){
        cupones.add(descuento);
    }

    public void quitarCupon(CuponDescuento descuento) {
        cupones.remove(descuento);
    }

    public Double descuentoPorCategoria(Double precio) {
        return categoria.descuentoPorCategoria(precio, this);
    }
}

