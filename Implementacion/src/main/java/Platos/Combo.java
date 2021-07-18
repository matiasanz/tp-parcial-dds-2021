package Platos;

import java.util.List;

public class Combo implements Plato{
    String nombre;
    List<PlatoSimple> platos;
    List<String> fotos;
    boolean disponible;

    @Override
    public double getPrecio() {
        return platos.stream().mapToDouble(p -> getPrecio()).sum();
    }
}
