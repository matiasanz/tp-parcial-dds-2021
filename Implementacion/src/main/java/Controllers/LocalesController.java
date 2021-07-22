package Controllers;

import Controllers.Utils.Modelo;
import Controllers.Utils.Templates;
import Controllers.Utils.URIs;
import Local.Local;
import Platos.Plato;
import Repositorios.RepoLocales;
import Utils.Exceptions.LocalInexistenteException;
import com.sun.org.apache.xpath.internal.operations.Mod;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Optional<Modelo> local = findLocal(req).map(this::parseModel);

        if(local.isPresent()){
            return new ModelAndView(local.orElseGet(Modelo::new), Templates.LOCAL_INDIVIDUAL);
        } else {
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.LOCALES);
            return null;
        }
    }

    private Optional<Local> findLocal(Request req){
        return repoLocales.find(Long.parseLong(req.params("id")));
    }

    private Modelo parseModel(Local local){
        return new Modelo("nombre", local.getNombre())
            .con("categorias", local.getCategorias())
            .con("platos", local.getMenu());
    }

    private boolean perteneceACategoria(Local local, String categoria){
        return local.getCategorias().stream().map(CategoriaLocal::toString).anyMatch(s->s.equals(categoria));
    }

    public ModelAndView getPlato(Request req, Response res) {
        long idLocal = Long.parseLong(req.params("id"));
        Optional<Local> local = repoLocales.find(idLocal);
        Long idPlato = Long.parseLong(req.params("idPlato"));
        Optional<Modelo> plato = local.flatMap(l->l.getPlato(idPlato)).map(this::parseModel);

        if(plato.isPresent()){
            return new ModelAndView(plato.get(), Templates.PLATO);
        } else{
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.LOCAL(idLocal));
            return null;
        }
    }

    private Modelo parseModel(Plato plato){
        return new Modelo("nombre", plato.getNombre())
            .con("precio", plato.getPrecio());
    }
}
