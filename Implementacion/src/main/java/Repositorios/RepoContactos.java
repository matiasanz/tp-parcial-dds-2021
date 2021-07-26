package Repositorios;
import Local.Contacto;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Cliente;
import Utils.Exceptions.ClienteInexistenteException;

public class RepoContactos extends RepoUsuarios<Contacto> {
    public static RepoContactos instance = new RepoContactos();
}

