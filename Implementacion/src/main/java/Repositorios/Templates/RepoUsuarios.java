package Repositorios.Templates;

import Usuarios.Usuario;
import Utils.Exceptions.UsuarioInexistenteException;

import java.util.Optional;

public class RepoUsuarios<T extends Usuario> extends RepoMemoria<T>{
    public Optional<T> findUsuario(String username){
        return findBy(username, Usuario::getUsername);
    }

    public T getUsuario(long id){
        return find(id).orElseThrow(UsuarioInexistenteException::new);
    }

    public T getUsuario(String nombre){
        return findBy(nombre, Usuario::getUsername).orElseThrow(UsuarioInexistenteException::new);
    }
}
