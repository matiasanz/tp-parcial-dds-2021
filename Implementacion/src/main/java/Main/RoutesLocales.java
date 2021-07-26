package Main;

import Controladores.Autenticador;
import Controladores.Cliente.LoginController;
import Controladores.Locales.DuenioLocalController;
import Controladores.Locales.PlatosController;
import Controladores.Locales.SignupController;
import Local.Contacto;
import Repositorios.RepoContactos;
import Repositorios.RepoLocales;
import Repositorios.Templates.RepoMemoria;
import Repositorios.Templates.RepoUsuarios;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RoutesLocales {
    //Repositorios
    private final RepoLocales repoLocales = RepoLocales.instance;
    private final RepoContactos repoContactos = new RepoContactos();
    private final Autenticador<Contacto> autenticador = new Autenticador<>(repoContactos);

    //Controladores
    private final LoginController loginController = new LoginController(autenticador);
    private final DuenioLocalController duenioLocalController = new DuenioLocalController(autenticador, repoLocales);
    private final PlatosController platosController = new PlatosController(repoLocales, autenticador);
    private final SignupController signupController = new SignupController(repoContactos, repoLocales);
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

        Spark.get("/signup", signupController::getRegistroClientes, engine);
        Spark.post("/usuarios", signupController::registrarCliente, engine);
        Spark.get("/", platosController::getLogin, engine);
        Spark.post("/login", loginController::tryLogin, engine);
        Spark.get("/signup", duenioLocalController::formularioCreacionLocal, engine);
        Spark.get("/home", platosController::getHomeLocal, engine);
        Spark.get("/locales/:id/pedidos", platosController::getPedidos, engine);
   //     Spark.get("/locales/:id", duenioLocalController::getPedido, engine);
//        Spark.get("/locales/:id", duenioLocalController::getLocal, engine);
        Spark.get("/locales/:id/platos/nuevo", duenioLocalController::formularioCreacionPlato, engine);
        Spark.get("/locales/:id/platos/nuevo-combo", duenioLocalController::formularioCreacionCombo, engine);
        Spark.post("/locales/:id/platos/nuevo-combo", duenioLocalController::agregarPlatoACombo, engine);
        Spark.post("/locales/:id/platos", duenioLocalController::agregarPlato, engine);
//        Spark.get("/locales/:id/platos/:id", duenioLocalController::getPlato, engine);
        System.out.println("Servidor iniciado correctamente");
    }
}