package Platos;

import java.util.List;

public class PlatoSimple extends Plato {
    List<String> ingredientes; //TODO: ver despues como implementamos esto
    double precio;

    @Override
    public double getPrecio() {
        return precio;
    }
}
