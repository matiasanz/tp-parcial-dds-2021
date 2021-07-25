package Controladores;

import Controladores.Utils.URIs;
import Local.CategoriaLocal;
import Local.Contacto;
import Local.Local;
import Pedidos.Direccion;
import Platos.PlatoSimple;
import Repositorios.RepoLocales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuenioLocalController {

    public ModelAndView agregarLocal(Request request, Response response) {
        String nombre = request.queryParams("nombre");
        String calle = request.queryParams("calle");
        Direccion direccion = new Direccion(calle);
        String nombreContacto = request.queryParams("nombreContacto");
        String mail = request.queryParams("mail");
        Contacto contacto = new Contacto(mail,nombreContacto);
        CategoriaLocal categoria_id = CategoriaLocal.valueOf((request.queryParams("categoria_id")));
        Local local = new Local(nombre,direccion,contacto,categoria_id);
        RepoLocales.instance.agregar(local);

        response.redirect(URIs.LOCALES);
        return null;
    }

    public ModelAndView agregarPlato(Request request, Response response) {
        Long id = new Long(request.params("id"));
        Local local = RepoLocales.instance.getLocal(id);
        String nombre = request.queryParams("nombre");
        Double precio = new Double(request.queryParams("precio"));

        PlatoSimple platoSimple = new PlatoSimple(nombre,precio);
        local.agregarPlato(platoSimple);

        response.redirect(URIs.LOCAL(local.getId()));
        return null;
    }

    public ModelAndView formularioCreacionPlato(Request request, Response response) {
        Long id = new Long(request.params("id"));
        Local local = RepoLocales.instance.getLocal(id);
        Map<String, Object> model = new HashMap<>();
        model.put("id",id);
        return new ModelAndView(model, "plato-crear.html.hbs");
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
