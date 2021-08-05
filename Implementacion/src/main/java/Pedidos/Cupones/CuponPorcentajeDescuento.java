package Pedidos.Cupones;

import Pedidos.Carrito;
import Usuarios.Cliente;

import javax.persistence.*;

@Entity
@Table(name="CuponesPorcentajeDto")
@AttributeOverride(name="cliente", column=@Column(name="cliente"))
public class CuponPorcentajeDescuento extends Cupon {
    public CuponPorcentajeDescuento(){}
    public CuponPorcentajeDescuento(float porcentaje, int cuantosPedidos){
        this.porcentaje = porcentaje;
        this.porCuantosPedidos = cuantosPedidos;
    }

    private float porcentaje;
    private int porCuantosPedidos;
    private int usos = 0;

    @Override
    public Double calcularSobre(double precio){
        return precio*porcentaje;
    }

    @Override
    public String getDetalle(){
        return porcentaje*100+"% en "+ porCuantosPedidos +" pedido/s ("+usosRestantes()+" restantes)";
    }

    @Override
    public void notificarUso(Cliente cliente, Carrito carrito){
        usos++;
        if(usosRestantes()==0){
            cliente.quitarCupon(this);
        }
    }

    public int usosRestantes(){
        return porCuantosPedidos - usos;
    }
}
