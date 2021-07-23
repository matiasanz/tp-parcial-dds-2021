package Usuarios;

import MediosContacto.Notificacion;
import Repositorios.Templates.Identificable;

import java.util.LinkedList;
import java.util.List;

public abstract class Usuario extends Identificable {
    private String username;
    private String password;
    public String mail;

    public String nombre;
    private String apellido;

    private List<Notificacion> notificacionesPush = new LinkedList<>();

    public Usuario(String mail, String nombre) {
        this.mail = mail;
        this.nombre = nombre;
    }

    //Credenciales
    public String getUsername(){
        return username;
    }

    //Datos
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() { return mail; }

    //Notificaciones
    public List<Notificacion> getNotificacionesPush() {
        return notificacionesPush;
    }

    public void agregarNotificacionPush(Notificacion notificacion) {
        notificacionesPush.add(notificacion);
    }
}

