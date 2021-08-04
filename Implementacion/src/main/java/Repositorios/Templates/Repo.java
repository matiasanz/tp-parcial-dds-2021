package Repositorios.Templates;

import Repositorios.Templates.Colecciones.Coleccion;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class Repo<T extends Identificado>{

    private Coleccion<T> contenido;

    public Repo(Coleccion<T> coleccion){
        this.contenido=coleccion;
    }

    public List<T> getAll(){
        return contenido.getAll();
    }

    public void agregar(T elemento){
        contenido.agregar(elemento);
    }

    public Optional<T> find(long id){
        return findBy(id, Identificado::getId);
    }

    protected <S> Optional<T> findBy(S valor, Function<T, S> getter){
        return findBy(elem->getter.apply(elem).equals(valor));
    }

    public Stream<T> stream(){
        return getAll().stream();
    }

    protected Optional<T> findBy(Predicate<T> predicate){
        return getAll().stream().filter(predicate).findAny();
    }

    public <U extends Comparable<U>>
    List<T> ordenadosPor(Function<T, U> keyExtractor){
        return stream().sorted(Comparator.comparing(keyExtractor).reversed()).collect(Collectors.toList());
    }
}