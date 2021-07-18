package MediosContacto;

import Usuarios.Cliente;

public class MailSender implements MedioDeContacto{
    @Override
    public void notificar(Cliente cliente, Notificacion notificacion) {
        throw new RuntimeException("Falta implementar el notificador de mail");
    }
}
