package MediosContacto;

import Repositorios.Templates.Identificado;
import Usuarios.Usuario;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="medio")
public abstract class MedioDeContacto extends Identificado {
    public abstract void notificar(Usuario usuario, Notificacion notificacion);
}
