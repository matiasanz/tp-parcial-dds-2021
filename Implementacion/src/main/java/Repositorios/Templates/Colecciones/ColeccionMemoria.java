package Repositorios.Templates.Colecciones;

import Repositorios.Templates.Identificable;

import java.util.LinkedList;
import java.util.List;

public class ColeccionMemoria<T extends Identificable> implements Coleccion<T>{
    private List<T> contenido = new LinkedList<>();

    public List<T> getAll(){
        return new LinkedList<>(contenido);
    }

    public void agregar(T elem){
        contenido.add(elem);
    }
}
