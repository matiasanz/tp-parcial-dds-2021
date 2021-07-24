package Utils;

import MediosContacto.Notificacion;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Cliente;

//Notificacion notificacion = new Notificacion("SUBISTE DE CATEGORIA!", "chequea los nuevos beneficios");
public class ProveedorDeNotif {
    public static Notificacion notificacionAscensoDeCategoria(Cliente cliente, CategoriaCliente categoria){
        return new Notificacion("Ascendiste de categoria!", "Tu categoria nueva es: "+ categoria.getNombre());
    }
}
