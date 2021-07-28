package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.Duenio;
import Pedidos.Pedido;
import Repositorios.RepoLocales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.util.*;
import java.util.stream.Collectors;

import static Controladores.Utils.Modelos.parseModel;

public class DuenioLocalController {

    Autenticador<Duenio> autenticador;
    RepoLocales repoLocales;
    private ErrorHandler errorHandler = new ErrorHandler();

    public DuenioLocalController(Autenticador<Duenio> autenticador, RepoLocales repoLocales){
        this.autenticador = autenticador;
        this.repoLocales=repoLocales;
    }

    public ModelAndView getHomeLocal(Request req, Response res){
        Duenio duenio = autenticador.getUsuario(req);
        return new ModelAndView(parseModel(duenio.getLocal()), "home-local.html.hbs");
    }

    public ModelAndView getPedidos(Request req, Response res){
        Duenio duenio = autenticador.getUsuario(req);

        List<Modelo> pedidos = duenio.getLocal().getPedidosRecibidos()
            .stream()
            .map(Modelos::parseModel)
            .collect(Collectors.toList());

        return new ModelAndView(new Modelo("pedidos", pedidos), "pedidos-local.html.hbs");
    }

    public ModelAndView getPedido(Request request, Response response) {
        Duenio duenio = autenticador.getUsuario(request);
        try{
            int nroPedido = Integer.parseInt(request.params("nroPedido"));
            Pedido pedido = duenio.getLocal().getPedidosRecibidos().get(nroPedido);
            return new ModelAndView(parseModel(pedido), "pedido-local.html.hbs");

        } catch (NumberFormatException | IndexOutOfBoundsException e){
            response.status(HttpURLConnection.HTTP_NOT_FOUND);
            response.redirect("/pedidos");
            return null;
        }
    }
}
