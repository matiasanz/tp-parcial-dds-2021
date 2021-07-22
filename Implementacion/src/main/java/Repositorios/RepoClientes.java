package Repositorios;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Cliente;
import Utils.Exceptions.ClienteInexistenteException;

public class RepoClientes extends RepoUsuarios<Cliente> {
    public static RepoClientes instance = new RepoClientes();

    public Cliente getCliente(String usuario){
        return findUsuario(usuario).orElseThrow(ClienteInexistenteException::new);
    }
}

