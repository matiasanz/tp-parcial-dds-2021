package Pedidos.Descuentos;

import Usuarios.Cliente;

public interface Descuento {
    Double calcularSobre(double precio);
    String getDetalle();
    void notificarUso(Cliente cliente);
}
