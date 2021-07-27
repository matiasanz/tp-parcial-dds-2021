package Platos;

import java.math.BigDecimal;
import java.util.List;

public class PlatoSimple extends Plato {
    List<String> ingredientes; //TODO: ver despues como implementamos esto
    Double precio;
    String descripcion;

    public PlatoSimple(String nombre, Double precio,  List<String> ingredientes){
        super(nombre);
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    @Override
    public Double getPrecio() {
        return precio;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    @Override
    public String getDescripcion(){
        return descripcion;
    }
}
