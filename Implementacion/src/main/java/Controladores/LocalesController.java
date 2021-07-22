package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Local.Local;
import Platos.Plato;
import Repositorios.RepoLocales;
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

    public ModelAndView getLocal(Request req, Response res){
        try{
            Local local = repoLocales.getLocal(parseId("id", req));
            return new ModelAndView(parseModel(local), Templates.LOCAL_INDIVIDUAL);

        } catch (LocalInexistenteException | NumberFormatException e) {
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.LOCALES);
            return null;
        }
    }

    private boolean perteneceACategoria(Local local, String categoria){
        return local.getCategorias().stream().map(CategoriaLocal::toString).anyMatch(s->s.equals(categoria));
    }

    public ModelAndView getPlato(Request req, Response res) {
        Long idLocal = null;
        try{
            idLocal = parseId("id", req);
            Local local = repoLocales.getLocal(idLocal);
            Plato plato = local.getPlato(parseId("idPlato", req));
            return new ModelAndView(parseModel(plato).con("idLocal", idLocal), Templates.PLATO);
        } catch ( PlatoInexistenteException | NumberFormatException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.LOCAL(idLocal));
            return null;
        }
    }

    private Modelo parseModel(Local local){
        return new Modelo("nombre", local.getNombre())
            .con("idLocal", local.getId())
            .con("categorias", local.getCategorias())
            .con("Platos", local.getMenu().stream().map(this::parseModel).collect(Collectors.toList()))
            .con("Direccion", local.getDireccion().getCalle());
    }

    private Modelo parseModel(Plato plato){
        return new Modelo("nombre", plato.getNombre())
            .con("precio", plato.getPrecio())
            .con("idPlato", plato.getId());
    }

    private Long parseId(String id, Request req){
        return Long.parseLong(req.params(id));
    }
}
