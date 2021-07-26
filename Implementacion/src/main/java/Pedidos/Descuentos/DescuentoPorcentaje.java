package Pedidos.Descuentos;

import Usuarios.Cliente;

public class DescuentoPorcentaje implements Descuento{
    public DescuentoPorcentaje(float porcentaje, int cuantosPedidos){
        this.porcentaje = porcentaje;
        this.cuantosPedidos = cuantosPedidos;
    }

    private float porcentaje;
    private int cuantosPedidos;

    @Override
    public Double calcularSobre(double precio){
        return precio*porcentaje;
    }

    @Override
    public String getDetalle(){
        return porcentaje+"% en "+ cuantosPedidos +" pedido/s";
    }

    @Override
    public void notificarUso(Cliente cliente){
        cuantosPedidos--;
        if(cuantosPedidos <=0){
            cliente.quitarDescuento(this);
        }
    }
}
