package Local;

import MediosContacto.MedioDeContacto;
import Pedidos.Direccion;
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

    public Contacto(String usuario, String contrasenia, String nombre, String apellido, String mail, Local local) {
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
