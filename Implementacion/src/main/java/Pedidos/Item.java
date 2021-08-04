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
    private Double precioCompra;

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
        return precioUnitario()*cantidad;
    }

    public Boolean adquirido(){
        return precioCompra!=null;
    }

    public Double precioUnitario(){
        return adquirido()? precioCompra: plato.getPrecio();
    }

    public void notificarCompra(){
        this.precioCompra = plato.getPrecio();
    }

    public void setAclaraciones(String aclaraciones){
        this.aclaraciones=aclaraciones;
    }

    public void setCantidad(int cantidad){
        this.cantidad=cantidad;
    }
    private Double getPrecioCompra(){
        return precioCompra;
    }
    private void setPrecioCompra(double precioCompra){
        this.precioCompra=precioCompra;
    }
}
