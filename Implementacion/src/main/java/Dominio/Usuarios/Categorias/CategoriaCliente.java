package Dominio.Usuarios.Categorias;

import Dominio.Pedidos.Pedido;
import Repositorios.Templates.Identificado;
import Dominio.Usuarios.Cliente;

import javax.persistence.*;

import static Dominio.Utils.Factory.ProveedorDeNotif.notificacionAscensoDeCategoria;

@Entity
@Table(name="CategoriasClientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "detalle")
public abstract class CategoriaCliente extends Identificado {

    public abstract double descuentoPorCategoria(double precio);

    public abstract void notificarPedido(Pedido pedido, Cliente cliente);

    public void cambiarDeCategoria(Cliente cliente, CategoriaCliente siguiente){
        cliente.setCategoria(siguiente);
        cliente.notificar(notificacionAscensoDeCategoria(cliente, siguiente));
    }

    public String getNombre(){
        return this.getClass().getSimpleName();
    }
}
