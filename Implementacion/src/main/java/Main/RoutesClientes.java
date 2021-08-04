package Main;

import Controladores.*;
import Controladores.Cliente.*;
import Controladores.Utils.Modelo;
import Controladores.Utils.URIs;
import MediosContacto.Notificacion;
import Repositorios.*;
import Usuarios.Cliente;
import com.google.gson.Gson;
import spark.*;
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

    public RoutesClientes(int puerto) {
        super(puerto, autenticadorClientes, signupController);
    }

    @Override
    public void rutasPropias(Service service) {
        service.get(URIs.HOME, homeController::getHome, engine);
        service.get(URIs.LOCALES, localesController::getLocales, engine);
        service.get("/locales/:id", localController::getLocal, engine);
        service.get("/locales/:id/platos/:idPlato", localController::getPlato, engine);
        service.post("/locales/:idLocal/carrito", localController::agregarItem, engine);
        service.post("/locales/:idLocal/carrito/:item", localController::eliminarItem, engine);
        service.post(URIs.PEDIDOS, localController::finalizarPedido, engine);
        service.get(URIs.PEDIDOS, pedidosController::getPedidos, engine);
        service.get("/pedidos/:id", pedidosController::getPedido, engine);
        service.post(URIs.PEDIDOS+"/:numero", pedidosController::notificarEntregaPedido, engine);
        service.post("/suscripciones", localController::suscribirmeALocal, engine);
        service.post("/suscripciones/:idLocal", localController::desuscribirmeALocal, engine);
    }
}