package Repositorios.Templates.Colecciones;

import Repositorios.Templates.Identificable;
import Repositorios.Templates.Identificado;

import java.util.List;

public interface Coleccion<T> {
    void agregar(T elem);
    List<T> getAll();
    void eliminar(T elem);
    void borrarTodo();
}
