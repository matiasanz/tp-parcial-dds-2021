package Platos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("p")
public class PlatoSimple extends Plato {
    @ElementCollection
    @CollectionTable(name="IngredienteXPlato", joinColumns = @JoinColumn(name="plato"))
    @Column(name="ingrediente")
    List<String> ingredientes;
    Double precioBase;

    public PlatoSimple(){
        super();
    }

    public PlatoSimple(String nombre, String descripcion, Double precioBase,  List<String> ingredientes){
        super(nombre, descripcion);
        this.precioBase = precioBase;
        setIngredientes(ingredientes);
    }

    @Override
    public Double getPrecioBase() {
        return precioBase;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }


    public void setIngredientes(List<String> nuevosIngredientes) {
        this.ingredientes = new ArrayList<>(nuevosIngredientes);
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

}
