package Platos;

import org.junit.Test;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@DiscriminatorValue("c")
public class Combo extends Plato {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="PlatoXCombo", joinColumns = @JoinColumn(name="combo"))
    List<Plato> platos = new LinkedList<>();

    public Combo() {}
    public Combo(String nombre, List<Plato> platos){
        super(nombre);
        platos.forEach(this::agregarPlato);
    }

    @Override
    public Double getPrecioBase() {
        return platos.stream().mapToDouble(Plato::getPrecio).sum();
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void agregarPlato(Plato plato){
        platos.add(plato);
    }

    public String getDescripcion(){
        return "Compuesto por "+ String.join(", ", platos.stream().map(Plato::getNombre).collect(Collectors.toList()));
    }

    @Override
    public String getTitulo(){
        return "Combo "+ super.getTitulo();
    }

    public void setPlatos(List<Plato> platos){
        this.platos.addAll(platos);
    }
}
