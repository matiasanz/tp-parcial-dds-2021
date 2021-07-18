package Pedidos;

import Usuarios.Repartidor;
import Utils.Exceptions.PedidoNoEntregadoException;
import Local.Local;
import Repositorios.Identificable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Pedido extends Identificable {
    List<Item> items = new LinkedList<>();
    Local local;
    EstadoPedido estado = EstadoPedido.PENDIENTE;
    LocalDateTime horaInicio = LocalDateTime.now();
    LocalDateTime horaFin;
    Direccion direccion;
    Repartidor repartidor;

    public Pedido(Direccion direccion, Local local, List<Item> items){
        this.local = local;
        this.items.addAll(items);
    }

    public Duration tiempoEntrega(){
        validarPedidoEntregado();
        return Duration.between(horaInicio, horaFin);
    }

    private void validarPedidoEntregado(){
        if(horaFin==null) throw new PedidoNoEntregadoException(this);
    }
}
