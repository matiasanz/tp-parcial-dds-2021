package MediosContacto;

import Usuarios.Cliente;
import Usuarios.Usuario;

public class NotificadorPush implements MedioDeContacto{

    @Override
    public void enviarNotificacion(Usuario usuario, Notificacion notificacion) {
        usuario.agregarNotificacionPush(notificacion);
    }

}
