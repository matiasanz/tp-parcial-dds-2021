package MediosContacto;

import Usuarios.Usuario;
import Utils.Factory.ProveedorDeEncargados;
import org.junit.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MailTest {
    static NotificadorMail notificadorMail = new NotificadorMail();
    static String ASUNTO = "Pedidos YA Testea";

    @Test
    public void meEnvioUnMensajeYLoRecibo() throws MessagingException {
        Usuario usuario = ProveedorDeEncargados.encargadoConMail(NotificadorMail.direccionEmail);
        String asunto = ASUNTO + "_" + LocalDateTime.now();
        notificadorMail.notificar(usuario, new Notificacion(asunto, "Este es un mail de prueba"));

        assertEquals(1, MailRunner.getMensajesConAsunto(asunto).size());
    }
}
