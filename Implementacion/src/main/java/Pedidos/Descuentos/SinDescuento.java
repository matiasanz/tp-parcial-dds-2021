package Pedidos.Descuentos;

import Usuarios.Cliente;

public class SinDescuento implements Descuento{
    @Override
    public Double calcularSobre(double precio) {
        return 0.0;
    }

    @Override
    public String getDetalle(){
        return "Sin descuento";
    }

    @Override
    public void notificarUso(Cliente cliente){
    }
}
