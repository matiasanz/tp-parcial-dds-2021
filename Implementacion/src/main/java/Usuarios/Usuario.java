package Usuarios;

import MediosContacto.MedioDeContacto;
import MediosContacto.Notificacion;
import Repositorios.Templates.Identificable;

import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public abstract class Usuario extends Identificable {
    public Usuario(String username, String contrasenia, String nombre, String apellido){
        this.username=username;
        this.password=contrasenia;
        this.nombre=nombre;
        this.apellido=apellido;
    }


    private String username;
    private String password;
    public String mail;

    public String nombre;
    private String apellido;

    private List<MedioDeContacto> notificadores = new LinkedList<>();
    private List<Notificacion> notificacionesPush = new LinkedList<>();

    public Usuario(String mail, String nombre) {
        this.mail = mail;
        this.nombre = nombre;
    }

    //Credenciales
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
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

    public void agregarMedioDeContacto(MedioDeContacto nuevoMedio){
        requireNonNull(nuevoMedio);
        notificadores.add(nuevoMedio);
    }

    public void notificar(Notificacion mensaje){
        notificadores.forEach(n->n.notificar(this,mensaje));
    }
}

