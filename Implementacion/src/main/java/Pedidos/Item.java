package Pedidos;

import Platos.Plato;
import Repositorios.Templates.Identificado;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
public class Item extends Identificado {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plato")
    private Plato plato;
    private Integer cantidad;
    private String aclaraciones;

    public Item(){}
    public Item(Plato plato, int cantidad, String aclaraciones){
        this.plato=plato;
        requireNonNull(plato, "no aclara plato");
        this.cantidad=cantidad;
        requireNonNull(plato, "no aclara cantidad");
        this.aclaraciones= aclaraciones;
    }

    public Plato getPlato() {
        return plato;
    }

    public int getCantidad(){
        return cantidad;
    }

    public String getAclaraciones(){
        return aclaraciones;
    }

    public Double getPrecio() {
        return plato.getPrecio()*cantidad;
    }

    public void setAclaraciones(String aclaraciones){
        this.aclaraciones=aclaraciones;
    }

    public void setCantidad(int cantidad){
        this.cantidad=cantidad;
    }
}
