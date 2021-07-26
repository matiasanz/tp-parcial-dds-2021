package Platos;

import java.math.BigDecimal;
import java.util.List;

public class PlatoSimple extends Plato {
    List<String> ingredientes; //TODO: ver despues como implementamos esto
    BigDecimal precio;

    public PlatoSimple(String nombre, Double precio,  List<String> ingredientes){
        super(nombre);
        this.precio = BigDecimal.valueOf(precio);
    }


    @Override
    public BigDecimal getPrecio() {
        return precio;
    }
}
