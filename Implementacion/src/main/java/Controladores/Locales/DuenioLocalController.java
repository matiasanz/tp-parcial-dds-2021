package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.Duenio;
import Pedidos.EstadoPedido;
import Pedidos.Pedido;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Utils.Factory.ProveedorDeNotif;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
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
        return new ModelAndView(parseModel(duenio.getLocal()), "home-local.html.hbs");
    }

    public ModelAndView getPedidos(Request req, Response res) {
        Duenio duenio = autenticador.getUsuario(req);

        return new ModelAndView(
            new Modelo("pedidos"
            , parseModel(duenio.getLocal().getPedidosRecibidos()))
            , "pedidos-local.html.hbs"
        );
    }

    public ModelAndView getPedido(Request request, Response response) {
        Duenio duenio = autenticador.getUsuario(request);

        try {
            int nroPedido = Integer.parseInt(request.params("nroPedido"));
            Pedido pedido = duenio.getLocal().getPedidosRecibidos().get(nroPedido - 1);
            return new ModelAndView(parseModel(pedido).con("numero", nroPedido), "pedido-local.html.hbs");

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            response.status(HttpURLConnection.HTTP_NOT_FOUND);
            response.redirect("/pedidos");
            return null;
        }
    }

    public ModelAndView cambiarEstadoPedido(Request req, Response response) {
        List<Pedido> pedidos = autenticador.getUsuario(req).getLocal().getPedidosRecibidos();

        getNumeroPedido(req, response).ifPresent(
            nroPedido->{
                try {
                    EstadoPedido estado = EstadoPedido.valueOf(req.queryParams("decisionPedido"));
                    pedidos.get(nroPedido - 1).setEstado(estado);
                } catch (NullPointerException | IllegalArgumentException e) {
                    response.status(HttpURLConnection.HTTP_BAD_REQUEST);
                }
                response.redirect("/pedidos/" + nroPedido);
            }
        );

        return null;
    }

    private Optional<Integer> getNumeroPedido(Request req, Response res) {
        try {
            int nroPedido = Integer.parseInt(req.params("nroPedido"));
            return Optional.of(nroPedido);
        } catch (NumberFormatException e) {

            res.status(HttpURLConnection.HTTP_BAD_REQUEST);
            res.redirect("/pedidos");
            return Optional.empty();
        }
    }
}

