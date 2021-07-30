package Utils.Factory;

import Usuarios.Cliente;

public class ProveedorDeClientes {

    public static Cliente matias(){
        Cliente yo = new Cliente("matiasanz", "123", "matias", "godinez", "_@mail.com", "Mi casa");
        yo.getDireccionesConocidas().add("La casa de mi tia");
        return yo;
    }
}
