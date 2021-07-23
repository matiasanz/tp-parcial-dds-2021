package Utils.Exceptions;

import MediosContacto.Notificacion;
import Usuarios.Usuario;

public class MailException extends RuntimeException{

    public MailException(Notificacion mensaje, Usuario usuario){
        super("no se pudo enviar a la cuenta "+usuario.getMail()+ " el mensaje ["+mensaje.getAsunto()+"] "+mensaje.getCuerpo());
    }

}
