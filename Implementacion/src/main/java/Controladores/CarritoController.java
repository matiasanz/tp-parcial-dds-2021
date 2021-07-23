package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Local.Local;
import Pedidos.Carrito;
import Pedidos.Direccion;
import Pedidos.Item;
import Platos.Plato;
import Repositorios.RepoLocales;
import Usuarios.Cliente;
import Utils.Exceptions.LocalInexistenteException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

import java.net.URI;
import java.util.stream.Collectors;

public class CarritoController {

    public CarritoController(RepoLocales repoLocales, Autenticador<Cliente> autenticador){
        this.repoLocales = repoLocales;
        this.autenticadorCliente = autenticador;
    }

    private RepoLocales repoLocales;
    private Autenticador<Cliente> autenticadorCliente;

    public ModelAndView getCarrito(Request req, Response res){

        Carrito carrito = findCarrito(req, res);

        if(carrito!=null){
            return new ModelAndView(parseModel(carrito), Templates.CARRITO);
        }

        return null;
    }

    public ModelAndView agregarItem(Request request, Response response) {
        Carrito carrito = findCarrito(request, response);

        if(carrito!=null){
            Local local = carrito.getLocal();
            Plato plato = local.getPlato(Long.parseLong(request.queryParams("idPlato")));
            int cantidad = Integer.parseInt(request.queryParams("cantidad"));
            String aclaraciones = request.queryParams("aclaraciones");
            carrito.conItem(new Item(plato, cantidad, aclaraciones));
            response.redirect(URIs.CARRITO(local.getId()));
        }

        return null;
    }

    private Carrito findCarrito(Request req, Response res){
        try{
            long idLocal = Long.parseLong(req.params("idLocal"));
            Cliente cliente = autenticadorCliente.getUsuario(req);
            return cliente.getCarrito(repoLocales.getLocal(idLocal));

        } catch (NumberFormatException | LocalInexistenteException e){
            res.status(HttpURLConnection.HTTP_NOT_FOUND);
            res.redirect(URIs.HOME);
            return null;
        }
    }

    private Modelo parseModel(Carrito carrito){
        Direccion direccion = carrito.getDireccion();

        return new Modelo("local", carrito.getLocal().getNombre())
            .con("direccion", (direccion==null)? null : direccion.getCalle())
            .con("items", carrito.getItems().stream().map(this::parseModel).collect(Collectors.toList()));
    }

    private Modelo parseModel(Item item){
        return new Modelo("plato", item.getPlato().getNombre())
            .con("aclaraciones", item.getAclaraciones())
            .con("cantidad", item.getCantidad());
    }
}
