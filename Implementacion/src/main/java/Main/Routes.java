package Main;

import Controladores.*;
import Controladores.Utils.URIs;
import Repositorios.*;
import Usuarios.Cliente;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.*;

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
    private final LocalController localController = new LocalController(repoLocales, autenticadorClientes);
    private final PedidosController pedidosController = new PedidosController(autenticadorClientes);
    private final DuenioLocalController duenioLocalController = new DuenioLocalController();

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
            System.out.println(request.requestMethod()+request.uri());

            if(request.uri().startsWith("/comerciantes")){

            } else if(!uriExceptuadaDeAutenticar(request.uri())){
                autenticadorClientes.reautenticar(request, response);
            }
        });

        rutasDelLocal();

        Spark.get("/signup", signupController::getRegistroClientes, engine);
        Spark.post("/clientes", signupController::registrarCliente, engine);
        Spark.get("/", loginController::getLogin, engine);
        Spark.post("/login", loginController::tryLogin, engine);
        Spark.get("/home", homeController::getHome, engine);
        Spark.get("/locales", localesController::getLocales, engine);
        Spark.get("/locales/:id", localController::getLocal, engine);
        Spark.get("/locales/:id/platos/:idPlato", localController::getPlato, engine);
        Spark.post("/locales/:idLocal/carrito", localController::agregarItem, engine);
        Spark.post("/locales/:idLocal/carrito/:item", localController::eliminarItem, engine);
        Spark.post("/pedidos", localController::finalizarPedido, engine);
        Spark.get("/pedidos", pedidosController::getPedidos, engine);
        Spark.get("/pedidos/:id", pedidosController::getPedido, engine);


        System.out.println("Servidor iniciado correctamente");
    }

    private void rutasDelLocal(){
        Spark.post("/locales", duenioLocalController::agregarLocal, engine);
        Spark.get("/locales/crear", duenioLocalController::formularioCreacionLocal, engine);
        Spark.post("/locales/:id/platos", duenioLocalController::agregarPlato, engine);
        Spark.get("/locales/:id/platos/nuevo", duenioLocalController::formularioCreacionPlato, engine);
        Spark.get("/locales/:id/platos/nuevo-combo", duenioLocalController::formularioCreacionCombo, engine);
        Spark.post("/locales/:id/platos/nuevo-combo", duenioLocalController::agregarComponenteACombo, engine);
    }

    private boolean uriExceptuadaDeAutenticar(String uri) {
        List<String> urisExceptuadas = Arrays.asList(
            URIs.LOGIN
            , "/login"
            , URIs.SIGNUP
            , "/clientes"
        );

        return urisExceptuadas.stream().anyMatch(uri::equalsIgnoreCase);
    }
}