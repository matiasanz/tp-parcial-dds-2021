package MediosDeContacto;

import Local.Duenio;
import MediosContacto.NotificadorMail;
import MediosContacto.Notificacion;
import Usuarios.Usuario;
import org.junit.After;
import org.junit.Test;


public class MailRunner {

    static NotificadorMail notificadorMail = new NotificadorMail();

    public static void main(String[] args) {
        Usuario usuario = new Duenio("romimartinez5799@gmail.com","romina", null, null, null, null);
        notificadorMail.notificar(usuario, new Notificacion("Pedidos YA Testea", "te regalamos una orden de compras por 10000 pe (?"));
    }



}