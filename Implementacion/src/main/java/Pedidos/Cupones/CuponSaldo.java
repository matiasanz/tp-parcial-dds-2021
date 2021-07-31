package Pedidos.Cupones;

import Pedidos.Carrito;
import Usuarios.Cliente;


import javax.persistence.Entity;
import javax.persistence.Table;

public class CuponSaldo extends CuponDescuento{
    private Double saldo;
    private Double cuantoGasto = 0.0;

    public CuponSaldo(){}
    public CuponSaldo(double cuantaPlata){
        this.saldo =cuantaPlata;
    }

    @Override
    public Double calcularSobre(double precio) {
        return Double.min(precio, saldoRestante());
    }

    @Override
    public String getDetalle() {
        return "Por $"+saldo+" ("+saldoRestante()+" restante)";
    }

    @Override
    public void notificarUso(Cliente cliente, Carrito carrito) {
        cuantoGasto += carrito.descuentoPorCupon();
        if(saldoRestante()==0){
            cliente.quitarDescuento(this);
        }
    }

    public Double saldoRestante(){
        return saldo - cuantoGasto;
    }
}

