package MediosContacto;

import Usuarios.Cliente;
import Usuarios.Usuario;
import Utils.Factory.ProveedorDeEncargados;
import Utils.Factory.ProveedorDeNotif;
import org.junit.Assert;

import java.util.*;
import javax.mail.*;
import javax.mail.search.SearchTerm;
import javax.naming.*;


public class MailRunner {

    public static void main(String[] args){
        Usuario usuario = ProveedorDeEncargados.encargadoConMail("romimartinez5799@gmail.com");
        new NotificadorMail().notificar(usuario, new Notificacion("Probando mails", "Si recibiste esto, es porque vamos a aprobar"));
    }

    //Esto es para recibir los mail *************************************************

    public static List<Message> getMensajesConAsunto(String asunto) throws MessagingException {
        Store store = Session
            .getInstance(getProperties(993))
            .getStore("imap");

        Folder bandejaDeEntrada = getInbox(store);

        Message[] messages = bandejaDeEntrada.search(asuntoMatcher(asunto));

        // disconnect
        bandejaDeEntrada.close(false);
        store.close();

        return Arrays.asList(messages);
    }

    private static SearchTerm asuntoMatcher(String asunto){
        return new SearchTerm() {
            public boolean match(Message message) {
                try {
                    return message.getSubject().equals(asunto);

                } catch (MessagingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

    private static Folder getInbox(Store store){
        try {
            store.connect(NotificadorMail.direccionEmail, NotificadorMail.password);

            Folder bandejaDeEntrada = store.getFolder("INBOX");
            bandejaDeEntrada.open(Folder.READ_ONLY);

            return bandejaDeEntrada;
        } catch (MessagingException e){
            throw new RuntimeException(e);
        }
    }

    private static Properties getProperties(int port){
        Properties properties = new Properties();

        // server setting
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", port);

        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port", String.valueOf(port));

        return properties;
    }
}