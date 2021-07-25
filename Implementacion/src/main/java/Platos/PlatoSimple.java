package Platos;

import java.math.BigDecimal;
import java.util.List;

public class PlatoSimple extends Plato {
    public PlatoSimple(String nombre, Double precio){
        super(nombre);
        this.precio = BigDecimal.valueOf(precio);
    }
    List<String> ingredientes; //TODO: ver despues como implementamos esto
    BigDecimal precio;

    @Override
    public BigDecimal getPrecio() {
        return precio;
    }
}
