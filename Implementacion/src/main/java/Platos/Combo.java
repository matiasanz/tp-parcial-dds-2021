package Platos;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Combo extends Plato {

    List<Plato> platos = new LinkedList<>();

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
        return String.join(", ", platos.stream().map(Plato::getNombre).collect(Collectors.toList()));
    }

    @Override
    public String getNombre(){
        return "Combo "+ super.getNombre();
    }
}
