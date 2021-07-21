package Platos;

import java.math.BigDecimal;
import java.util.List;

public class Combo extends Plato {

    List<PlatoSimple> platos;

    @Override
    public BigDecimal getPrecio() {
        return platos.stream().map(Plato::getPrecio).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
