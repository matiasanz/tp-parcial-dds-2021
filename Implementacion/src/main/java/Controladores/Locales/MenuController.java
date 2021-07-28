package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.Duenio;
import Local.Local;
import Platos.Combo;
import Platos.ComboBorrador;
import Platos.Plato;
import Platos.PlatoSimple;
import Repositorios.RepoLocales;
import Utils.Exceptions.NombreOcupadoException;
import Utils.Exceptions.PlatoInexistenteException;
import Utils.Exceptions.PlatoRepetidoException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Optional;

import static Controladores.Utils.Modelos.parseModel;

public class MenuController {
    private Autenticador<Duenio> autenticador;
    private ErrorHandler errorHandler = new ErrorHandler();

    public MenuController(RepoLocales repo, Autenticador<Duenio> autenticador) {
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

        try{
            local.agregarPlato(platoSimple);
            response.redirect("/platos/"+platoSimple.getId());
        } catch (PlatoRepetidoException e){
            errorHandler.setMensaje(request, e.getMessage());
            response.redirect("/platos/nuevo");
        }

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
        } catch (PlatoInexistenteException | NumberFormatException e) {
            res.status(HttpURLConnection.HTTP_BAD_REQUEST);
        }

        res.redirect(URIs.CREACION_COMBO);
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
            //TODO: Cambiar runtimeException por ComboInvalidoException o una cosa asi
            res.status(HttpURLConnection.HTTP_BAD_REQUEST);
            res.redirect(URIs.CREACION_COMBO);
        }

        return null;
    }

    public ModelAndView getPlato(Request request, Response response) {
        Optional<Plato> plato = findPlato(request, response);

        if(plato.isPresent()){
            Modelo modelo = Modelos.parseModel(plato.get());
            return new ModelAndView(modelo, "plato-local.html.hbs");
        } else{
            response.redirect(URIs.HOME);
            return null;
        }
    }

    public ModelAndView agregarDescuento(Request req, Response res){
        Optional<Plato> platoOp = findPlato(req, res);
        platoOp.ifPresent(
            plato -> {
                try {
                    plato.setDescuento(new Float(req.queryParams("descuento"))/100);
                    res.status(HttpURLConnection.HTTP_OK);
                } catch (NumberFormatException e){
                    res.status(HttpURLConnection.HTTP_BAD_REQUEST);
                }

                res.redirect("/platos/"+plato.getId());
            }
        );



        return null;
    }

    private Optional<Plato> findPlato(Request request, Response response){
        Local local = autenticador.getUsuario(request).getLocal();
        try{
            Long idPlato = Long.parseLong(request.params("id"));
            return Optional.of(local.getPlato(idPlato));

        } catch (PlatoInexistenteException e){
            response.status(HttpURLConnection.HTTP_NOT_FOUND);
        } catch (NumberFormatException e) {
            response.status(HttpURLConnection.HTTP_BAD_REQUEST);
        }

        return Optional.empty();
    }
}
