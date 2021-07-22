package Main;

import Controllers.HomeController;
import Controllers.LocalesController;
import Controllers.LoginController;
import Repositorios.RepoClientes;
import Repositorios.RepoLocales;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.after;

public class Routes {
    private RepoClientes repoClientes = RepoClientes.instance;
    private RepoLocales repoLocales = RepoLocales.instance;
    private final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    private final LoginController loginController = new LoginController(repoClientes);
    private HomeController homeController = new HomeController(repoLocales);
    private LocalesController localesController = new LocalesController(repoLocales);

    public static void main(String[] args) {
        new Routes().execute(args);
    }

    public void execute(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);

        Bootstrap.main(args);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        DebugScreen.enableDebugScreen();

        Spark.staticFileLocation("/public");

        //Descomentar la llamada al bootstrap para trabajar localmente pero no pushear al repo porque el schema no se debe crear todo el tiempo en el server
//        Bootstrap.main(args);

        Spark.get("/", loginController::getLogin, engine);
        Spark.post("/login", loginController::tryLogin, engine);
        Spark.get("/home", homeController::getHome, engine);
        Spark.get("/locales", localesController::getLocales, engine);
        Spark.get("/locales/:id", localesController::getLocal, engine);
        Spark.get("/locales/:id/platos/:idPlato", localesController::getPlato, engine);

        System.out.println("Servidor iniciado correctamente");
    }
}