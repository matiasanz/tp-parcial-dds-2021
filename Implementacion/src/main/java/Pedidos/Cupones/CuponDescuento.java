package Pedidos.Cupones;

import Pedidos.Carrito;
import Usuarios.Cliente;

import java.util.Calendar;

public interface CuponDescuento {
    Double calcularSobre(double precio);
    String getDetalle();
    void notificarUso(Cliente cliente, Carrito carrito);
}
