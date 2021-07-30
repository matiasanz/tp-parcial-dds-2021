package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.Duenio;
import Local.Local;
import Pedidos.EstadoPedido;
import Pedidos.Pedido;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Utils.Factory.ProveedorDeNotif;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static Controladores.Utils.Modelos.parseModel;
import static Utils.Factory.ProveedorDeNotif.notificacionResultadoPedido;

public class DuenioLocalController {

    Autenticador<Duenio> autenticador;
    RepoLocales repoLocales;
    private ErrorHandler errorHandler = new ErrorHandler();

    public DuenioLocalController(Autenticador<Duenio> autenticador, RepoLocales repoLocales) {
        this.autenticador = autenticador;
        this.repoLocales = repoLocales;
    }

    public ModelAndView getHomeLocal(Request req, Response res) {
        Duenio duenio = autenticador.getUsuario(req);

        Local local = duenio.getLocal();

        return new ModelAndView(parseModel(local), "home-local.html.hbs");
    }
}

