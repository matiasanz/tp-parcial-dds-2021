package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Cliente.LocalesController;
import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Local.CategoriaLocal;
import Local.Contacto;
import Local.Local;
import MediosContacto.MailSender;
import MediosContacto.MedioDeContacto;
import MediosContacto.NotificadorPush;
import Pedidos.Direccion;
import Repositorios.RepoClientes;
import Repositorios.RepoContactos;
import Repositorios.RepoLocales;
import Repositorios.Templates.RepoUsuarios;
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

//TODO: Rehacer
public class SignupController {
    private RepoContactos repoUsuarios;
    Autenticador<Contacto> autenticador;
    private RepoLocales repoLocales;
    private MedioDeContacto mailSender = new MailSender();
    private MedioDeContacto notificacionesPush = new NotificadorPush();
    String ERROR_TOKEN = "mensaje";

    public SignupController(RepoContactos repoContactos, RepoLocales repoLocales){
        this.repoUsuarios = repoContactos;
        autenticador = new Autenticador<>(repoContactos);
        this.repoLocales = repoLocales;
    }

    public ModelAndView getRegistroClientes(Request req, Response res) {
        String mensaje = req.cookie(ERROR_TOKEN);
        res.removeCookie(ERROR_TOKEN);
        return new ModelAndView(new Modelo(ERROR_TOKEN, mensaje).con("local", true).con("categorias", Arrays.asList(CategoriaLocal.values())), Templates.SIGNUP);
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
            Contacto nuevoCliente = new Contacto(usuario, contrasenia, nombre, apellido, mail, null);
            nuevoCliente.setLocal(registrarLocal(req, nuevoCliente));

            nuevoCliente.agregarMedioDeContacto(notificacionesPush);
            Optional.ofNullable(req.queryParams("notif_mail")).ifPresent(
                aceptado->nuevoCliente.agregarMedioDeContacto(mailSender)
            );

            nuevoCliente.notificar(ProveedorDeNotif.notificacionBienvenida(nuevoCliente));

            repoUsuarios.agregar(nuevoCliente);
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

    private Local registrarLocal(Request request, Contacto contacto){
        String nombre = request.queryParams("nombreLocal");
        String calle = request.queryParams("calleLocal");
        Direccion direccion = new Direccion(calle);
        CategoriaLocal categoria_id = CategoriaLocal.valueOf((request.queryParams("categoriaLocal")));
        Local local = new Local(nombre,direccion,contacto,categoria_id);
        repoLocales.agregar(local);

        return local;
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
