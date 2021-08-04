package Repositorios;
import Local.Duenio;
import Repositorios.Templates.Colecciones.Coleccion;
import Repositorios.Templates.Colecciones.DB;
import Repositorios.Templates.RepoUsuarios;

public class RepoDuenios extends RepoUsuarios<Duenio> {
    public static RepoDuenios instance = new RepoDuenios(new DB<>(Duenio.class));

    public RepoDuenios(Coleccion<Duenio> coleccion){
        super(coleccion);
    }
}

