package Controladores.Cliente;

import Controladores.Utils.Transaccional;
import Controladores.Utils.Modelo;
import Controladores.Utils.Modelos;
import Controladores.Utils.Templates;
import Local.Local;
import Repositorios.RepoLocales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static Controladores.Utils.Modelos.unparseEnum;

public class LocalesController {
    RepoLocales repoLocales;
    public LocalesController(RepoLocales repoLocales){
        this.repoLocales=repoLocales;
    }

    public ModelAndView getLocales(Request req, Response res){
        List<Local> locales = repoLocales.ordenadosPorPedidos();
        List<String> categorias = Modelos.getCategorias();
        categorias.add(0, "Todas");

        Optional.ofNullable(req.queryParams("categoria"))
             .ifPresent(categoria->{
                 locales.removeIf(l -> !perteneceACategoria(l, categoria));
                 categorias.remove(categoria);
                 categorias.add(0, categoria);
             });

        List<Modelo> modelosLocales = locales.stream().map(Modelos::parseModel).collect(Collectors.toList());
        return new ModelAndView(new Modelo("Locales", modelosLocales).con("categorias", categorias), Templates.LOCALES);
    }

    private boolean perteneceACategoria(Local local, String categoria){
        return categoria.equals("Todas")
            || local.getCategoria().toString().equals(unparseEnum(categoria));
    }

}
