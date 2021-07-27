package Main;

import Controladores.*;
import Controladores.Cliente.*;
import Controladores.Locales.DuenioLocalController;
import Controladores.Utils.URIs;
import Repositorios.*;
import Usuarios.Cliente;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.*;

import static spark.Spark.after;

public class RoutesClientes {
    //Repositorios
    private final RepoClientes repoClientes = RepoClientes.instance;
    private final Autenticador<Cliente> autenticadorClientes = new Autenticador<>(repoClientes);
    private final RepoLocales repoLocales = RepoLocales.instance;

    //Controladores
    private final ClienteSignupController signupController = new ClienteSignupController(repoClientes);
    private final LoginController loginController = new LoginController(autenticadorClientes);
    private final HomeController homeController = new HomeController(autenticadorClientes, repoLocales);
    private final LocalesController localesController = new LocalesController(repoLocales);
    private final LocalController localController = new LocalController(repoLocales, autenticadorClientes);
    private final PedidosController pedidosController = new PedidosController(autenticadorClientes);

    //Spark
    private final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    //Ejecutable
    public static void main(String[] args) {
        Bootstrap.main(args);
        new RoutesClientes().execute(args);
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
                autenticadorClientes.reautenticar(request, response);
            }
        });

        Spark.get(URIs.SIGNUP, signupController::getFormRegistro, engine);
        Spark.post(URIs.SIGNUP, signupController::registrarUsuario, engine);
        Spark.get("/", loginController::getLogin, engine);
        Spark.post("/login", loginController::tryLogin, engine);
        Spark.get(URIs.HOME, homeController::getHome, engine);
        Spark.get(URIs.LOCALES, localesController::getLocales, engine);
        Spark.get("/locales/:id", localController::getLocal, engine);
        Spark.get("/locales/:id/platos/:idPlato", localController::getPlato, engine);
        Spark.post("/locales/:idLocal/carrito", localController::agregarItem, engine);
        Spark.post("/locales/:idLocal/carrito/:item", localController::eliminarItem, engine);
        Spark.post(URIs.PEDIDOS, localController::finalizarPedido, engine);
        Spark.get(URIs.PEDIDOS, pedidosController::getPedidos, engine);
        Spark.get("/pedidos/:id", pedidosController::getPedido, engine);

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