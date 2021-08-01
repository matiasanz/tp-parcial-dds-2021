package Local;

import MediosContacto.MedioDeContacto;
import Platos.Combo;
import Usuarios.Usuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("d")
public class Duenio extends Usuario {

    @OneToOne
    private Local local;

    public Duenio() {
        super();
    }
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
