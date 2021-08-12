package Controladores.Locales;

import Controladores.SignupController;
import Controladores.Utils.Modelo;
import Controladores.Utils.Modelos;
import Local.CategoriaLocal;
import Local.Encargado;
import Local.Local;
import Repositorios.RepoEncargados;
import Repositorios.RepoLocales;

import java.util.*;

//TODO: Rehacer
public class LocalSignupController extends SignupController<Encargado> {
    private RepoLocales repoLocales;

    public LocalSignupController(RepoEncargados repoContactos, RepoLocales repoLocales){
        super(repoContactos);
        this.repoLocales = repoLocales;
    }

    @Override
    protected Encargado crearUsuario(Map<String, String> req) {
        Local local = crearLocal(req);

        Encargado contacto = new Encargado(
            req.get("username")
            , req.get("password")
            , req.get("nombre")
            , req.get("apellido")
            , req.get("mail")
            , local
        );

        withTransaction(()->repoLocales.agregar(local));
        return contacto;
    }

    private Local crearLocal(Map<String, String> req){
        return new Local(
            req.get("nombreLocal")
            , req.get("calleLocal")
            , CategoriaLocal.valueOf(Modelos.unparseEnum(req.get("categoriaLocal"))) //TODO esto puede romper
        );
    }

    @Override
    protected Modelo modeloBase(){
        return super.modeloBase()
            .con("local", true)
            .con("categorias", Modelos.getCategorias());
    }
}