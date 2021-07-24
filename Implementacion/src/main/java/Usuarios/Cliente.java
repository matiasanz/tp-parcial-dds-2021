package Usuarios;

import Local.Local;
import Pedidos.Carrito;
import Pedidos.Direccion;
import Pedidos.Pedido;
import Usuarios.Categorias.CategoriaCliente;

import java.util.*;

public class Cliente extends Usuario {
    public Cliente(String usuario, String contrasenia, String nombre, String apellido, String mail, Direccion direccion){
        super(usuario, contrasenia, nombre, apellido, mail);
        direccionesConocidas.add(direccion);
    }

    private Map<Long, Carrito> carritos = new HashMap<>();

    private List<Direccion> direccionesConocidas = new ArrayList<>();
    private List<Pedido> pedidosRealizados = new LinkedList<>();
    public CategoriaCliente categoria;
    public int cantidadComprasHechas;

    public Carrito getCarrito(Local local) {
        Carrito carrito = carritos.get(local.getId());
        if(carrito==null){
            carrito = new Carrito(local);
            carritos.put(local.getId(), carrito);
        }

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
    }
}

