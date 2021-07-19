package Usuarios;

import MediosContacto.Notificacion;
import Repositorios.Identificable;

import java.util.LinkedList;
import java.util.List;

public abstract class Usuario extends Identificable {
    private String username;
    private String password;

    private String nombre;
    private String apellido;

    private List<Notificacion> notificacionesPush = new LinkedList<>();

    //Datos
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    //Notificaciones
    public List<Notificacion> getNotificacionesPush() {
        return notificacionesPush;
    }

    public void agregarNotificacionPush(Notificacion notificacion) {
        notificacionesPush.add(notificacion);
    }
}

