package Main;

import Controladores.Autenticador;
import Controladores.LoginController;
import Controladores.Locales.DuenioLocalController;
import Controladores.Locales.MenuController;
import Controladores.Locales.LocalSignupController;
import Controladores.Utils.URIs;
import Local.Contacto;
import Repositorios.RepoContactos;
import Repositorios.RepoLocales;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.List;

public class RoutesLocales {
    //Repositorios
    private final RepoLocales repoLocales = RepoLocales.instance;
    private final RepoContactos repoContactos = RepoContactos.instance;
    private final Autenticador<Contacto> autenticador = new Autenticador<>(repoContactos);

    //Controladores
    private final LoginController loginController = new LoginController(autenticador);
    private final DuenioLocalController duenioLocalController = new DuenioLocalController(autenticador, repoLocales);
    private final MenuController platosController = new MenuController(repoLocales, autenticador);
    private final LocalSignupController signupController = new LocalSignupController(repoContactos, repoLocales);
    //Spark
    private final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    //Ejecutable
    public static void main(String[] args) {
        Bootstrap.main(args);
        new RoutesLocales().execute(args);
    }

    public void execute(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        DebugScreen.enableDebugScreen();

        Spark.staticFileLocation("/public");

        Spark.before((request, response)->{
            System.out.println(request.requestMethod()+request.uri());

            if(!uriExceptuadaDeAutenticar(request.uri())){
                autenticador.reautenticar(request, response);
            }
        });

        Spark.get(URIs.SIGNUP, signupController::getFormRegistro, engine);
        Spark.post(URIs.SIGNUP, signupController::registrarUsuario, engine);
        Spark.get("/", loginController::getLogin, engine);
        Spark.post("/login", loginController::tryLogin, engine);
        Spark.get("/signup", duenioLocalController::formularioCreacionLocal, engine);
        Spark.get(URIs.HOME, duenioLocalController::getHomeLocal, engine);
        Spark.get("/pedidos", duenioLocalController::getPedidos, engine);
        Spark.get("/pedidos/:nroPedido", duenioLocalController::getPedido, engine);
        Spark.post("/platos", platosController::agregarPlato, engine);
        Spark.get("/platos/nuevo", platosController::formularioCreacionPlato, engine);
        Spark.get("/platos/nuevo-combo", platosController::formularioCreacionCombo, engine);
        Spark.post("/platos/nuevo-combo", platosController::agregarPlatoACombo, engine);
        Spark.get("/platos/:id", platosController::getPlato, engine);
        Spark.get("/logout", loginController::logout, engine);

        System.out.println("Servidor iniciado correctamente");
    }

    private boolean uriExceptuadaDeAutenticar(String uri) {
        List<String> urisExceptuadas = Arrays.asList(
            URIs.LOGIN
            , "/login"
            , URIs.SIGNUP
            , "/usuarios"
        );

        return urisExceptuadas.stream().anyMatch(uri::equalsIgnoreCase);
    }
}