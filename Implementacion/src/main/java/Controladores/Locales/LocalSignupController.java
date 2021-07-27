package Controladores.Locales;

import Controladores.SignupControllerTemplate;
import Controladores.Utils.Modelo;
import Controladores.Utils.Modelos;
import Local.CategoriaLocal;
import Local.Contacto;
import Local.Local;
import Pedidos.Direccion;
import Repositorios.RepoContactos;
import Repositorios.RepoLocales;
import spark.Request;
import spark.Response;

import java.util.*;

//TODO: Rehacer
public class LocalSignupController extends SignupControllerTemplate<Contacto> {
    private RepoLocales repoLocales;

    public LocalSignupController(RepoContactos repoContactos, RepoLocales repoLocales){
        super(repoContactos);
        this.repoLocales = repoLocales;
    }

    @Override
    protected Contacto crearUsuario(Map<String, String> req) {
        Contacto contacto = new Contacto(
            req.get("username")
            , req.get("password")
            , req.get("nombre")
            , req.get("apellido")
            , req.get("mail")
            , null  //TODO
        );

        Local local = crearLocal(req, contacto);
        repoLocales.agregar(local);
        contacto.setLocal(local);

        return contacto;
    }

    //TODO: Raro
    private Local crearLocal(Map<String, String> req, Contacto contacto){
        Local local = new Local(
            req.get("nombreLocal")
            , new Direccion(req.get("calleLocal"))
            , contacto
            , CategoriaLocal.valueOf(Modelos.unparseEnum(req.get("categoriaLocal"))) //TODO esto puede romper
        );

        return local;
    }

    @Override
    protected Modelo generarModeloRegistro(Request req, Response res){
        return super.generarModeloRegistro(req, res)
            .con("local", true)
            .con("categorias", Modelos.getCategorias());
    }

}
