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
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RoutesLocales extends RoutesTemplate{
    //Repositorios
    private static final RepoLocales repoLocales = RepoLocales.instance;
    private static final RepoDuenios repoContactos = RepoDuenios.instance;
    private static final Autenticador<Duenio> autenticador = new Autenticador<>(repoContactos);

    //Controladores
    private final DuenioLocalController duenioLocalController = new DuenioLocalController(autenticador, repoLocales);
    private final PedidosLocalController pedidosLocalController = new PedidosLocalController(autenticador);
    private final MenuController platosController = new MenuController(repoLocales, autenticador);

    //Spark
    private final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    public RoutesLocales() {
        super(8081, autenticador, new LocalSignupController(repoContactos, repoLocales));
    }

    //Ejecutable
    public static void main(String[] args) {
        Bootstrap.main(args);
        new RoutesLocales().execute(args);
    }

    @Override
    public void rutasPropias(){
        Spark.get(URIs.HOME, duenioLocalController::getHomeLocal, engine);
        Spark.get("/pedidos", pedidosLocalController::getPedidos, engine);
        Spark.get("/pedidos/:nroPedido", pedidosLocalController::getPedido, engine);
        Spark.post("/pedidos/:nroPedido", pedidosLocalController::cambiarEstadoPedido, engine);
        Spark.post("/platos", platosController::agregarPlato, engine);
        Spark.get("/platos/nuevo", platosController::formularioCreacionPlato, engine);
        Spark.get("/platos/nuevo-combo", platosController::formularioCreacionCombo, engine);
        Spark.post("/platos/nuevo-combo", platosController::agregarPlatoACombo, engine);
        Spark.get("/platos/:id", platosController::getPlato, engine);
        Spark.post("/platos/:id/descuento", platosController::agregarDescuento, engine);
    }
}