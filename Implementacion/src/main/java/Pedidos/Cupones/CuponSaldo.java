package Pedidos.Cupones;

import Pedidos.Carrito;
import Usuarios.Cliente;

public class CuponSaldo implements CuponDescuento{
    private Double saldo;
    private Double cuantoGasto = 0.0;

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
