package Local;

import MediosContacto.MedioDeContacto;

import java.util.List;

public class Contacto {
    String nombre;
    String apellido;
    List<MedioDeContacto> mediosDeContacto;
    String mail;

    public Contacto(String nombre, String apellido, List<MedioDeContacto> mediosDeContacto, String mail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeContacto = mediosDeContacto;
        this.mail=mail;
    }
}
