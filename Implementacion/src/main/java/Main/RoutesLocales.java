package Main;

import Controladores.Autenticador;
import Controladores.Locales.DuenioLocalController;
import Controladores.Locales.MenuController;
import Controladores.Locales.LocalSignupController;
import Controladores.Locales.PedidosLocalController;
import Controladores.Utils.URIs;
import Local.Duenio;
import Repositorios.RepoDuenios;
import Repositorios.RepoLocales;
import spark.Service;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RoutesLocales extends RoutesTemplate{
    //Repositorios
    private static final RepoLocales repoLocales = RepoLocales.instance;
    private static final RepoDuenios repoContactos = RepoDuenios.getInstance();
    private static final Autenticador<Duenio> autenticador = new Autenticador<>(repoContactos);

    //Controladores
    private final DuenioLocalController duenioLocalController = new DuenioLocalController(autenticador, repoLocales);
    private final PedidosLocalController pedidosLocalController = new PedidosLocalController(autenticador);
    private final MenuController platosController = new MenuController(repoLocales, autenticador);

    //Spark
    private final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    public RoutesLocales(int puerto) {
        super(puerto, autenticador, new LocalSignupController(repoContactos, repoLocales));
    }

    @Override
    public void rutasPropias(Service service){
        service.get(URIs.HOME, duenioLocalController::getHomeLocal, engine);
        service.post("/local", duenioLocalController::actualizarLocal, engine);
        service.get("/pedidos", pedidosLocalController::getPedidos, engine);
        service.get("/pedidos/:nroPedido", pedidosLocalController::getPedido, engine);
        service.post("/pedidos/:nroPedido", pedidosLocalController::cambiarEstadoPedido, engine);
        service.post("/platos", platosController::agregarPlato, engine);
        service.get("/platos/nuevo", platosController::formularioCreacionPlato, engine);
        service.get("/platos/nuevo-combo", platosController::formularioCreacionCombo, engine);
        service.get("/platos/:id", platosController::getPlato, engine);
        service.post("/platos/:id/descuento", platosController::setDescuento, engine);
    }
}