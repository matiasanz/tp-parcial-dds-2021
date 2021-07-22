package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.URIs;
import Platos.Plato;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CarritoController {

    private Autenticador<Cliente> autenticadorCliente;

    public ModelAndView getCarrito(Request req, Response res){
       Cliente cliente = autenticadorCliente.getUsuario(req);
       return new ModelAndView(cliente.getCarrito(), "#TODO");
    }

    public ModelAndView agregarItem(Request request, Response response) {
/*TODO
        autenticadorCliente.getUsuario(request).getCarrito().conItem(new );
        Long idLocal = null;
        response.redirect(URIs.LOCAL(idLocal));*/
        return null;
    }
}
