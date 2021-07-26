package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.Modelo;
import Controladores.Utils.Modelos;
import Controladores.Utils.Templates;
import Local.Contacto;
import Repositorios.RepoLocales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlatosController {
    private Autenticador<Contacto> autenticador;
    private RepoLocales repoLocales;

    public PlatosController(RepoLocales repo, Autenticador<Contacto> autenticador){
        this.autenticador=autenticador;
        this.repoLocales = repo;
    }

    public ModelAndView getHomeLocal(Request req, Response res){
        Contacto duenio = autenticador.getUsuario(req);
        List<Modelo> platos = duenio.getLocal().getMenu()
            .stream()
            .map(Modelos::parseModel)
            .collect(Collectors.toList());

        return new ModelAndView(new Modelo("platos", platos), "home-local.html.hbs");
    }

    public ModelAndView getPedidos(Request req, Response res){
        Contacto duenio = autenticador.getUsuario(req);

        List<Modelo> pedidos = duenio.getLocal().getPedidosRecibidos()
            .stream()
            .map(Modelos::parseModel)
            .collect(Collectors.toList());

        return new ModelAndView(new Modelo("pedidos", pedidos), "pedidos-local.html.hbs");
    }

    public ModelAndView getLogin(Request request, Response response) {
        return new ModelAndView(null, "login.html.hbs");
    }
}
