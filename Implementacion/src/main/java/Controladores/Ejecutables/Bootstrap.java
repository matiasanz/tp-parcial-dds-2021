package Controladores.Ejecutables;

import Repositorios.RepoClientes;
import Repositorios.RepoEncargados;
import Repositorios.RepoLocales;
import Dominio.Usuarios.Cliente;
import Dominio.Usuarios.Encargado;
import Dominio.Utils.Exceptions.NombreOcupadoException;
import Dominio.Utils.Factory.ProveedorDeClientes;
import Dominio.Utils.Factory.ProveedorDeEncargados;
import Dominio.Utils.Factory.ProveedorDeLocales;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    public static void main(String[] args) {
        try{
            new Bootstrap().run();
            System.out.println("*********** Boostrap complete ***********");
        } catch (NombreOcupadoException e){
            System.out.println("********* Bootstrap interrumpido ********");
        }
    }

    public void run() {
        withTransaction(()->{
            cargarUsuarios();
            cargarLocales();
        });
    }

    private void cargarLocales(){
        RepoLocales repo = RepoLocales.getInstance();
        repo.agregar(ProveedorDeLocales.cincoEsquinas());
        repo.agregar(ProveedorDeLocales.leble());
        repo.agregar(ProveedorDeLocales.localSinPlatos());
    }

    private void cargarUsuarios(){
        Cliente matias = ProveedorDeClientes.matias();
        Encargado romi = ProveedorDeEncargados.romina();

        RepoClientes.getInstance().agregar(matias);
        RepoEncargados.getInstance().agregar(romi);
    }
}
