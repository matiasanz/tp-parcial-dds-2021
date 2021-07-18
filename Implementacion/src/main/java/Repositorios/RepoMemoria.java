package Repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class RepoMemoria<T extends Identificable>{
    private long idAutogenerado = 0L;
    private List<T> contenido = new ArrayList<>();

    public void agregar(T elemento){
        if(elemento.getId()==null){
            elemento.setId(idAutogenerado++);
        }

        contenido.add(elemento);
    }

    public Optional<T> findById(long id){
        return contenido.stream().filter(i->i.getId()==id).findAny();
    }
}