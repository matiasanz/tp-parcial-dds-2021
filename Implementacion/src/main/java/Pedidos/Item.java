package Pedidos;

import Platos.Plato;
import Repositorios.Templates.Identificado;

import javax.persistence.*;

import static java.util.Objects.requireNonNull;

@Entity
public class Item extends Identificado {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "plato")
    private Plato plato;
    private Integer cantidad;
    private String aclaraciones;
    private Double precioAlMomentoDeCompra;

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
        return precioAlMomentoDeCompra !=null;
    }

    public Double precioUnitario(){
        return adquirido()? precioAlMomentoDeCompra : plato.getPrecio();
    }

    public void notificarCompra(){
        this.precioAlMomentoDeCompra = plato.getPrecio();
    }

    public void setAclaraciones(String aclaraciones){
        this.aclaraciones=aclaraciones;
    }
    public void setCantidad(int cantidad){
        this.cantidad=cantidad;
    }
    private Double getPrecioAlMomentoDeCompra(){
        return precioAlMomentoDeCompra;
    }
    private void setPrecioAlMomentoDeCompra(double precioAlMomentoDeCompra){
        this.precioAlMomentoDeCompra = precioAlMomentoDeCompra;
    }
}
