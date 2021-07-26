package Repositorios;
import Local.Local;
import Repositorios.Templates.RepoMemoria;
import Utils.Exceptions.LocalInexistenteException;

import java.util.List;
import java.util.stream.Collectors;

public class RepoLocales extends RepoMemoria<Local> {
    public static RepoLocales instance = new RepoLocales();

    public Local getLocal(long id){
        return find(id).orElseThrow(()->new LocalInexistenteException(id));
    }
    public List<Local> ordenadosPorPedidos(){
        return ordenadosPor(local->local.getPedidosRecibidos().size());
    }
}
