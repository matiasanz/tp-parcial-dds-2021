package Pedidos;

import Platos.Plato;

public class Item {
    private Plato plato;
    private Integer cantidad;
    private String aclaraciones;

    public Item(Plato plato, int cantidad, String aclaraciones){
        this.plato=plato;
        this.cantidad=cantidad;
        this.aclaraciones=aclaraciones;
    }

}
