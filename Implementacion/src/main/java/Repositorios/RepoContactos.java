package Repositorios;
import Local.Duenio;
import Repositorios.Templates.RepoUsuarios;

public class RepoContactos extends RepoUsuarios<Duenio> {
    public static RepoContactos instance = new RepoContactos();
}

