package Controllers;

import Controllers.Utils.Modelo;
import Controllers.Utils.URIs;
import Repositorios.RepoClientes;
import Usuarios.Cliente;
import Utils.Exceptions.ClienteInexistenteException;
import Utils.Exceptions.ContraseniaIncorrectaException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    public LoginController(RepoClientes repoClientes){
        this.repoClientes=repoClientes;
    }

    private final RepoClientes repoClientes;
    private final String ARCHIVO_LOGIN = "login.html.hbs";
    private final String MENSAJE_TOKEN = "mensaje";


    public ModelAndView getLogin(Request request, Response response) {
        return new ModelAndView( generarModelo(request, response) , ARCHIVO_LOGIN);
    }

    public ModelAndView tryLogin(Request req, Response res) {
        try{
            autenticar(req,res);
            res.status(HttpURLConnection.HTTP_ACCEPTED);
            res.redirect(URIs.HOME);

        } catch(ClienteInexistenteException | ContraseniaIncorrectaException e) {
            res.status(HttpURLConnection.HTTP_PROXY_AUTH);
            res.cookie(MENSAJE_TOKEN, "El usuario y/o la contraseña ingresada son incorrectos");
            res.redirect(URIs.LOGIN);
        }

        return null;
    }

    public void autenticar(Request request, Response response){
        Cliente cliente = repoClientes.getCliente(request.queryParams("username"));
        String contrasenia = request.queryParams("password");
        if(!cliente.getPassword().equals(contrasenia))
            throw new ContraseniaIncorrectaException();
    }

    private Map<String, Object> generarModelo(Request req, Response res){
        Modelo modelo = new Modelo(MENSAJE_TOKEN, req.cookie(MENSAJE_TOKEN));
        res.removeCookie(MENSAJE_TOKEN);
        return modelo;
    }
}
