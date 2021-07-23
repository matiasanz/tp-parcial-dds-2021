package Main;

import Controladores.*;
import Controladores.Utils.URIs;
import Repositorios.RepoClientes;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.after;

public class Routes {
    //Repositorios
    private final RepoClientes repoClientes = RepoClientes.instance;
    private final Autenticador<Cliente> autenticadorClientes = new Autenticador<>(repoClientes);
    private final RepoLocales repoLocales = RepoLocales.instance;

    //Controladores
    private final SignupController signupController = new SignupController(repoClientes);
    private final LoginController loginController = new LoginController(autenticadorClientes);
    private final HomeController homeController = new HomeController(autenticadorClientes, repoLocales);
    private final LocalesController localesController = new LocalesController(repoLocales);
    private final CarritoController carritoController = new CarritoController();

    //Spark
    private final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    //Ejecutable
    public static void main(String[] args) {
        Bootstrap.main(args);
        new Routes().execute(args);
    }

    public void execute(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        DebugScreen.enableDebugScreen();

        Spark.staticFileLocation("/public");

        Spark.before((request, response)->{
            if(uriExceptuadaDeAutenticar(request.uri())){
                autenticadorClientes.reautenticar(request, response);
            }
        });

        Spark.get("/signup", signupController::getRegistroClientes, engine);
        Spark.post("/clientes", signupController::registrarCliente, engine);
        Spark.get("/", loginController::getLogin, engine);
        Spark.post("/login", loginController::tryLogin, engine);
        Spark.get("/home", homeController::getHome, engine);
        Spark.get("/locales", localesController::getLocales, engine);
        Spark.get("/locales/:id", localesController::getLocal, engine);
        Spark.get("/locales/:id/platos/:idPlato", localesController::getPlato, engine);
        Spark.post("/carrito", carritoController::agregarItem, engine);

        System.out.println("Servidor iniciado correctamente");
    }

    private boolean uriExceptuadaDeAutenticar(String uri) {
        List<String> urisExceptuadas = Arrays.asList(
            URIs.LOGIN
            , "/login"
            , URIs.SIGNUP
            , "/clientes"
        );

        return urisExceptuadas.stream().noneMatch(uri::equalsIgnoreCase);
    }
}