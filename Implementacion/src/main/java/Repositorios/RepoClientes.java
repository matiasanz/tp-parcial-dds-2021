package Repositorios;
import Repositorios.Templates.RepoMemoria;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Cliente;

public class RepoClientes extends RepoUsuarios<Cliente> {
    public static RepoClientes instance = new RepoClientes();
}

