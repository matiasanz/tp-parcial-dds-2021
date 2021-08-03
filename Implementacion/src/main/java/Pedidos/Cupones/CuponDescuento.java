package Pedidos.Cupones;

import Pedidos.Carrito;
import Repositorios.Templates.Identificable;
import Usuarios.Cliente;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CuponDescuento implements Serializable, Identificable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;


    public void setId(Long id){
        this.id=id;
    }

    public Long getId(){
        return id;
    }


    public abstract Double calcularSobre(double precio);
    public abstract String getDetalle();
    public abstract void notificarUso(Cliente cliente, Carrito carrito);
}
