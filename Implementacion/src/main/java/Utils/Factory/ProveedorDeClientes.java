package Utils.Factory;

import Local.*;
import Pedidos.Pedido;
import MediosContacto.NotificadorPush;
import Usuarios.Cliente;

public class ProveedorDeClientes {

    Cliente usuario = new Cliente("romi","romi","romina","martinez","romi@gmail.com","caseros");
    Local local = new Local("farola","caseros 34", CategoriaLocal.VEGANO);

    public static Cliente matias(){
        Cliente yo = new Cliente("matiasanz", "123", "matias", "godinez", "_@mail.com", "Mi casa");
        yo.getDireccionesConocidas().add("La casa de mi tia");
        yo.agregarMedioDeContacto(new NotificadorPush());
        return yo;
    }
}
