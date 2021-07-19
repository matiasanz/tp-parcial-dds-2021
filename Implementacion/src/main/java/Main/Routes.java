package Main;

import Controllers.ControllerPrueba;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.after;

public class Routes {
    private static final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    private static ControllerPrueba clienteController = new ControllerPrueba();

    public static void main(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(8080);

        Bootstrap.main(args);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        //TODO comentar el dia de la entrega
        DebugScreen.enableDebugScreen();

        Spark.staticFileLocation("/public");

        //Descomentar la llamada al bootstrap para trabajar localmente pero no pushear al repo porque el schema no se debe crear todo el tiempo en el server
//        Bootstrap.main(args);

        Spark.get("/", clienteController::getLogin, engine);

        System.out.println("Servidor iniciado correctamente");
    }
}