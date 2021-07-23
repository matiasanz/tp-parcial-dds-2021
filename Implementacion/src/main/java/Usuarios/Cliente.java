package Usuarios;

import Pedidos.Direccion;
import Pedidos.Pedido;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cliente extends Usuario {
    private List<Direccion> direccionesConocidas = new ArrayList<>(); //TODO: A futuro marcar en mapa
    private List<Pedido> pedidosRealizados = new LinkedList<>();

    public Cliente(String mail, String nombre) {
        super(mail, nombre);
    }
}

