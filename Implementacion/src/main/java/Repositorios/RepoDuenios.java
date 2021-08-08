package Repositorios;
import Local.Duenio;
import Repositorios.Templates.Colecciones.Coleccion;
import Repositorios.Templates.Colecciones.DB;
import Repositorios.Templates.RepoUsuarios;
import Usuarios.Cliente;

public class RepoDuenios extends RepoUsuarios<Duenio> {
    public static RepoDuenios instance;

    private RepoDuenios(Coleccion<Duenio> coleccion){
        super(coleccion);
    }

    public static RepoDuenios getInstance(){
        if(instance==null){
            instance = new RepoDuenios(new DB<>(Duenio.class));
        }
        return instance;
    }
}

