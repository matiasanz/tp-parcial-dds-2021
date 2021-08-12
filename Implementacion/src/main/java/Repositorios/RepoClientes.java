package Repositorios;
import Repositorios.Templates.Colecciones.Coleccion;
import Repositorios.Templates.Colecciones.DB;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Cliente;
import Utils.Exceptions.ClienteInexistenteException;

public class RepoClientes extends RepoUsuarios<Cliente> {
    public static RepoClientes instance;

    private RepoClientes(Coleccion<Cliente> coleccion){
        super(coleccion);
    }

    public static RepoClientes getInstance(){
        if(instance==null){
            instance = new RepoClientes(new DB<>(Cliente.class));
        }
        return instance;
    }


    public Cliente getCliente(String usuario){
        return findUsuario(usuario).orElseThrow(ClienteInexistenteException::new);
    }
}

