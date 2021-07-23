package Controladores;

import Controladores.Utils.Modelo;
import Controladores.Utils.Templates;
import Repositorios.RepoClientes;
import Usuarios.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ClienteController {
    public ClienteController(RepoClientes repoClientes){
        autenticador = new Autenticador<>(repoClientes);
    }

    Autenticador<Cliente> autenticador;

    public ModelAndView getCliente(Request req, Response res){
        return new ModelAndView(autenticador.getUsuario(req), Templates.CLIENTE);
    }
}
