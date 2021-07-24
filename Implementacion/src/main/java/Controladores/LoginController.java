package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Repositorios.RepoClientes;
import Usuarios.Cliente;
import Utils.Exceptions.ClienteInexistenteException;
import Utils.Exceptions.ContraseniaIncorrectaException;
import Utils.Exceptions.UsuarioInexistenteException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.util.Map;

public class LoginController {

    public LoginController(Autenticador<Cliente> autenticador){
        this.autenticador = autenticador;
    }

    private final Autenticador<Cliente> autenticador;
    private final String MENSAJE_TOKEN = "mensaje";


    public ModelAndView getLogin(Request request, Response response) {
        return new ModelAndView( generarModelo(request, response) , Templates.LOGIN);
    }

    //TODO: Esto se repite para todos, cambiando el LOGIN y el HOME
    public ModelAndView tryLogin(Request req, Response res) {
        try{
            autenticador.autenticar(req,res);
            res.status(HttpURLConnection.HTTP_ACCEPTED);
            res.redirect(URIs.HOME);

        } catch(UsuarioInexistenteException | ContraseniaIncorrectaException e) {
            res.status(HttpURLConnection.HTTP_PROXY_AUTH);
            res.cookie(MENSAJE_TOKEN, "El usuario y/o la contraseña ingresada son incorrectos");
            res.redirect(URIs.LOGIN);
        }

        return null;
    }

    private Map<String, Object> generarModelo(Request req, Response res){
        Modelo modelo = new Modelo(MENSAJE_TOKEN, req.cookie(MENSAJE_TOKEN));
        res.removeCookie(MENSAJE_TOKEN);
        return modelo;
    }
}
