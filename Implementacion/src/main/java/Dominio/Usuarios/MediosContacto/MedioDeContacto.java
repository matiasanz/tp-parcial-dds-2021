package Dominio.Usuarios.MediosContacto;

import Repositorios.Templates.Identificado;
import Dominio.Usuarios.Usuario;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="medio")
@Table(name="mediosDeContacto")
public abstract class MedioDeContacto extends Identificado {
    public abstract void notificar(Usuario usuario, Notificacion notificacion);
}
