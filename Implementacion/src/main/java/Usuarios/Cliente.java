package Usuarios;

import Local.Local;
import Pedidos.Carrito;
import Pedidos.Cupones.CuponDescuento;
import Pedidos.Cupones.SinCupon;
import Pedidos.Pedido;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Categorias.Primerizo;

import java.util.*;

public class Cliente extends Usuario {
    public Cliente(String usuario, String contrasenia, String nombre, String apellido, String mail, String direccion){
        super(usuario, contrasenia, nombre, apellido, mail);
        direccionesConocidas.add(direccion);
        agregarDescuento(new SinCupon());
    }

    private Map<Long, Carrito> carritos = new HashMap<>();

    private List<String> direccionesConocidas = new ArrayList<>();
    private List<Pedido> pedidosRealizados = new LinkedList<>();
    public CategoriaCliente categoria = new Primerizo();
    public int cantidadComprasHechas;
    private List<CuponDescuento> cupones = new ArrayList<>();

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

    public List<CuponDescuento> getCupones(){
        return cupones;
    }

    public void agregarDescuento(CuponDescuento descuento){
        cupones.add(descuento);
    }

    public void quitarDescuento(CuponDescuento descuento) {
        cupones.remove(descuento);
    }

    public Double descuentoPorCategoria(Double precio) {
        return categoria.descuentoPorCategoria(precio, this);
    }
}

