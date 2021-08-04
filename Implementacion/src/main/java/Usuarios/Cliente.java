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

    @OneToMany(cascade = CascadeType.ALL,mappedBy="cliente")
    private List<Carrito> carritos = new LinkedList<>();

    @ElementCollection
    @JoinTable(name="DireccionXCliente", joinColumns=@JoinColumn(name="cliente"))
    private List<String> direccionesConocidas = new ArrayList<>();
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    @OrderColumn(name="numero_pedido")
    private List<Pedido> pedidosRealizados = new LinkedList<>();

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="categoria")
    public CategoriaCliente categoria = new Primerizo();

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private List<CuponDescuento> cupones = new ArrayList<>();

    public void setCupones(List<CuponDescuento> cupones) {
        this.cupones = cupones;
    }

    public Cliente() {}

    public Carrito getCarrito(Local local) {
        //Carrito carrito = carritos.getOrDefault(local.getId(), new Carrito(this, local));
        //carritos.put(local.getId(), carrito);
        Optional<Carrito> carrito = carritos.stream().filter(c->c.getLocal().matchId(local.getId())).findFirst();
        if(!carrito.isPresent()){
            Carrito nuevoCarrito = new Carrito(this, local);
            carritos.add(nuevoCarrito);
            return nuevoCarrito;
        }
        return carrito.get();
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

    public void agregarDireccion(String direccion) {
        direccionesConocidas.add(direccion);
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }
}

