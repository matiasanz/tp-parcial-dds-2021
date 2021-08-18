package Dominio.Platos;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

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
        return platos.stream().mapToDouble(Plato::getPrecioBase).sum();
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void agregarPlato(Plato plato){
        platos.add(plato);
    }

    @Override
    public String getNombre(){
        return "Combo "+ super.getNombre();
    }

    public void setPlatos(List<Plato> platos){
        this.platos.addAll(platos);
    }
}
