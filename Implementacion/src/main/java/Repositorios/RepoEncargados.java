package Repositorios;
import Local.Encargado;
import Repositorios.Templates.Colecciones.Coleccion;
import Repositorios.Templates.Colecciones.DB;
import Repositorios.Templates.RepoUsuarios;

public class RepoEncargados extends RepoUsuarios<Encargado> {
    public static RepoEncargados instance;

    private RepoEncargados(Coleccion<Encargado> coleccion){
        super(coleccion);
    }

    public static RepoEncargados getInstance(){
        if(instance==null){
            instance = new RepoEncargados(new DB<>(Encargado.class));
        }
        return instance;
    }
}

