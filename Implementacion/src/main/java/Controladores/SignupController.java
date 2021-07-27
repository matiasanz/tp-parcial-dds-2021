package Controladores;

import Controladores.Utils.ErrorHandler;
import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import MediosContacto.MailSender;
import MediosContacto.MedioDeContacto;
import MediosContacto.NotificadorPush;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Usuario;
import Utils.Exceptions.ContraseniasDistintasException;
import Utils.Exceptions.DatosNulosException;
import Utils.Exceptions.MailNoEnviadoException;
import Utils.Exceptions.NombreOcupadoException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static Utils.Factory.ProveedorDeNotif.notificacionBienvenida;

public abstract class SignupController<T extends Usuario> {
    private RepoUsuarios<T> repoUsuarios;
    private Autenticador<T> autenticador;

    private ErrorHandler errorHandler = new ErrorHandler();
    private MedioDeContacto notificadorMail = new MailSender();
    private MedioDeContacto notificadorPush = new NotificadorPush();

    public SignupController(RepoUsuarios<T> repoUsuarios){
        this.repoUsuarios = repoUsuarios;
        this.autenticador = new Autenticador<>(repoUsuarios);
    }

    public ModelAndView getFormRegistro(Request req, Response res) {
        if(autenticador.sesionEnCurso(req)){
            res.redirect(URIs.HOME);
        }

        return new ModelAndView(generarModeloRegistro(req, res), Templates.SIGNUP);
    }

    protected Modelo generarModeloRegistro(Request req, Response res){
        return new Modelo("mensaje", errorHandler.getMensaje(req));
    }

    public ModelAndView registrarUsuario(Request req, Response res){
        try {
            Map<String, String> queryParams = queryParams(req);
            validarNoNull(queryParams);
            validarContraseniasIguales(queryParams);

            T nuevoUsuario = crearUsuario(queryParams);

            agregarMediosDeComunicacion(nuevoUsuario, queryParams);

            nuevoUsuario.notificar(notificacionBienvenida(nuevoUsuario));

            repoUsuarios.agregar(nuevoUsuario);

            autenticador.autenticar(req, res);

            res.status(HttpURLConnection.HTTP_ACCEPTED);
            res.redirect(URIs.HOME);

        } catch (NombreOcupadoException | ContraseniasDistintasException | DatosNulosException e) {
            manejarError(req, res, e.getMessage());
        } catch (MailNoEnviadoException e){
            manejarError(req, res, "Se produjo un error al intentar comunicarnos con su cuenta de mail. Por favor intente nuevamente");
        }

        return null;
    }

    private void agregarMediosDeComunicacion(T nuevoUsuario, Map<String, String> req) {
        nuevoUsuario.agregarMedioDeContacto(notificadorPush);
        if(aceptaMedio("notif_mail", req)){
            nuevoUsuario.agregarMedioDeContacto(notificadorMail);
        }
    }

    protected abstract T crearUsuario(Map<String, String> queryParams);

    private boolean aceptaMedio(String tokenMedioDeContacto, Map<String, String> req) {
        return req.get(tokenMedioDeContacto)!=null;
    }

    private void manejarError(Request req, Response res, String mensaje){
        errorHandler.setMensaje(req, mensaje);
        res.status(HttpURLConnection.HTTP_BAD_REQUEST);
        res.redirect(URIs.SIGNUP);
    }

    private void validarNoNull(Map<String, String> args){
        boolean hayNulos = args.entrySet().stream().allMatch(
            entry -> (entry.getValue()==null || entry.getValue().isEmpty())
                    && !entry.getKey().equals("notif_mail")
        );

        if(hayNulos){
            throw new DatosNulosException();
        }
    }

    private void validarContraseniasIguales(Map<String, String> req) {
        String duplicada = req.get("contraseniaDuplicada");

        if (!req.get("password").equals(duplicada)) {
            throw new ContraseniasDistintasException();
        }
    }

    private Map<String, String> queryParams(Request req){
        return req.queryParams().stream()
            .collect(Collectors.toMap(Function.identity(), req::queryParams));
    }
}
