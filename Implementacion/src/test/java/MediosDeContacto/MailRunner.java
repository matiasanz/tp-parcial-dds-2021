package MediosDeContacto;

import Local.Encargado;
import MediosContacto.NotificadorMail;
import MediosContacto.Notificacion;
import Usuarios.Usuario;


public class MailRunner {

    static NotificadorMail notificadorMail = new NotificadorMail();

    public static void main(String[] args) {
        Usuario usuario = new Encargado("romimartinez5799@gmail.com","romina", null, null, null, null);
        notificadorMail.notificar(usuario, new Notificacion("Pedidos YA Testea", "te regalamos una orden de compras por 10000 pe (?"));
    }



}