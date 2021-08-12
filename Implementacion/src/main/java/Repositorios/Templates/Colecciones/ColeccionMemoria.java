package Repositorios.Templates.Colecciones;

import Repositorios.Templates.Identificable;
import Repositorios.Templates.Identificado;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ColeccionMemoria<T extends Identificable> implements Coleccion<T>{
    private List<T> contenido = new LinkedList<>();

    public ColeccionMemoria(T ... elems){
        contenido.addAll(Arrays.asList(elems));
    }

    public List<T> getAll(){
        return new LinkedList<>(contenido);
    }

    public void agregar(T elem){
        contenido.add(elem);
    }

    public void eliminar(T elem){
        contenido.removeIf(elem::matchId);
    }

    public void borrarTodo(){
        this.contenido = new LinkedList<>();
    }
}
