package Usuarios;

import Local.Local;
import MediosContacto.MedioDeContacto;
import Platos.Combo;
import Usuarios.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("d")
public class Encargado extends Usuario {

    @OneToOne (cascade = CascadeType.ALL)
    private Local local;

    public Encargado() {
        super();
    }
    public Encargado(String usuario, String contrasenia, String nombre, String apellido, String mail, Local local) {
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
