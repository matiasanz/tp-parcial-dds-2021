package Dominio.Utils.Factory;

import Dominio.Local.*;
import Dominio.Usuarios.MediosContacto.NotificadorPush;
import Dominio.Usuarios.Categorias.CategoriaCliente;
import Dominio.Usuarios.Cliente;

public class ProveedorDeClientes {

    Cliente usuario = new Cliente("romi","romi","romina","martinez","romi@gmail.com","caseros");
    Local local = new Local("farola","caseros 34", CategoriaLocal.VEGANO);

    public static Cliente matias(){
        Cliente yo = new Cliente("matiasanz", "123", "matias", "godinez", "_@mail.com", "Mi casa");
        yo.getDireccionesConocidas().add("La casa de mi tia");
        yo.agregarMedioDeContacto(new NotificadorPush());
        return yo;
    }

    public static Cliente clienteConCategoria(CategoriaCliente categoria) {
        Cliente cliente = new Cliente("lucho", "ddsutn2021", "luciano", "s", "luciano@surimail.com", "lucholandia 2452");
        cliente.setCategoria(categoria);
        return cliente;
    }

    public static Cliente clienteNotificable(String nombre) {
        Cliente cliente = new Cliente(nombre, nombre, nombre, "Krycek", nombre+"@mail.com", "figorilla 2363");
        cliente.agregarMedioDeContacto(new NotificadorPush());
        return cliente;
    }
}
