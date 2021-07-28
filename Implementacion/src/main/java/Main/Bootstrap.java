package Main;

import Repositorios.RepoClientes;
import Repositorios.RepoContactos;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Local.Duenio;
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

        Duenio romi = ProveedorDeContactos.romina();
        RepoContactos.instance.agregar(romi);
        RepoLocales.instance.agregar(romi.getLocal());
    }
}
