package Main;

import Controladores.*;
import Controladores.Cliente.*;
import Controladores.Utils.Modelo;
import Controladores.Utils.URIs;
import MediosContacto.Notificacion;
import Repositorios.*;
import Usuarios.Cliente;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.*;

import static spark.Spark.after;

public class RoutesClientes extends RoutesTemplate{
    //Repositorios
    private static final RepoClientes repoClientes = RepoClientes.instance;
    private static final Autenticador<Cliente> autenticadorClientes = new Autenticador<>(repoClientes);
    private final RepoLocales repoLocales = RepoLocales.instance;

    //Controladores
    private static final ClienteSignupController signupController = new ClienteSignupController(repoClientes);
    private final HomeController homeController = new HomeController(autenticadorClientes, repoLocales);
    private final LocalesController localesController = new LocalesController(repoLocales);
    private final LocalController localController = new LocalController(repoLocales, autenticadorClientes);
    private final PedidosController pedidosController = new PedidosController(autenticadorClientes);

    public RoutesClientes() {
        super(8080, autenticadorClientes, signupController);
    }

    //Ejecutable
    public static void main(String[] args) {
        Bootstrap.main(args);
        new RoutesClientes().execute(args);
    }

    @Override
    public void rutasPropias() {
        Spark.get(URIs.HOME, homeController::getHome, engine);
        Spark.get(URIs.LOCALES, localesController::getLocales, engine);
        Spark.get("/locales/:id", localController::getLocal, engine);
        Spark.get("/locales/:id/platos/:idPlato", localController::getPlato, engine);
        Spark.post("/locales/:idLocal/carrito", localController::agregarItem, engine);
        Spark.post("/locales/:idLocal/carrito/:item", localController::eliminarItem, engine);
        Spark.post(URIs.PEDIDOS, localController::finalizarPedido, engine);
        Spark.get(URIs.PEDIDOS, pedidosController::getPedidos, engine);
        Spark.get("/pedidos/:id", pedidosController::getPedido, engine);
    }
}