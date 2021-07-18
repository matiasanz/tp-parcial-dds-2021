package MediosContacto;

import Usuarios.Cliente;

public class NotificadorPush implements MedioDeContacto{

    @Override
    public void notificar(Cliente cliente, Notificacion notificacion) {
        cliente.agregarNotificacionPush(notificacion);
    }

}
