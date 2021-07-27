package Controladores.Cliente;

import Controladores.Autenticador;
import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import MediosContacto.MailSender;
import MediosContacto.MedioDeContacto;
import MediosContacto.NotificadorPush;
import Pedidos.Direccion;
import Repositorios.RepoClientes;
import Usuarios.Cliente;
import Utils.Exceptions.ContraseniasDistintasException;
import Utils.Exceptions.DatosInvalidosException;
import Utils.Exceptions.MailNoEnviadoException;
import Utils.Exceptions.NombreOcupadoException;
import Utils.ProveedorDeNotif;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class SignupController {
    private RepoClientes repoClientes;
    Autenticador<Cliente> autenticador;
    private MedioDeContacto mailSender = new MailSender();
    private MedioDeContacto notificacionesPush = new NotificadorPush();
    String ERROR_TOKEN = "mensaje";

    public SignupController(RepoClientes repoClientes){
        this.repoClientes = repoClientes;
        autenticador = new Autenticador<Cliente>(repoClientes);
    }

    public ModelAndView getRegistroClientes(Request req, Response res) {
        String mensaje = req.cookie(ERROR_TOKEN);
        res.removeCookie(ERROR_TOKEN);
        return new ModelAndView(new Modelo(ERROR_TOKEN, mensaje), Templates.SIGNUP);
    }

    //TODO: No valide nada
    public ModelAndView registrarCliente(Request req, Response res){
        res.removeCookie(ERROR_TOKEN);

        String usuario = req.queryParams("usuario");
        String contrasenia = req.queryParams("contrasenia");
        String nombre = req.queryParams("nombre");
        String apellido= req.queryParams("apellido");
        String mail = req.queryParams("mail");
        String direccion=req.queryParams("direccion");

        try{
            validarNoNull(usuario, contrasenia, nombre, apellido, mail, direccion);
            validarContraseniasIguales(contrasenia, req.queryParams("contraseniaDuplicada"));
            Cliente nuevoCliente = new Cliente(usuario, contrasenia, nombre, apellido, mail, new Direccion(direccion));

            nuevoCliente.agregarMedioDeContacto(notificacionesPush);
            Optional.ofNullable(req.queryParams("notif_mail")).ifPresent(
                aceptado->nuevoCliente.agregarMedioDeContacto(mailSender)
            );

            nuevoCliente.notificar(ProveedorDeNotif.notificacionBienvenida(nuevoCliente));

            repoClientes.agregar(nuevoCliente);
            autenticador.guardarCredenciales(req, nuevoCliente);
            res.status(java.net.HttpURLConnection.HTTP_ACCEPTED);
            res.redirect(URIs.HOME);

        } catch (NombreOcupadoException | ContraseniasDistintasException e) {
            manejarError(res, e.getMessage());
        } catch (MailNoEnviadoException e){
            manejarError(res, "Se produjo un error al intentar comunicarnos con su cuenta de mail");
        }

        return null;
    }

    private void manejarError(Response res, String mensaje){
        res.cookie(ERROR_TOKEN, mensaje);
        res.status(HttpURLConnection.HTTP_BAD_REQUEST);
        res.redirect(URIs.SIGNUP);
    }

    private void validarNoNull(String ... args){
        List<String> recibidos = Arrays.asList(args);
        if(recibidos.stream().anyMatch(Objects::isNull) || recibidos.stream().anyMatch(String::isEmpty)){
            throw new DatosInvalidosException();
        }
    }

    private void validarContraseniasIguales(String contrasenia, String duplicada) {
        if (!contrasenia.equals(duplicada)) {
            throw new ContraseniasDistintasException();
        }
    }
}