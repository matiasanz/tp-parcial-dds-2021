package Platos;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public abstract class Plato {
    String nombre;
    List<String> fotos = new LinkedList<>();
    boolean disponible;

    public abstract BigDecimal getPrecio();
}
