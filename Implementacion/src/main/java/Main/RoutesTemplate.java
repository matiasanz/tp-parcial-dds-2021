package Main;

import Controladores.Autenticador;
import Controladores.LoginController;
import Controladores.SignupController;
import Controladores.Utils.URIs;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.Service;
import java.util.Arrays;
import java.util.List;

public abstract class RoutesTemplate {
    private final int puerto;
    private final List<String> urisExceptuadasDeAutenticar = Arrays.asList(
        "/"
        , "/login"
        , URIs.SIGNUP
        , "/usuarios"
        , "/styles.css"
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

    public void execute() {
        System.out.println("Iniciando servidor");

        Service service = Service
            .ignite()
            .port(puerto);

        //Esta linea muestra el stack trace en el navegador, en caso de excepcion no manejada.
        DebugScreen.enableDebugScreen();

        service.staticFileLocation("/public");

        service.before((request, response)->{
            System.out.println(request.requestMethod()+request.uri());

            vaciarCacheHibernate();
            bloquearCacheNavegador(response);

            if(!uriExceptuadaDeAutenticar(request.uri())){
                autenticador.reautenticar(request, response);
            }
        });

        service.after((request, response)->closeEntityManager());

        /*Rutas comunes*/ {
            service.get("/finalizar", this::finalizar, engine);
            service.get(URIs.SIGNUP, signupController::getFormRegistro, engine);
            service.post(URIs.SIGNUP, signupController::registrarUsuario, engine);
            service.get("/", loginController::getLogin, engine);
            service.post("/login", loginController::tryLogin, engine);
            service.get("/logout", loginController::logout, engine);
            service.get(URIs.NOTIFICACIONES, signupController::getNotificaciones, engine);
        }

        this.rutasPropias(service);

        System.out.println("Servidor iniciado correctamente");
    }

    protected abstract void rutasPropias(Service service);

    private boolean uriExceptuadaDeAutenticar(String uri) {
        return urisExceptuadasDeAutenticar.stream().anyMatch(uri::equalsIgnoreCase);
    }

    private void bloquearCacheNavegador(Response response){
        response.header("Cache-Control", "no-store, must-revalidate");
    }

    private static void vaciarCacheHibernate(){
        PerThreadEntityManagers
            .getEntityManager()
            .getEntityManagerFactory()
            .getCache()
            .evictAll()
        ;

    }

    private static void closeEntityManager(){
        PerThreadEntityManagers.getEntityManager();
        PerThreadEntityManagers.closeEntityManager();
    }

    private ModelAndView finalizar(Request req, Response res){
        System.exit(0);
        return null;
    }
}