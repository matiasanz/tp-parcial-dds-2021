package Controladores;

import Controladores.Utils.URIs;
import Repositorios.RepoClientes;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Utils.Exceptions.ContraseniaIncorrectaException;
import Utils.Exceptions.NingunaSesionAbiertaException;
import Utils.Exceptions.UsuarioInexistenteException;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;

public class Autenticador<T extends Usuario> {
    private RepoUsuarios<T> repoUsuarios;

    public Autenticador(RepoUsuarios<T> repo){
        this.repoUsuarios = repo;
    }

    private String USER_ID = "uid"; //Cookie que "persiste" al usuario

    public void guardarCredenciales(Request request, T usuario)	{
        request.session().attribute(USER_ID, usuario.getId());
    }

    public void quitarCredenciales(Request request, Response response){
        request.session().removeAttribute(USER_ID);
    }

    public void autenticar(Request request, Response response){
        T usuario = repoUsuarios.getUsuario(request.queryParams("username"));
        String contrasenia = request.queryParams("password");
        if(!usuario.getPassword().equals(contrasenia))
            throw new ContraseniaIncorrectaException();

        guardarCredenciales(request, usuario);
    }


    public void reautenticar(Request pedido, Response respuesta){
        Long id = pedido.session().attribute(USER_ID);

        try{
            validarSesionEnCurso(pedido);
            repoUsuarios.getUsuario(id);
        }

        catch(NingunaSesionAbiertaException | UsuarioInexistenteException e){
            respuesta.status(HttpURLConnection.HTTP_PROXY_AUTH);
            respuesta.cookie("mensaje","Para acceder al contenido, primero debe identificarse");
            respuesta.redirect(URIs.LOGIN);
        }
    }

    public T getUsuario(Request request){
        Long id = request.session().attribute(USER_ID);
        return repoUsuarios.find(id).orElseThrow(UsuarioInexistenteException::new);
    }

    private void validarSesionEnCurso(Request pedido){
        if(!sesionEnCurso(pedido)) throw new NingunaSesionAbiertaException();
    }

    public boolean sesionEnCurso(Request request){
        Long id = request.session().attribute(USER_ID);
        return  id != null && repoUsuarios.find(id).isPresent();
    }
}
