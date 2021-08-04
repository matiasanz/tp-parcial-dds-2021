package Controladores.Locales;

import Controladores.Autenticador;
import Controladores.Utils.*;
import Local.Duenio;
import Local.Local;
import MediosContacto.Notificacion;
import Platos.Combo;
import Platos.Plato;
import Platos.PlatoSimple;
import Repositorios.RepoLocales;
import Utils.Exceptions.PlatoInexistenteException;
import Utils.Exceptions.PlatoRepetidoException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static Controladores.Utils.Modelos.parseModel;
import static Utils.Factory.ProveedorDeNotif.notificacionDescuento;

public class MenuController implements Transaccional {
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
            withTransaction(()->local.agregarPlato(platoSimple));
            response.redirect("/platos/"+platoSimple.getId());
        } catch (PlatoRepetidoException e){
            errorHandler.setMensaje(request, e.getMessage());
            response.redirect("/platos/nuevo");
        }

        return null;
    }

    public ModelAndView formularioCreacionPlato(Request request, Response response) {
        Long id = autenticador.getUsuario(request).getLocal().getId();
        Modelo modelo = new Modelo("id", id).con("mensaje", errorHandler.getMensaje(request));
        return new ModelAndView(modelo, "plato-nuevo-local.html.hbs");
    }

    public ModelAndView formularioCreacionCombo(Request req, Response res) {

        Local local = autenticador.getUsuario(req).getLocal();

        Modelo modelo = new Modelo("idLocal", local.getId())
            .con("platosDelLocal", local.getMenu())
            .con("mensaje", errorHandler.getMensaje(req))
        ;

        return new ModelAndView(modelo, "combo-nuevo-local.html.hbs");
    }

    public ModelAndView agregarCombo(Request req, Response res) {
        Local local = autenticador.getUsuario(req).getLocal();

        try {
            Combo nuevoCombo = comboFromRequest(req, local);

            withTransaction( () -> {
                local.agregarPlato(nuevoCombo);
            });

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

    private Combo comboFromRequest(Request req, Local local) {
        List<Plato> platos = new LinkedList<>();

        local.getMenu().forEach( plato->{

            int cantidad = Optional.of(req)
                .map( r-> r.queryParams(plato.getNombre()))
                .map(Integer::valueOf)
                .orElse(0);

            for(int i=0; i<cantidad; i++){
                platos.add(plato);
            }
        });

        return new Combo(req.queryParams("nombre"), req.queryParams("descripcion"), platos);
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

    public ModelAndView setDescuento(Request req, Response res){
        Local local = autenticador.getUsuario(req).getLocal();
        Optional<Plato> platoOp = findPlato(req, res);
        platoOp.ifPresent(
            plato -> {
                try {
                    withTransaction(()-> {
                        float descuento = new Float(req.queryParams("descuento"));

                        plato.setDescuento(descuento / 100);
                        if (descuento > 0) {
                            local.notificarSuscriptores(notificacionDescuento(descuento, plato, local));
                        }
                    });

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
