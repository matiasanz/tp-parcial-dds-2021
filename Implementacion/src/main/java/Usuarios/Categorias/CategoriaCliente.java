package Usuarios.Categorias;

import MediosContacto.MailSender;
import MediosContacto.Notificacion;
import MediosContacto.NotificadorPush;
import Pedidos.Pedido;
import Usuarios.Cliente;

public abstract class CategoriaCliente {
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public abstract double calcularTotal(Pedido pedido, Cliente cliente);

}
