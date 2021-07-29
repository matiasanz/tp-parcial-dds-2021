package Local;

import MediosContacto.MedioDeContacto;
import Platos.Combo;
import Usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Duenio extends Usuario {
    private Local local;

    public Duenio(String usuario, String contrasenia, String nombre, String apellido, String mail, Local local) {
        super(usuario, contrasenia, nombre, apellido, mail);
        this.local=local;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local=local;
    }
}
