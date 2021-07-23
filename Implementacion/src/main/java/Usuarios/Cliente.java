package Usuarios;

import Local.Local;
import Pedidos.Carrito;
import Pedidos.Direccion;
import Pedidos.Pedido;

import java.util.*;

public class Cliente extends Usuario {
    public Cliente(String usuario, String contrasenia, String nombre, String apellido, Direccion direccion){
        super(usuario, contrasenia, nombre, apellido);
        direccionesConocidas.add(direccion);
    }

    private Map<Long, Carrito> carritos = new HashMap<>();

    private List<Direccion> direccionesConocidas = new ArrayList<>();
    private List<Pedido> pedidosRealizados = new LinkedList<>();

    public Carrito getCarrito(Local local) {
        Carrito carrito = carritos.get(local.getId());
        if(carrito==null){
            carrito = new Carrito(local);
            carritos.put(local.getId(), carrito);
        }

        return carrito;
    }
}

