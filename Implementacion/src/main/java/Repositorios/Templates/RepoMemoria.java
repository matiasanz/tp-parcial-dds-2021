package Repositorios.Templates;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class RepoMemoria<T extends Identificable>{
    private long idAutogenerado = 0L;
    private List<T> contenido = new ArrayList<>();

    public void agregar(T elemento){
        if(elemento.getId()==null){
            elemento.setId(idAutogenerado++);
        }

        contenido.add(elemento);
    }

    public List<T> getAll(){
        return contenido;
    }

    public Optional<T> find(long id){
        return findBy(id, Identificable::getId);
    }

    protected <S> Optional<T> findBy(S valor, Function<T, S> getter){
        return findBy(elem->getter.apply(elem).equals(valor));
    }

    public Stream<T> stream(){
        return getAll().stream();
    }

    protected Optional<T> findBy(Predicate<T> predicate){
        return contenido.stream().filter(predicate).findAny();
    }

    public <U extends Comparable<U>>
    List<T> ordenadosPor(Function<T, U> keyExtractor){
        return stream().sorted(Comparator.comparing(keyExtractor).reversed()).collect(Collectors.toList());
    }
}