package Pedidos.Cupones;

import Pedidos.Carrito;
import Usuarios.Cliente;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Sin_cupon")
@AttributeOverride(name="cliente", column=@Column(name="cliente"))
public class SinCupon extends Cupon {
    @Override
    public Double calcularSobre(double precio) {
        return 0.0;
    }

    @Override
    public String getDetalle(){
        return "Sin Cupon";
    }

    @Override
    public void notificarUso(Cliente cliente, Carrito carrito){
    }
}
