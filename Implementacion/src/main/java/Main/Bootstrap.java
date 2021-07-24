package Main;

import Local.Local;
import MediosContacto.NotificadorPush;
import Pedidos.Carrito;
import Pedidos.Direccion;
import Pedidos.Item;
import Platos.PlatoSimple;
import Repositorios.RepoClientes;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Local.Contacto;
import Local.CategoriaLocal;

import java.util.Arrays;
import java.util.Collections;

public class Bootstrap {
    public static void main(String[] args) {
        new Bootstrap().run();
        System.out.println("Boostrap complete");
    }

    public void run() {
        cargarUsuarios();
        cargarLocales();
    }

    private void cargarLocales(){
        Contacto contacto = new Contacto("jorge", "salomon");
        contacto.agregarMedioDeContacto(new NotificadorPush());
        Local local = new Local("5 esquinas", new Direccion("Calle falsa 123"), contacto, CategoriaLocal.PARRILLA);
        PlatoSimple plato =new PlatoSimple("Fideos con tuco", 900.0, Arrays.asList("fideos", "tuco"));
        local.agregarPlato(plato);
        RepoLocales.instance.agregar(local);

        Local local2 = new Local("Leble", new Direccion("por ahi"), contacto, CategoriaLocal.DESAYUNO);
        local2.agregarPlato(plato);
        RepoLocales.instance.agregar(local2);

        new Carrito(local2).conItem(new Item(plato, 4, null)).conDireccion(new Direccion("traelo aca")).build();
    }

    private void cargarUsuarios(){
        Cliente yo = new Cliente("matiasanz", "123", "matias", "godinez", new Direccion("Mi casa"));
        yo.getDireccionesConocidas().add(new Direccion("La casa de mi tia"));
        RepoClientes.instance.agregar(yo);

    }
}
