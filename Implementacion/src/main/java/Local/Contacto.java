package Local;

import MediosContacto.MedioDeContacto;

import java.util.List;

public class Contacto {
    String nombre;
    String apellido;
    List<MedioDeContacto> mediosDeContacto;

    public Contacto(String nombre, String apellido, List<MedioDeContacto> mediosDeContacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeContacto = mediosDeContacto;
    }
}
