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
    public Combo(String nombre, String descripcion, List<Plato> platos){
        super(nombre, descripcion);

        if(nombre==null) throw new RuntimeException("Debe especificar el nombre del combo");
        if(platos.size()<2) throw new RuntimeException("Debe agregar al menos dos platos al combo");

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

    @Override
    public String getTitulo(){
        return "Combo "+ super.getTitulo();
    }

    public void setPlatos(List<Plato> platos){
        this.platos.addAll(platos);
    }
}
