package Usuarios;

import MediosContacto.MedioDeContacto;
import MediosContacto.Notificacion;
import Pedidos.Pedido;
import Repositorios.Identificable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Cliente<Direccion> extends Identificable {
    private String nombre;
    private String apellido;
    private List<Direccion> direccionesConocidas = new ArrayList<>(); //TODO: A futuro marcar en mapa
    private List<MedioDeContacto> mediosDeContacto = new LinkedList<>();
    private List<Notificacion> notificacionesPush = new LinkedList<>();
    private List<Pedido> pedidos = new LinkedList<>();

    public List<Notificacion> getNotificacionesPush(){
        return notificacionesPush;
    }

    public void agregarNotificacionPush(Notificacion notificacion){
        notificacionesPush.add(notificacion);
    }

    public String getNombre(){
        return nombre;
    }
}

