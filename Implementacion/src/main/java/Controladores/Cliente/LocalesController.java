package Controladores.Cliente;

import Controladores.Utils.Modelo;
import Controladores.Utils.Modelos;
import Controladores.Utils.Templates;
import Local.Local;
import Repositorios.RepoLocales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
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
        List<String> categorias = getCategorias();

         Optional.ofNullable(req.queryParams("categoria"))
             .ifPresent(categoria->{
                 locales.removeIf(l -> !perteneceACategoria(l, categoria));
                 categorias.remove(categoria);
                 categorias.add(0, categoria);
             });

        return new ModelAndView(new Modelo("Locales", locales).con("categorias", categorias), Templates.LOCALES);
    }

    private boolean perteneceACategoria(Local local, String categoria){
        return categoria.equals("Todas")
            || local.getCategorias().stream()
                .map(CategoriaLocal::toString)
                .anyMatch(s->unparseEnum(categoria).equals(s));
    }

    public List<String> getCategorias(){
        List<String> categorias = Arrays.stream(CategoriaLocal.values())
            .map(Modelos::parseModel)
            .collect(Collectors.toList());

        categorias.add(0, "Todas");

        return categorias;
    }
}
