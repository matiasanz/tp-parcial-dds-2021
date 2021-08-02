package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.*;
import Repositorios.RepoLocales;
import Utils.Exceptions.DatosInvalidosException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.net.URI;

import static Controladores.Utils.Modelos.*;

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

        Modelo modelo = parseModel(duenio.getLocal())
            .con("masCategorias", getCategorias());

        return new ModelAndView(modelo, "home-local.html.hbs");
    }

    public ModelAndView actualizarLocal(Request req, Response res) {
        Local local = autenticador.getUsuario(req).getLocal();
        try{
            local.setDireccion(req.queryParams("nuevaDireccion"));
            local.setCategoria(leerCategoria(req));
            res.status(200);
        } catch (DatosInvalidosException e){
            //TODO Mensaje
            res.status(HttpURLConnection.HTTP_BAD_REQUEST);
        }

        res.redirect(URIs.HOME);
        return null;
    }

    public static CategoriaLocal leerCategoria(Request req){
        try{
            return CategoriaLocal.valueOf(unparseEnum(req.queryParams("nuevaCategoria")));
        } catch (IllegalArgumentException e){
            throw new DatosInvalidosException();
        }
    }
}

