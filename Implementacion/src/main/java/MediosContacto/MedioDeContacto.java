package MediosContacto;

import Usuarios.Cliente;
import Usuarios.Usuario;

public interface MedioDeContacto {
    public void notificar(Usuario usuario, Notificacion notificacion);
}
