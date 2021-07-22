package Platos;

import java.math.BigDecimal;
import java.util.List;

public class PlatoSimple extends Plato {
    public PlatoSimple(String nombre, List<String> ingredientes){
        super(nombre);
    }
    List<String> ingredientes; //TODO: ver despues como implementamos esto
    BigDecimal precio;

    @Override
    public BigDecimal getPrecio() {
        return precio;
    }
}
