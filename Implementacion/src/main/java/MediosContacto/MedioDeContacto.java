package MediosContacto;

import Usuarios.Usuario;

public interface MedioDeContacto {
    public void notificar(Usuario usuario, Notificacion notificacion);
}
