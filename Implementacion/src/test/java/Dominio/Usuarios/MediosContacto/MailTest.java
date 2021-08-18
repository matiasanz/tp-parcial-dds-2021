package Dominio.Usuarios.MediosContacto;

import static org.junit.Assert.assertEquals;

public class MailTest {
    static NotificadorMail notificadorMail = new NotificadorMail();
    static String ASUNTO = "Dominio.Pedidos YA Testea";

    /*@Test
    public void meEnvioUnMensajeYLoRecibo() throws MessagingException {
        Usuario usuario = ProveedorDeEncargados.encargadoConMail(NotificadorMail.direccionEmail);
        String asunto = ASUNTO + "_" + LocalDateTime.now();
        notificadorMail.notificar(usuario, new Notificacion(asunto, "Este es un mail de prueba"));

        assertEquals(1, MailRunner.getMensajesConAsunto(asunto).size());
    }*/
}
