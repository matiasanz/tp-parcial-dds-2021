package Utils.Factory;

import Local.*;
import MediosContacto.Notificacion;
import Pedidos.EstadoPedido;
import Platos.Plato;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Cliente;
import Usuarios.Encargado;
import Usuarios.Usuario;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public class ProveedorDeNotif {
    public static Notificacion notificacionAscensoDeCategoria(Cliente cliente, CategoriaCliente categoria){
        return new Notificacion("¡Ascendiste de categoria!", espaciado(
            saludar(cliente)
            , "Al alcanzar los"
            , Integer.toString(cliente.getPedidosRealizados().size())
            , "pedidos, has ascendido de categoría a"
            , categoria.getNombre()+"."
            , "¡Felicidades! Sigue así y recibirás más descuentos en las próximas compras"
            )
        ) ;
    }

    public static Notificacion notificacionBienvenida(Usuario nuevoCliente) {
        return new Notificacion("Registro Usuario", espaciado(
            saludar(nuevoCliente)
            , "Le informamos que su usuario"
            , entreComillas(nuevoCliente.getUsername())
            , "ha sido creado correctamente. Le damos la bienvenida a la plataforma y"
            , "le agradecemos por confiar en nuestra plataforma"
            )
        );
    }

    public static Notificacion notificacionResultadoPedido(Usuario usuario, EstadoPedido estado){
        String estadoPedido = StringUtils.capitalize(estado.name());

        return new Notificacion("Pedido "+estadoPedido.toLowerCase(), espaciado(
            saludar(usuario)
            , "Le informamos que su pedido al local ha sido"
            , estadoPedido.toLowerCase()
            )
        );
    }

    public static Notificacion notificacionNuevoPlato(Plato plato, Local local){
        return new Notificacion("Nuevo Plato", espaciado(
            "Le informamos que el local"
            , local.getNombre()
            , "ha sacado a la venta un nuevo plato:"
            , entreComillas(plato.getNombre())+"."
            , "¡Sea el primero en disfrutarlo!"
            )
        );
    }


    public static Notificacion notificacionDescuento(Float descuento, Plato plato, Local local) {
        return new Notificacion("Descuento", espaciado("Hay un descuento de "
            , descuento.toString(),"%", "en el local", local.getNombre(), ". ¡Aprovechalo!"));
    }

    public static Notificacion notificacionCambioDeDireccion(Local local, String direccionAnterior) {
        return new Notificacion("Cambio de Direccion", espaciado(

            "Le informamos que el local"
            , local.getDireccion() + ", del que ud. es suscriptor,"
            , "ha cambiado su dirección, de"
            , direccionAnterior
            , "a"
            , local.getDireccion()
            )
        );
    }

    public static Notificacion notificacionSaldoAFavor(Encargado duenio, Double saldo) {
        return new Notificacion("Saldo fin de mes"
            , espaciado(saludar(duenio)
            , "Le informamos que a la fecha ha acumulado un saldo a favor de"
            , "$"+saldo.toString()
            , "por los descuentos en sus pedidos, el cual será descontado de la cuota mensual,"
            , "cuyo monto próntamente se le hará llegar."
            , "Nuevamente, le agradecemos por confiar en nuestra plataforma")
        );
    }

    // Auxiliares **************************************************************************

    private static String saludar(Usuario usuario) {
        int queHoraEs = LocalDateTime.now().getHour();

        return espaciado(getSaludo(queHoraEs), usuario.getNombre() + ".");
    }

    private static String getSaludo(int queHoraEs){
        if(queHoraEs>=6 &&queHoraEs<=13)
            return "Buenos días";
        else
            return "Buenas "
                + (queHoraEs<=20 && queHoraEs>13? "tardes": "noches");
    }

    private static String entreComillas(String s){

        return "'"+s+"'";
    }


    private static String espaciado(String ... strings){
        return String.join(" ", strings);
    }
}
