package Usuarios;

import Pedidos.Carrito;
import Pedidos.Direccion;
import Pedidos.Pedido;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cliente extends Usuario {
    public Cliente(String usuario, String contrasenia, String nombre, String apellido, Direccion direccion){
        super(usuario, contrasenia, nombre, apellido);
        direccionesConocidas.add(direccion);
    }

    private Carrito carrito = new Carrito();

    private List<Direccion> direccionesConocidas = new ArrayList<>();
    private List<Pedido> pedidosRealizados = new LinkedList<>();

    public Carrito getCarrito() {
        return carrito;
    }
}

