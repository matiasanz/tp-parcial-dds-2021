package MediosContacto;

import Cliente.Cliente;

public class NotificadorPush implements MedioDeContacto{

    @Override
    public void notificar(Cliente cliente, Notificacion notificacion) {
        cliente.agregarNotificacionPush(notificacion);
    }

}
