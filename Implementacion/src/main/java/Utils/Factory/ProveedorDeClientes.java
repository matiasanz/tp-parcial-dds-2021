package Utils.Factory;

import MediosContacto.NotificadorPush;
import Usuarios.Cliente;

public class ProveedorDeClientes {

    public static Cliente matias(){
        Cliente yo = new Cliente("matiasanz", "123", "matias", "godinez", "_@mail.com", "Mi casa");
        yo.getDireccionesConocidas().add("La casa de mi tia");
        yo.agregarMedioDeContacto(new NotificadorPush());
        return yo;
    }
}
