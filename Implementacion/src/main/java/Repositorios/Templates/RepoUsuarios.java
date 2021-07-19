package Repositorios.Templates;

import Usuarios.Usuario;

import java.util.Optional;

public class RepoUsuarios<T extends Usuario> extends RepoMemoria<T>{
    public Optional<T> get(String username){
        return findBy(username, Usuario::getUsername);
    }
}
