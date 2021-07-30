package Utils.Factory;

import Local.Local;
import MediosContacto.Notificacion;
import Pedidos.EstadoPedido;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Cliente;
import Usuarios.Usuario;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Locale;

public class ProveedorDeNotif {
    public static Notificacion notificacionAscensoDeCategoria(Cliente cliente, CategoriaCliente categoria){
        return new Notificacion("Ascendiste de categoria!", "Tu categoria nueva es: "+ categoria.getNombre());
    }

    public static Notificacion notificacionBienvenida(Usuario nuevoCliente) {
        return new Notificacion("Registro Usuario", String.join(" ",
            saludar(nuevoCliente)
            , "Le informamos que su usuario <<"
            , nuevoCliente.getUsername()
            , ">> ha sido creado correctamente. Le damos la bienvenida!")
        );
    }

    public static Notificacion notificacionResultadoPedido(Usuario usuario, EstadoPedido estado){
        String estadoPedido = StringUtils.capitalize(estado.name());

        return new Notificacion("Pedido "+estadoPedido, String.join(" "
            ,saludar(usuario)
            , "Le informamos que su pedido ha sido"
            , estadoPedido.toLowerCase()
        ));
    }

    private static String saludar(Usuario usuario){
        int queHoraEs = LocalDateTime.now().getHour();

        return getSaludo(queHoraEs)+" "+usuario.getNombre()+".";
    }

    private static String getSaludo(int queHoraEs){
        if(queHoraEs>=6 &&queHoraEs<=13)
            return "Buenos días";
        else
            return "Buenas "
                + (queHoraEs<=20 && queHoraEs>13? "tardes": "noches");
    }
}
