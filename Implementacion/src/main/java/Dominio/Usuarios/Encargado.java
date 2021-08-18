package Dominio.Usuarios;

import Dominio.Local.Local;

import javax.persistence.*;

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
