package Platos;

import Repositorios.Templates.Identificado;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="Platos")
@DiscriminatorColumn(name="tipo_plato")
public abstract class Plato extends Identificado {
    String nombre;
    String descripcion;
    float descuento;

    public Plato(){}
    public Plato(String nombre, String descripcion){
        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public Double getPrecio(){
        return getPrecioBase()*(1.0-descuento);
    }

    public abstract Double getPrecioBase();

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String nuevaDescripcion){
        this.descripcion=nuevaDescripcion;
    }

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
