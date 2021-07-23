package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Controladores.Utils.URIs;
import Pedidos.Direccion;
import Repositorios.RepoClientes;
import Usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import sun.net.www.protocol.http.HttpURLConnection;

public class SignupController {
    private RepoClientes repoClientes;
    Autenticador<Cliente> autenticador;
    String MENSAJE_TOKEN = "mensaje";

    public SignupController(RepoClientes repoClientes){
        this.repoClientes = repoClientes;
        autenticador = new Autenticador<Cliente>(repoClientes);
    }

    public ModelAndView getRegistroClientes(Request req, Response res) {
        return new ModelAndView(new Modelo(MENSAJE_TOKEN, req.cookie(MENSAJE_TOKEN)), Templates.SIGNUP);
    }

    //TODO: No valide nada
    public ModelAndView registrarCliente(Request req, Response res){
        res.removeCookie(MENSAJE_TOKEN);

        String usuario = req.queryParams("usuario");
        String contrasenia = req.queryParams("contrasenia");
        String nombre = req.queryParams("nombre");
        String apellido= req.queryParams("apellido");
        String direccion=req.queryParams("direccion");

        if(!contrasenia.equals(req.queryParams("contraseniaDuplicada"))){
            res.cookie(MENSAJE_TOKEN, "La segunda contraseña no se corresponde con la primera");
            res.status(HttpURLConnection.HTTP_BAD_REQUEST);
            res.redirect(URIs.SIGNUP);
        } else{
            Cliente nuevoCliente = new Cliente(usuario, contrasenia, nombre, apellido, new Direccion(direccion));
            repoClientes.agregar(nuevoCliente);
            autenticador.guardarCredenciales(req, nuevoCliente);

            res.status(java.net.HttpURLConnection.HTTP_ACCEPTED);
            res.redirect(URIs.HOME);
        }

        return null;
    }
}
