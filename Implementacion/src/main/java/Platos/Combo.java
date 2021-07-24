package Platos;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Combo extends Plato {

    List<Plato> platos = new LinkedList<>();

    public Combo(String nombre, List<Plato> platos){
        super(nombre);
        platos.forEach(this::agregarPlato);
    }

    @Override
    public BigDecimal getPrecio() {
        return platos.stream().map(Plato::getPrecio).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void agregarPlato(Plato plato){
        platos.add(plato);
    }
}
