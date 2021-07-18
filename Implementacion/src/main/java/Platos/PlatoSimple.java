package Platos;

import java.util.List;

public class PlatoSimple implements Plato{
    String nombre;
    List<String> ingredientes; //TODO: ver despues como implementamos esto
    double precio;
    List<String> fotos;
    boolean disponible;


    @Override
    public double getPrecio() {
        return precio;
    }
}
