package Utils.Factory;

import Pedidos.Direccion;
import Repositorios.RepoClientes;
import Usuarios.Cliente;

import java.util.Optional;

public class ProveedorDeClientes {

    public static Cliente matias(){
        Cliente yo = new Cliente("matiasanz", "123", "matias", "godinez", "_@mail.com", new Direccion("Mi casa"));
        yo.getDireccionesConocidas().add(new Direccion("La casa de mi tia"));
        return yo;
    }
}
