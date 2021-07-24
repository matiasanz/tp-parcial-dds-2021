package Pedidos;

import Platos.Plato;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class Item {
    private Plato plato;
    private Integer cantidad;
    private String aclaraciones;

    public Item(Plato plato, int cantidad, String aclaraciones){
        this.plato=plato;
        requireNonNull(plato, "no aclara plato");
        this.cantidad=cantidad;
        requireNonNull(plato, "no aclara cantidad");
        this.aclaraciones= aclaraciones;
    }

    public Plato getPlato() {
        return plato;
    }

    public int getCantidad(){
        return cantidad;
    }

    public String getAclaraciones(){
        return aclaraciones.equals("")? "-": aclaraciones;
    }

    public BigDecimal getPrecio() {
        return plato.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }
}
