package Repositorios.Templates.Colecciones;

import Repositorios.Templates.Identificable;

import java.util.List;

public interface Coleccion<T extends Identificable> {
    void agregar(T elem);
    List<T> getAll();
}
