package Main;

import Controladores.Autenticador;
import Controladores.Locales.DuenioLocalController;
import Controladores.Locales.MenuController;
import Controladores.Locales.LocalSignupController;
import Controladores.Utils.URIs;
import Local.Duenio;
import Repositorios.RepoContactos;
import Repositorios.RepoLocales;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RoutesLocales extends RoutesTemplate{
    //Repositorios
    private static final RepoLocales repoLocales = RepoLocales.instance;
    private static final RepoContactos repoContactos = RepoContactos.instance;
    private static final Autenticador<Duenio> autenticador = new Autenticador<>(repoContactos);

    //Controladores
    private final DuenioLocalController duenioLocalController = new DuenioLocalController(autenticador, repoLocales);
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
        Spark.get("/pedidos", duenioLocalController::getPedidos, engine);
        Spark.get("/pedidos/:nroPedido", duenioLocalController::getPedido, engine);
        Spark.post("/pedidos/:nroPedido", duenioLocalController::cambiarEstadoPedido, engine);
        Spark.post("/platos", platosController::agregarPlato, engine);
        Spark.get("/platos/nuevo", platosController::formularioCreacionPlato, engine);
        Spark.get("/platos/nuevo-combo", platosController::formularioCreacionCombo, engine);
        Spark.post("/platos/nuevo-combo", platosController::agregarPlatoACombo, engine);
        Spark.get("/platos/:id", platosController::getPlato, engine);
    }
}