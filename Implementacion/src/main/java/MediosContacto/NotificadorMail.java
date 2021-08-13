package MediosContacto;

import Utils.Exceptions.MailNoEnviadoException;
import Usuarios.Usuario;

import javax.mail.MessagingException;

// [START simple_includes]
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// [END simple_includes]

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("mail")
public class NotificadorMail extends MedioDeContacto{

    protected final static String nombre = "Pedidos Ya";
    protected final static String direccionEmail = "pedidos.ya.rym@gmail.com";
    protected final static String password = "Pedidos123!!";

    @Override
    public void notificar(Usuario usuario, Notificacion mensaje) {

        Session autenticador = Session.getDefaultInstance(getProperties(), getAutenticador());

        try {
            Message msg = crearMensaje(mensaje, usuario, autenticador);
            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new MailNoEnviadoException(mensaje, usuario);
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

    protected Authenticator getAutenticador(){
        return new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(direccionEmail, password);
            }
        };
    }

    protected Properties getProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return props;
    }

}
