package Controladores;

import Controladores.Autenticador;
import Controladores.Utils.ErrorHandler;
import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Utils.Exceptions.ContraseniaIncorrectaException;
import Utils.Exceptions.UsuarioInexistenteException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;

public class LoginController {

    public LoginController(Autenticador<?> autenticador){
        this.autenticador = autenticador;
    }
    private ErrorHandler errorHandler = new ErrorHandler();

    private final Autenticador<?> autenticador;

    public ModelAndView getLogin(Request request, Response response) {
        if(autenticador.sesionEnCurso(request)){
            response.redirect(URIs.HOME);
        }

        return new ModelAndView(
            new Modelo("mensaje", errorHandler.getMensaje(request))
            , Templates.LOGIN
        );
    }

    public ModelAndView tryLogin(Request req, Response res) {
        try{
            autenticador.autenticar(req,res);
            errorHandler.notificarIntentoCorrecto(req);
            res.status(HttpURLConnection.HTTP_ACCEPTED);
            res.redirect(URIs.HOME);

        } catch(UsuarioInexistenteException | ContraseniaIncorrectaException e) {
            res.status(HttpURLConnection.HTTP_PROXY_AUTH);
            errorHandler.notificarIntentoCorrecto(req);
            errorHandler.setMensaje(req, "El usuario y/o la contraseña ingresada son incorrectos");
            res.redirect(URIs.LOGIN);
        }

        return null;
    }

    public ModelAndView logout(Request req, Response res){
        autenticador.quitarCredenciales(req, res);
        res.redirect(URIs.LOGIN);
        return null;
    }
}
