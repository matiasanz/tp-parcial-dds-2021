package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.Contacto;
import Local.Local;
import Platos.Combo;
import Platos.ComboBorrador;
import Platos.Plato;
import Platos.PlatoSimple;
import Repositorios.RepoLocales;
import Utils.Exceptions.LocalInexistenteException;
import Utils.Exceptions.PlatoInexistenteException;
import com.sun.org.apache.xpath.internal.operations.Mod;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static Controladores.Utils.Modelos.parseModel;

public class MenuController {
    private Autenticador<Contacto> autenticador;
    private ErrorHandler errorHandler = new ErrorHandler();

    public MenuController(RepoLocales repo, Autenticador<Contacto> autenticador) {
        this.autenticador = autenticador;
    }

    public ModelAndView agregarPlato(Request request, Response response) {
        switch (request.queryParams("tipo")) {
            case "simple": {
                return agregarPlatoSimple(request, response);
            }
            case "combo": {
                return agregarCombo(request, response);
            }
            default: {
                response.status(HttpURLConnection.HTTP_BAD_REQUEST);
                response.redirect(URIs.LOCAL(Long.parseLong(request.queryParams("id"))));
                return null;
            }
        }
    }

    public ModelAndView agregarPlatoSimple(Request request, Response response) {
        Local local = autenticador.getUsuario(request).getLocal();

        PlatoSimple platoSimple = new PlatoSimple(
            request.queryParams("nombre")
            , request.queryParams("descripcion")
            , new Double(request.queryParams("precio"))
            , Arrays.asList(request.queryParams("ingredientes"))
        );

        local.agregarPlato(platoSimple);

        response.redirect("/platos/"+platoSimple.getId());
        return null;
    }

    public ModelAndView formularioCreacionPlato(Request request, Response response) {
        Long id = autenticador.getUsuario(request).getLocal().getId();
        return new ModelAndView(new Modelo("id", id), "plato-nuevo-local.html.hbs");
    }

    public ModelAndView formularioCreacionCombo(Request req, Response res) {

        ComboBorrador borrador = autenticador.getUsuario(req).getLocal().getBorrador();

        Modelo modelo = new Modelo("idLocal", borrador.getLocal().getId())
            .con("nombre", borrador.getNombre())
            .con("componentes", borrador.getPlatos())
            .con("platosDelLocal", borrador.getLocal().getMenu())
            .con("mensaje", errorHandler.getMensaje(req))
        ;

        return new ModelAndView(modelo, "combo-nuevo-local.html.hbs");
    }

    public ModelAndView agregarPlatoACombo(Request req, Response res) {
        Local local = autenticador.getUsuario(req).getLocal();
        ComboBorrador borrador = local.getBorrador();

        try {
            Plato plato = local.getPlato(Long.parseLong(req.queryParams("idPlato")));
            borrador.agregarPlato(plato);
            res.status(HttpURLConnection.HTTP_OK);
            res.redirect(URIs.CREACION_COMBO);

        } catch (PlatoInexistenteException | NumberFormatException e) {
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.CREACION_COMBO);
        }

        return null;
    }

    public ModelAndView agregarCombo(Request req, Response res) {
        Local local = autenticador.getUsuario(req).getLocal();

        ComboBorrador borrador = local.getBorrador();

        try {
            borrador.setNombre(req.queryParams("nombre"));
            Combo nuevoCombo = borrador.crearCombo();
            local.agregarPlato(nuevoCombo);
            local.resetBorrador();
            res.status(HttpURLConnection.HTTP_OK);
            res.redirect("/platos/"+nuevoCombo.getId());
        } catch (RuntimeException e) {
            errorHandler.setMensaje(req, e.getMessage());
            //TODO: Mostrar mensaje de error
            //TODO: Cambiar runtimeException por ComboInvalidoException o una cosa asi
            res.status(HttpURLConnection.HTTP_BAD_REQUEST);
            res.redirect(URIs.CREACION_COMBO);
        }

        return null;
    }

    public ModelAndView getPlato(Request request, Response response) {
        Local local = autenticador.getUsuario(request).getLocal();
        try{
            Long idPlato = Long.parseLong(request.params("id"));
            Modelo modelo = Modelos.parseModel(local.getPlato(idPlato));
            return new ModelAndView(modelo, "plato-local.html.hbs");

        } catch (PlatoInexistenteException e){
            response.status(HttpURLConnection.HTTP_NOT_FOUND);
        } catch (NumberFormatException e) {
            response.status(HttpURLConnection.HTTP_BAD_REQUEST);
        }

        response.redirect("/home");
        return null;
    }
}
