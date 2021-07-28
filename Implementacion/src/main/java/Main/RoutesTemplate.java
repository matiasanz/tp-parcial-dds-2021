package Main;

import Controladores.Autenticador;
import Controladores.LoginController;
import Controladores.SignupController;
import Controladores.Utils.URIs;
import Usuarios.Usuario;
import spark.Response;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.List;

public abstract class RoutesTemplate {
    private final int puerto;
    private final List<String> urisExceptuadasDeAutenticar = Arrays.asList(
        "/"
        , "/login"
        , URIs.SIGNUP
        , "/usuarios"
    );

    //Repositorios
    private final Autenticador<?> autenticador;
    private final LoginController loginController;
    private final SignupController<?> signupController;

    //Spark
    protected final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    public RoutesTemplate(int puerto, Autenticador<?> autenticador, SignupController<?> signupController){
        this.puerto = puerto;
        this.autenticador=autenticador;
        this.loginController = new LoginController(autenticador);
        this.signupController = signupController;
    }

    public void execute(String[] args) {
        System.out.println("Iniciando servidor");

        Spark.port(puerto);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        DebugScreen.enableDebugScreen();

        Spark.staticFileLocation("/public");

        Spark.before((request, response)->{
            System.out.println(request.requestMethod()+request.uri());

            if(request.uri().equals("/finalizar")){
                //por las dudas
                System.exit(0);
            }

            bloquearCacheNavegador(response);

            if(!uriExceptuadaDeAutenticar(request.uri())){
                autenticador.reautenticar(request, response);
            }
        });

        /*Rutas comunes*/ {
            Spark.get(URIs.SIGNUP, signupController::getFormRegistro, engine);
            Spark.post(URIs.SIGNUP, signupController::registrarUsuario, engine);
            Spark.get("/", loginController::getLogin, engine);
            Spark.post("/login", loginController::tryLogin, engine);
            Spark.get("/logout", loginController::logout, engine);
            Spark.get(URIs.NOTIFICACIONES, signupController::getNotificaciones, engine);
        }

        this.rutasPropias();

        System.out.println("Servidor iniciado correctamente");
    }

    protected abstract void rutasPropias();

    private boolean uriExceptuadaDeAutenticar(String uri) {
        return urisExceptuadasDeAutenticar.stream().anyMatch(uri::equalsIgnoreCase);
    }

    private void bloquearCacheNavegador(Response response){
        response.header("Cache-Control", "no-store, must-revalidate");
    }
}