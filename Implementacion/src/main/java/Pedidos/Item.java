package Pedidos;

import Platos.Plato;

public class Item {
    private Plato plato;
    Integer cantidad;

    public Item(Plato plato, int cantidad){
        this.plato=plato;
        this.cantidad=cantidad;
    }

}
