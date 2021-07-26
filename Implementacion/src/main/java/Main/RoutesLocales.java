package Main;

import Controladores.Autenticador;
import Controladores.Locales.DuenioLocalController;
import Controladores.Locales.PlatosController;
import Local.Contacto;
import Repositorios.RepoLocales;
import Repositorios.Templates.RepoMemoria;
import Repositorios.Templates.RepoUsuarios;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RoutesLocales {
    //Repositorios
    private final RepoLocales repoLocales = RepoLocales.instance;
    private final RepoUsuarios<Contacto> repoContactos = new RepoUsuarios<>();
    private final Autenticador<Contacto> autenticador = new Autenticador<>(repoContactos);

    //Controladores
    private final DuenioLocalController duenioLocalController = new DuenioLocalController();
    private final PlatosController platosController = new PlatosController(repoLocales, autenticador);

    //Spark
    private final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    //Ejecutable
    public static void main(String[] args) {
        Bootstrap.main(args);
        new RoutesLocales().execute(args);
    }

    public void execute(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8081);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        DebugScreen.enableDebugScreen();

        Spark.staticFileLocation("/public");

        Spark.get("/", platosController::getLogin, engine);

        Spark.post("/locales", duenioLocalController::agregarLocal, engine);
        Spark.get("/home", platosController::getHomeLocal, engine);
        Spark.get("/signup", duenioLocalController::formularioCreacionLocal, engine);
        Spark.get("/locales/:id/pedidos", platosController::getPedidos, engine);
        Spark.get("/locales/:id", duenioLocalController::getPedido, engine);
//        Spark.get("/locales/:id", duenioLocalController::getLocal, engine);
        Spark.get("/locales/:id/platos/nuevo", duenioLocalController::formularioCreacionPlato, engine);
        Spark.get("/locales/:id/platos/nuevo-combo", duenioLocalController::formularioCreacionCombo, engine);
        Spark.post("/locales/:id/platos/nuevo-combo", duenioLocalController::agregarPlatoACombo, engine);
        Spark.post("/locales/:id/platos", duenioLocalController::agregarPlato, engine);
//        Spark.get("/locales/:id/platos/:id", duenioLocalController::getPlato, engine);
        System.out.println("Servidor iniciado correctamente");
    }
}