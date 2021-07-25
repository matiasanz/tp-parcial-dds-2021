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
import Utils.Factory.ProveedorDeClientes;

import java.util.Arrays;

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

        Local local3 = new Local("McConals", new Direccion("alla a la vuelta"), contacto, CategoriaLocal.COMIDA_RAPIDA);
        local3.agregarPlato(plato);
        RepoLocales.instance.agregar(local3);
    }

    private void cargarUsuarios(){
        Cliente yo = ProveedorDeClientes.matias();
        RepoClientes.instance.agregar(yo);
    }
}
