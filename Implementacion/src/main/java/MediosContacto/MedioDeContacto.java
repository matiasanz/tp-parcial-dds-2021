package MediosContacto;

import Repositorios.Templates.Identificable;
import Usuarios.Usuario;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="medio")
public abstract class MedioDeContacto extends Identificable {
    public abstract void notificar(Usuario usuario, Notificacion notificacion);
}
