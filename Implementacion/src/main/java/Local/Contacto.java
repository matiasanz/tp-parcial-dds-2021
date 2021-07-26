package Local;

import MediosContacto.MedioDeContacto;
import Platos.Combo;
import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Contacto extends Usuario {
    private Local local;

    //TODO: AgregarLOCAL
    public Contacto(String mail, String nombre) {
        super(mail, nombre);
    }

    public Local getLocal() {
        return local;
    }
}
