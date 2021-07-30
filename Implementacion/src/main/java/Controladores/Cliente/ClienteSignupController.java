package Controladores.Cliente;

import Controladores.SignupController;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Cliente;

import java.util.*;

public class ClienteSignupController extends SignupController<Cliente> {

    public ClienteSignupController(RepoUsuarios<Cliente> repoUsuarios) {
        super(repoUsuarios);
    }

    @Override
    protected Cliente crearUsuario(Map<String, String> req) {
        return new Cliente(
            req.get("username")
            , req.get("password")
            , req.get("nombre")
            , req.get("apellido")
            , req.get("mail")
            , req.get("direccion")
        );
    }
}
