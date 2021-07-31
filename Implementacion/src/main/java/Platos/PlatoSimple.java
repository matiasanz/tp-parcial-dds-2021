package Platos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("p")
public class PlatoSimple extends Plato {
    @ElementCollection
    @CollectionTable(name="IngredientesXPlato", joinColumns = @JoinColumn(name="plato"))
    List<String> ingredientes = new ArrayList<>(); //TODO: mostrar
    Double precio;
    String descripcion;

    public PlatoSimple(){
        super();
    }

    public PlatoSimple(String nombre, String descripcion, Double precio,  List<String> ingredientes){
        super(nombre);
        this.precio = precio;
        this.ingredientes.addAll(ingredientes);
        this.descripcion=descripcion;
    }

    @Override
    public Double getPrecioBase() {
        return precio;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    @Override
    public String getDescripcion(){
        return descripcion;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
