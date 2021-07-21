package MediosContacto;

import Usuarios.Cliente;
import Usuarios.Usuario;

public interface MedioDeContacto {
    public void enviarNotificacion(Usuario usuario, Notificacion notificacion);
}
