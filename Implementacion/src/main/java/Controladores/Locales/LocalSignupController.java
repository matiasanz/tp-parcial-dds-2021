package Controladores.Locales;

import Controladores.SignupController;
import Controladores.Utils.Modelo;
import Controladores.Utils.Modelos;
import Local.CategoriaLocal;
import Local.Duenio;
import Local.Local;
import Repositorios.RepoContactos;
import Repositorios.RepoLocales;
import spark.Request;
import spark.Response;

import java.util.*;

//TODO: Rehacer
public class LocalSignupController extends SignupController<Duenio> {
    private RepoLocales repoLocales;

    public LocalSignupController(RepoContactos repoContactos, RepoLocales repoLocales){
        super(repoContactos);
        this.repoLocales = repoLocales;
    }

    @Override
    protected Duenio crearUsuario(Map<String, String> req) {
        Local local = crearLocal(req);

        Duenio contacto = new Duenio(
            req.get("username")
            , req.get("password")
            , req.get("nombre")
            , req.get("apellido")
            , req.get("mail")
            , local
        );

        repoLocales.agregar(local);
        return contacto;
    }

    private Local crearLocal(Map<String, String> req){
        Local local = new Local(
            req.get("nombreLocal")
            , req.get("calleLocal")
            , CategoriaLocal.valueOf(Modelos.unparseEnum(req.get("categoriaLocal"))) //TODO esto puede romper
        );

        return local;
    }

    @Override
    protected Modelo modeloBase(){
        return super.modeloBase()
            .con("local", true)
            .con("categorias", Modelos.getCategorias());
    }
}