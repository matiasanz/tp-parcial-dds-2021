package Platos;

import Repositorios.Templates.Identificable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="Platos")
@DiscriminatorColumn(name="tipo_plato")
public abstract class Plato extends Identificable {
    String nombre;
    float descuento;

    public Plato(){}
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

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getTitulo(){
        return getNombre();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
