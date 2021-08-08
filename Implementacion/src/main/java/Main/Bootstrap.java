package Main;

import Repositorios.RepoClientes;
import Repositorios.RepoDuenios;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Local.Duenio;
import Utils.Exceptions.NombreOcupadoException;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeDuenios;
import Utils.Factory.ProveedorDeLocales;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public static void main(String[] args) {
        new Bootstrap().run();
        System.out.println("Boostrap complete");
    }

    public void run() {
        withTransaction(()->{
            cargarUsuarios();
            cargarLocales();
        });
    }

    private void cargarLocales(){
        try{
            RepoLocales repo = RepoLocales.getInstance();
            repo.agregar(ProveedorDeLocales.cincoEsquinas());
            repo.agregar(ProveedorDeLocales.leble());
            repo.agregar(ProveedorDeLocales.mcConals());
        } catch (NombreOcupadoException e){};
    }

    private void cargarUsuarios(){
        Cliente matias = ProveedorDeClientes.matias();
        Duenio romi = ProveedorDeDuenios.romina();

        try{
            RepoClientes.getInstance().agregar(matias);
            RepoDuenios.getInstance().agregar(romi);
            RepoLocales.getInstance().agregar(romi.getLocal());
        }
        catch(NombreOcupadoException n){

        }
    }
}
