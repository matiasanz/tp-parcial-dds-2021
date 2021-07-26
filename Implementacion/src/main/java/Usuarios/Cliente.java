package Usuarios;

import Local.Local;
import Pedidos.Carrito;
import Pedidos.Descuentos.Descuento;
import Pedidos.Descuentos.SinDescuento;
import Pedidos.Direccion;
import Pedidos.Pedido;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Categorias.Primerizo;

import java.util.*;

public class Cliente extends Usuario {
    public Cliente(String usuario, String contrasenia, String nombre, String apellido, String mail, Direccion direccion){
        super(usuario, contrasenia, nombre, apellido, mail);
        direccionesConocidas.add(direccion);
        agregarDescuento(new SinDescuento());
    }

    private Map<Long, Carrito> carritos = new HashMap<>();

    private List<Direccion> direccionesConocidas = new ArrayList<>();
    private List<Pedido> pedidosRealizados = new LinkedList<>();
    public CategoriaCliente categoria = new Primerizo();
    public int cantidadComprasHechas;
    private List<Descuento> descuentosDisponibles = new ArrayList<>();

    public Carrito getCarrito(Local local) {
        Carrito carrito = carritos.getOrDefault(local.getId(), new Carrito(this, local));
        carritos.put(local.getId(), carrito);
        return carrito;
    }

    public List<Direccion> getDireccionesConocidas() {
        return direccionesConocidas;
    }

    public CategoriaCliente getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCliente categoria) {
        this.categoria = categoria;
    }

    public int getCantidadComprasHechas() {
        return cantidadComprasHechas;
    }

    public List<Pedido> getPedidosRealizados() {
        return pedidosRealizados;
    }

    public void agregarPedido(Pedido pedido) {
        pedidosRealizados.add(pedido);
        categoria.notificarPedido(pedido, this);
    }

    public List<Descuento> getDescuentos(){
        return descuentosDisponibles;
    }

    public void agregarDescuento(Descuento descuento){
        descuentosDisponibles.add(descuento);
    }

    public void quitarDescuento(Descuento descuento) {
        descuentosDisponibles.remove(descuento);
    }

    public Double descuentoPorCategoria(Double precioFinal) {
        return categoria.descuentoPorCategoria(precioFinal, this);
    }
}

