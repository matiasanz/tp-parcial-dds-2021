package Usuarios;

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

    private List<Direccion> direccionesConocidas = new ArrayList<>();
    private List<Pedido> pedidosRealizados = new LinkedList<>();

}

