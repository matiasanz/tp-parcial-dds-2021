package Usuarios;

import MediosContacto.MedioDeContacto;
import MediosContacto.Notificacion;
import Repositorios.Templates.Identificable;
import MediosContacto.MongoHandler;
import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public abstract class Usuario extends Identificable {
    public Usuario(String username, String contrasenia, String nombre, String apellido, String mail){
        this.username=username;
        this.password=contrasenia;
        this.nombre=nombre;
        this.apellido=apellido;
        this.mail=mail;
    }

    //TODO: BOrrar
    public Usuario(String nombre, String mail){
        this.nombre=nombre;
        this.mail=mail;
    }

    private String username;
    private String password;
    public String mail;

    public String nombre;
    private String apellido;

    private List<MedioDeContacto> mediosDeContacto = new ArrayList<>();

    private List<Notificacion> notificacionesPush = new LinkedList<>();

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
        mediosDeContacto.add(nuevoMedio);
    }

    public void notificar(Notificacion mensaje){
        mediosDeContacto.forEach(n->n.notificar(this,mensaje));
    }
}

