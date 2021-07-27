package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.CategoriaLocal;
import Local.Contacto;
import Local.Local;
import Pedidos.Direccion;
import Pedidos.Pedido;
import Platos.Combo;
import Platos.ComboBorrador;
import Platos.Plato;
import Platos.PlatoSimple;
import Repositorios.RepoLocales;
import Repositorios.Templates.RepoUsuarios;
import Utils.Exceptions.LocalInexistenteException;
import Utils.Exceptions.PlatoInexistenteException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.util.*;
import java.util.stream.Collectors;

import static Controladores.Utils.Modelos.parseModel;

public class DuenioLocalController {

    Autenticador<Contacto> autenticador;
    RepoLocales repoLocales;
    private ErrorHandler errorHandler = new ErrorHandler();

    public DuenioLocalController(Autenticador<Contacto> autenticador, RepoLocales repoLocales){
        this.autenticador = autenticador;
        this.repoLocales=repoLocales;
    }

    public ModelAndView getLogin(Request request, Response response) {
        return new ModelAndView(new Modelo("mensaje", errorHandler.getMensaje(request)), Templates.LOGIN);
    }

    public ModelAndView getHomeLocal(Request req, Response res){
        Contacto duenio = autenticador.getUsuario(req);
        return new ModelAndView(parseModel(duenio.getLocal()), "home-local.html.hbs");
    }


    public ModelAndView formularioCreacionLocal(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("categorias",(Modelos.getCategorias()));
        return new ModelAndView(model, "local-crear.html.hbs");
    }

    public ModelAndView getPedidos(Request req, Response res){
        Contacto duenio = autenticador.getUsuario(req);

        List<Modelo> pedidos = duenio.getLocal().getPedidosRecibidos()
            .stream()
            .map(Modelos::parseModel)
            .collect(Collectors.toList());

        return new ModelAndView(new Modelo("pedidos", pedidos), "pedidos-local.html.hbs");
    }

    public ModelAndView getPedido(Request request, Response response) {
        Contacto duenio = autenticador.getUsuario(request);
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
