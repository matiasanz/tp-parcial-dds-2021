import Local.Contacto;
import MediosContacto.MailSender;
import MediosContacto.Notificacion;
import Usuarios.Usuario;
import org.junit.Test;


public class MailSenderTest {

    MailSender notificadorMail = new MailSender();

    @Test
    public void enviaMailCorrectamente() {
        Usuario usuario = new Contacto("romimartinez5799@gmail.com","romina", null, null, null, null);
        notificadorMail.notificar(usuario, new Notificacion("Pedidos YA Testea", "te regalamos una orden de compras por 10000 pe (?"));
    }

}