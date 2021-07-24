package Local;

import MediosContacto.MedioDeContacto;
import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Contacto extends Usuario {
    String apellido;
    List<MedioDeContacto> mediosDeContacto = new ArrayList<>();

    public Contacto(String mail, String nombre) {
        super(mail, nombre);
    }
}
