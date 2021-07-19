package Usuarios;

import MediosContacto.MedioDeContacto;
import MediosContacto.Notificacion;
import Pedidos.Direccion;
import Pedidos.Pedido;
import Repositorios.Identificable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cliente extends Usuario {
    private List<Direccion> direccionesConocidas = new ArrayList<>(); //TODO: A futuro marcar en mapa
    private List<Pedido> pedidosRealizados = new LinkedList<>();

}

