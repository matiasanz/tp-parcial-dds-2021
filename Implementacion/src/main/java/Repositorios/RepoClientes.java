package Repositorios;
import Repositorios.Templates.Colecciones.Coleccion;
import Repositorios.Templates.Colecciones.DB;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Cliente;
import Utils.Exceptions.ClienteInexistenteException;

public class RepoClientes extends RepoUsuarios<Cliente> {
    public static RepoClientes instance = new RepoClientes(new DB<>(Cliente.class));

    public RepoClientes(Coleccion coleccion){
        super(coleccion);
    }



    public Cliente getCliente(String usuario){
        return findUsuario(usuario).orElseThrow(ClienteInexistenteException::new);
    }
}

