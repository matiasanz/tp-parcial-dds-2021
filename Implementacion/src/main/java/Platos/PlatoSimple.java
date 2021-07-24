package Platos;

import java.math.BigDecimal;
import java.util.List;

public class PlatoSimple extends Plato {
    public PlatoSimple(String nombre, double precio, List<String> ingredientes){
        super(nombre);
        this.precio = precio;
    }
    List<String> ingredientes; //TODO: ver despues como implementamos esto
    double precio;

    @Override
    public Double getPrecio() {
        return precio;
    }
}
