package MediosContacto;

import Utils.Exceptions.MailNoEnviadoException;
import Utils.Exceptions.MailNoEnviadoException;
import com.sun.activation.registries.MailcapParseException;
import Usuarios.Usuario;
import Utils.Exceptions.PendingException;

// [START simple_includes]
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// [END simple_includes]

import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MailSender implements MedioDeContacto{

    final String nombre = "Pedidos Ya";
    final String direccionEmail = "pedidos.ya.rym@gmail.com";
    final String password = "Pedidos123!!";

    @Override
    public void notificar(Usuario usuario, Notificacion mensaje) {
        Session autenticador = crearAutenticador(direccionEmail, password);

        try {
            Message msg = crearMensaje(mensaje, usuario, autenticador);
            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new MailNoEnviadoException(mensaje,usuario);
        }
    }

    private Message crearMensaje(Notificacion mensaje, Usuario destinatario, Session autenticador) throws MessagingException, UnsupportedEncodingException {
        Message msg = new MimeMessage(autenticador);
        msg.setFrom(new InternetAddress(direccionEmail, nombre));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getMail(), destinatario.getNombre()));
        msg.setSubject(mensaje.getAsunto());
        msg.setText(mensaje.getCuerpo());

        return msg;
    }

    private Session crearAutenticador(String mail, String contrasenia){
        Session sesion = Session.getDefaultInstance(getProperties(), new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, contrasenia);
            }
        });

        return sesion;
    }

    private Properties getProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

}
