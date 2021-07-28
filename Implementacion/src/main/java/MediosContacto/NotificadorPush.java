package MediosContacto;

import Usuarios.Usuario;

public class NotificadorPush implements MedioDeContacto{

    @Override
    public void notificar(Usuario usuario, Notificacion notificacion) {
        usuario.agregarNotificacionPush(notificacion);
    }

}
