package Platos;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Combo extends Plato {
    public Combo(String nombre, List<Plato> platos){
        super(nombre);
        platos.forEach(this::agregarPlato);
    }
    List<Plato> platos = new LinkedList<>();

    @Override
    public Double getPrecio() {
        return platos.stream().mapToDouble(Plato::getPrecio).sum();
    }

    public void agregarPlato(Plato plato){
        platos.add(plato);
    }
}
