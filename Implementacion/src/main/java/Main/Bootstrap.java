package Main;

import Local.Local;
import Pedidos.Direccion;
import Repositorios.RepoClientes;
import Repositorios.RepoContactos;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Local.Contacto;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeContactos;
import Utils.Factory.ProveedorDeLocales;

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
        RepoLocales repo = RepoLocales.instance;
        repo.agregar(ProveedorDeLocales.cincoEsquinas());
        repo.agregar(ProveedorDeLocales.leble());
        repo.agregar(ProveedorDeLocales.mcConals());
    }

    private void cargarUsuarios(){
        Cliente matias = ProveedorDeClientes.matias();
        RepoClientes.instance.agregar(matias);

        Contacto romi = ProveedorDeContactos.romina();
        RepoContactos.instance.agregar(romi);
    }
}
