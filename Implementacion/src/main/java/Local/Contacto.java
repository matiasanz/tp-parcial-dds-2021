package Local;

import MediosContacto.MedioDeContacto;
import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Contacto extends Usuario {
    String nombre;
    List<MedioDeContacto> mediosDeContacto = new ArrayList<>();
    String mail;

    public Contacto(String mail, String nombre) {
        super(mail, nombre);
    }
}
