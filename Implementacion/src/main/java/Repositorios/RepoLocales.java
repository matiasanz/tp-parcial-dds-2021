package Repositorios;
import Local.Local;
import Repositorios.Templates.Colecciones.Coleccion;
import Repositorios.Templates.Colecciones.ColeccionMemoria;
import Repositorios.Templates.Colecciones.DB;
import Repositorios.Templates.Repo;
import Utils.Exceptions.LocalInexistenteException;
import Utils.Exceptions.NombreOcupadoException;

import java.util.List;

public class RepoLocales extends Repo<Local> {
    public static RepoLocales instance = new RepoLocales(new DB<>(Local.class));

    public RepoLocales(Coleccion<Local> coleccion) {
        super(coleccion);
    }

    public Local getLocal(long id){
        return find(id).orElseThrow(()->new LocalInexistenteException(id));
    }
    public List<Local> ordenadosPorPedidos(){
        return ordenadosPor(local->local.getPedidosRecibidos().size());
    }

    @Override
    public void agregar(Local nuevoLocal){
        String nombre = nuevoLocal.getNombre();
        if(nombreOcupado(nombre)){
            throw new NombreOcupadoException("local", nombre);
        }

        super.agregar(nuevoLocal);
    }

    public boolean nombreOcupado(String nombre){
        return stream().anyMatch(u->u.getNombre().equals(nombre));
    }
}
