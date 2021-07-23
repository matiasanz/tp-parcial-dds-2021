package Utils.Exceptions;

import MediosContacto.Notificacion;
import Usuarios.Usuario;

public class MailNoEnviadoException extends RuntimeException{

    public MailNoEnviadoException(Notificacion mensaje, Usuario usuario){
        super("no se pudo enviar a la cuenta "+usuario.getMail()+ " el mensaje ["+mensaje.getAsunto()+"] "+mensaje.getCuerpo());
    }

}
