package Repositorios.Templates.Colecciones;

import Repositorios.Templates.Identificado;

import java.util.List;

public interface Coleccion<T extends Identificado> {
    void agregar(T elem);
    List<T> getAll();
}
