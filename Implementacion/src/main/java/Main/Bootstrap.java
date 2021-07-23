package Main;

import Local.Local;
import MediosContacto.NotificadorPush;
import Pedidos.Direccion;
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
        Contacto contacto = new Contacto("jorge", "salomon", Collections.singletonList(new NotificadorPush()));
        Local local = new Local("5 esquinas", new Direccion("Calle falsa 123"), contacto, CategoriaLocal.PARRILLA);
        PlatoSimple plato =new PlatoSimple("Fideos con tuco", 900.0, Arrays.asList("fideos", "tuco"));
        local.agregarPlato(plato);
        RepoLocales.instance.agregar(local);
    }

    private void cargarUsuarios(){
        RepoClientes.instance.agregar(new Cliente("matiasanz", "123", "matias", "godinez", new Direccion("Mi casa")));
    }
}
