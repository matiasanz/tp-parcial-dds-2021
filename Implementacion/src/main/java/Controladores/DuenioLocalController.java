package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.URIs;
import Local.CategoriaLocal;
import Local.Contacto;
import Local.Local;
import Pedidos.Direccion;
import Platos.Combo;
import Platos.ComboBorrador;
import Platos.Plato;
import Platos.PlatoSimple;
import Repositorios.RepoLocales;
import Utils.Exceptions.LocalInexistenteException;
import Utils.Exceptions.PlatoInexistenteException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.net.HttpURLConnection;
import java.util.*;

public class DuenioLocalController {

    RepoLocales repoLocales = RepoLocales.instance;

    public ModelAndView agregarLocal(Request request, Response response) {
        String nombre = request.queryParams("nombre");
        String calle = request.queryParams("calle");
        Direccion direccion = new Direccion(calle);
        String nombreContacto = request.queryParams("nombreContacto");
        String mail = request.queryParams("mail");
        Contacto contacto = new Contacto(mail,nombreContacto);
        CategoriaLocal categoria_id = CategoriaLocal.valueOf((request.queryParams("categoria_id")));
        Local local = new Local(nombre,direccion,contacto,categoria_id);
        repoLocales.agregar(local);

        response.redirect(URIs.LOCALES);
        return null;
    }

    public ModelAndView agregarPlato(Request request, Response response){
        switch(request.queryParams("tipo")){
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
        Long id = new Long(request.params("id"));
        Local local = repoLocales.getLocal(id);
        String nombre = request.queryParams("nombre");
        Double precio = new Double(request.queryParams("precio"));
        List<String> ingredientes = Collections.singletonList(request.queryParams("ingredientes"));

        PlatoSimple platoSimple = new PlatoSimple(nombre,precio,ingredientes);
        local.agregarPlato(platoSimple);

        response.redirect(URIs.PLATO(platoSimple.getId(), local.getId()));
        return null;
    }

    public ModelAndView formularioCreacionPlato(Request request, Response response) {
        Long id = new Long(request.params("id"));
        Local local = repoLocales.getLocal(id);
        Map<String, Object> model = new HashMap<>();
        model.put("id",id);
        return new ModelAndView(model, "plato-crear.html.hbs");
    }

    private Optional<ComboBorrador> getBorrador(Request req, Response res){
        try{
            long idLocal = Long.parseLong(req.params("id"));
            Local local = repoLocales.getLocal(idLocal);
            return Optional.of(local.getBorrador());
        } catch (LocalInexistenteException | NumberFormatException e){
            res.redirect(URIs.LOCALES);
            return Optional.empty();
        }
    }

    public ModelAndView formularioCreacionCombo(Request req, Response res){
        Optional<ComboBorrador> comboBorrador = getBorrador(req, res);

        if(!comboBorrador.isPresent()){
            return null;
        }

        ComboBorrador borrador = comboBorrador.get();

        Modelo modelo = new Modelo("idLocal", borrador.getLocal().getId())
            .con("nombre", borrador.getNombre())
            .con("componentes", borrador.getPlatos())
            .con("platosDelLocal", borrador.getLocal().getMenu())
        ;

        return new ModelAndView(modelo, "combo-crear.html.hbs");
    }

    public ModelAndView agregarComponenteACombo(Request req, Response res){
        Optional<ComboBorrador> comboBorrador = getBorrador(req, res);
        comboBorrador.ifPresent(
            borrador -> {
                Local local =borrador.getLocal();

                try{
                    Plato plato = local.getPlato(Long.parseLong(req.queryParams("idPlato")));
                    borrador.agregarPlato(plato);
                    res.status(HttpURLConnection.HTTP_OK);
                    res.redirect(URIs.CREACION_COMBO(local.getId()));

                } catch (PlatoInexistenteException | NumberFormatException e){
                    res.status(HttpURLConnection.HTTP_NOT_FOUND);
                    res.redirect(URIs.CREACION_COMBO(local.getId()));
                }
            }
        );

        return null;
    }

    public ModelAndView agregarCombo(Request req, Response res){
        Optional<ComboBorrador> comboBorrador = getBorrador(req, res);

        comboBorrador.ifPresent(
            borrador ->{
                Local local = borrador.getLocal();
                try{
                    System.out.println(req.queryParams("nombre"));
                    borrador.setNombre(req.queryParams("nombre"));
                    Combo nuevoCombo = borrador.crearCombo();
                    local.agregarPlato(nuevoCombo);
                    local.resetBorrador();
                    res.status(HttpURLConnection.HTTP_OK);
                    res.redirect(URIs.PLATO(nuevoCombo.getId(), local.getId()));
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    //TODO: Mostrar mensaje de error
                    //TODO: Cambiar runtimeException por ComboInvalidoException o una cosa asi
                    res.status(HttpURLConnection.HTTP_BAD_REQUEST);
                    res.redirect(URIs.CREACION_COMBO(local.getId()));
                }
            }
        );

        return null;

    }

    public ModelAndView formularioCreacionLocal(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("categorias",(getCategorias()));
        return new ModelAndView(model, "local-crear.html.hbs");
    }

    public List<CategoriaLocal> getCategorias(){
        return Arrays.asList(CategoriaLocal.values());
    }
}
