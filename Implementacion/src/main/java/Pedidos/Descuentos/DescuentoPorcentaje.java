package Pedidos.Descuentos;

import Usuarios.Cliente;

import java.time.LocalDateTime;

public class DescuentoPorcentaje implements Descuento{
    public DescuentoPorcentaje(float porcentaje){
        this.porcentaje = porcentaje;
    }

    private float porcentaje;

    @Override
    public Double calcularSobre(double precio){
        return precio*porcentaje;
    }

    @Override
    public String getDetalle(){
        return porcentaje+"% en una compra";
    }

    @Override
    public void notificarUso(Cliente cliente){
        cliente.quitarDescuento(this);
    }
}
