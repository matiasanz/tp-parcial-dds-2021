package Pedidos.Cupones;

import Pedidos.Carrito;
import Repositorios.Templates.Identificable;
import Usuarios.Cliente;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Calendar;

public abstract class CuponDescuento extends Identificable {
    public abstract Double calcularSobre(double precio);
    public abstract String getDetalle();
    public abstract void notificarUso(Cliente cliente, Carrito carrito);
}
