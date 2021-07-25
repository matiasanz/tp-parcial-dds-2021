package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Local.Local;
import Pedidos.Carrito;
import Platos.Plato;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Utils.Exceptions.LocalInexistenteException;
import Utils.Exceptions.PlatoInexistenteException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Local.*;

import static Controladores.Utils.Modelos.unparseEnum;

public class LocalesController {
    RepoLocales repoLocales;
    public LocalesController(RepoLocales repoLocales){
        this.repoLocales=repoLocales;
    }

    public ModelAndView getLocales(Request req, Response res){
        List<Local> locales = repoLocales.ordenadosPorPedidos();

         Optional.ofNullable(req.queryParams("categoria"))
             .ifPresent(categoria-> locales.removeIf(l -> !perteneceACategoria(l, categoria)));

        return new ModelAndView(new Modelo("Locales", locales), Templates.LOCALES);
    }

    private boolean perteneceACategoria(Local local, String categoria){
        return local.getCategorias().stream().map(CategoriaLocal::toString).anyMatch(s->unparseEnum(categoria).equals(s));
    }
}
