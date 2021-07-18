package Platos;

import java.util.List;

public class Combo extends Plato {

    List<PlatoSimple> platos;

    @Override
    public double getPrecio() {
        return platos.stream().mapToDouble(Plato::getPrecio).sum();
    }
}
