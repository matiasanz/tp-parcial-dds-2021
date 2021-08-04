package Utils.Factory;

import Local.Local;
import MediosContacto.Notificacion;
import Pedidos.Cupones.Cupon;
import Pedidos.EstadoPedido;
import Platos.Plato;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Cliente;
import Usuarios.Usuario;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

public class ProveedorDeNotif {
    public static Notificacion notificacionAscensoDeCategoria(Cliente cliente, CategoriaCliente categoria){
        return new Notificacion("Ascendiste de categoria!", "Tu categoria nueva es: "+ categoria.getNombre());
    }

    public static Notificacion notificacionBienvenida(Usuario nuevoCliente) {
        return new Notificacion("Registro Usuario", String.join(" ",
            saludar(nuevoCliente)
            , "Le informamos que su usuario"
            , entreComillas(nuevoCliente.getUsername())
            , "ha sido creado correctamente. Le damos la bienvenida!")
        );
    }

    public static Notificacion notificacionResultadoPedido(Usuario usuario, EstadoPedido estado){
        String estadoPedido = StringUtils.capitalize(estado.name());

        return new Notificacion("Pedido "+estadoPedido, espaciado(
            saludar(usuario)
            , "Le informamos que su pedido ha sido"
            , estadoPedido.toLowerCase()
        ));
    }

    public static Notificacion notificacionNuevoPlato(Plato plato, Local local){
        return new Notificacion("Nuevo plato", espaciado(
            "Le informamos que el local"
            , local.getNombre()
            , "tiene un nuevo plato"
            , entreComillas(plato.getNombre())
            )
        );
    }

    private static String saludar(Usuario usuario){
        int queHoraEs = LocalDateTime.now().getHour();

        return espaciado(getSaludo(queHoraEs), usuario.getNombre()+".");
    }

    public static Notificacion notificacionDescuento(Float descuento, Plato plato, Local local) {
        return new Notificacion("Descuento", espaciado("Hay un descuento de "
            , descuento.toString(),"%", "en el local", local.getNombre(), ". ¡Aprovechalo!"));
    }

    public static Notificacion notificacionCambioDeDireccion(Local local, String direccionAnterior) {
        return new Notificacion("Cambio de direccion", espaciado(
                "El local "+local.getDireccion()
                + ", del que sos suscriptor,"
                , "ha cambiado su dirección, de"
                , direccionAnterior
                , "a"
                , local.getDireccion()
            )
        );
    }

    public static Notificacion notificacionCuponRecibido(Cliente cliente, Cupon cupon){
        return new Notificacion("Cupon Otorgado", espaciado(saludar(cliente)
            ,"Has sido beneficiado con un cupon", cupon.getDetalle()+". ¡Enhorabuena!"));
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
