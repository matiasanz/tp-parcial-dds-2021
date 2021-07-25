package Usuarios.Categorias;

import MediosContacto.MailSender;
import MediosContacto.Notificacion;
import MediosContacto.NotificadorPush;
import Pedidos.Pedido;
import Usuarios.Cliente;
import static Utils.ProveedorDeNotif.notificacionAscensoDeCategoria;

public abstract class CategoriaCliente {

    public abstract String getNombre();

    public abstract double descuentoPorCategoria(double precio, Cliente cliente);

    public abstract void notificarPedido(Pedido pedido, Cliente cliente);

    public void cambiarDeCategoria(Cliente cliente, CategoriaCliente siguiente){
        cliente.setCategoria(siguiente);
        cliente.notificar(notificacionAscensoDeCategoria(cliente, siguiente));
    }
}
