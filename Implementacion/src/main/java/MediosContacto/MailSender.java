package MediosContacto;

import Usuarios.Cliente;
import Usuarios.Usuario;
import Utils.Exceptions.PendingException;

public class MailSender implements MedioDeContacto{
    @Override
    public void notificar(Usuario usuario, Notificacion notificacion) {
        throw new PendingException("notificador de mail");
    }
}
