package Usuarios;

import MediosContacto.Notificacion;
import Repositorios.Templates.Identificable;

import java.util.LinkedList;
import java.util.List;

public abstract class Usuario extends Identificable {
    public Usuario(String username, String contrasenia, String nombre, String apellido){
        this.username=username;
        this.password=contrasenia;
        this.nombre=nombre;
        this.apellido=apellido;
    }


    private String username;
    private String password;

    private String nombre;
    private String apellido;

    private List<Notificacion> notificacionesPush = new LinkedList<>();

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

    //Notificaciones
    public List<Notificacion> getNotificacionesPush() {
        return notificacionesPush;
    }

    public void agregarNotificacionPush(Notificacion notificacion) {
        notificacionesPush.add(notificacion);
    }

    public String getPassword() {
        return password;
    }
}

