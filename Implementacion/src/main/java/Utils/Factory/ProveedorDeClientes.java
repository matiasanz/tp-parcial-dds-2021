package Utils.Factory;

import Local.*;
import Pedidos.Pedido;
import Usuarios.Cliente;

public class ProveedorDeClientes {

    Cliente usuario = new Cliente("romi","romi","romina","martinez","romi@gmail.com","caseros");
    Local local = new Local("farola","caseros 34", CategoriaLocal.VEGANO);
    Pedido pedido = new Pedido (890.5,"caseros 123",local,usuario);

    public static Cliente matias(){
        Cliente yo = new Cliente("matiasanz", "123", "matias", "godinez", "_@mail.com", "Mi casa");
        yo.getDireccionesConocidas().add("La casa de mi tia");
        return yo;
    }
}
