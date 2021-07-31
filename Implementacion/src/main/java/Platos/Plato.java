package Platos;

import Repositorios.Templates.Identificable;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public abstract class Plato extends Identificable {
    String nombre;
    float descuento;

    public Plato(String nombre){
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public Double getPrecio(){
        return getPrecioBase()*(1.0-descuento);
    }

    protected abstract Double getPrecioBase();

    public abstract String getDescripcion();

    public boolean mismoNombre(Plato plato) {
        return getNombre().equalsIgnoreCase(plato.getNombre());
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
}
