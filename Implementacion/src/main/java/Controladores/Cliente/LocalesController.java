package Controladores.Cliente;

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
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
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

        String nombre = req.queryParams("nombre");
        filtrarPorNombre(nombre, locales);

        String categoria = req.queryParams("categoria");
        filtrarPorCategorias(categoria, locales);

        if(categoria!=null){
            categorias.remove(categoria);
            categorias.add(0, categoria);
        }

        List<Modelo> modelosLocales = locales.stream().map(Modelos::parseModel).collect(Collectors.toList());

        Modelo modelo = new Modelo("Locales", modelosLocales).con("categorias", categorias).con("ultimoBuscado", nombre);

        return new ModelAndView(modelo, Templates.LOCALES);
    }

    private void filterIfPresent(String palabraNullable, List<Local> locales, BiPredicate<Local, String> filtro){
        Optional.ofNullable(palabraNullable)
            .ifPresent(palabra->locales.removeIf(l->!filtro.test(l, palabra)));
    }

    private void filtrarPorNombre(String nombre, List<Local> locales){
        filterIfPresent(nombre, locales,this::matchNombre);
    }

    private void filtrarPorCategorias(String categoria, List<Local> locales){
        filterIfPresent(categoria, locales, this::perteneceACategoria);
    }

    private boolean perteneceACategoria(Local local, String categoria){
        return categoria.equals("Todas")
            || local.getCategoria().toString().equals(unparseEnum(categoria));
    }

    private boolean matchNombre(Local local, String nombre){
        return local.getNombre().toUpperCase().contains(nombre.toUpperCase());
    }
}