package Pedidos;

import Platos.Plato;
import Utils.Exceptions.PedidoNoEntregadoException;
import Local.Local;
import Repositorios.Templates.Identificable;

import java.time.Duration;
import java.time.LocalDate;
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

    public Pedido(Direccion direccion, Local local, List<Item> items){
        this.direccion=direccion;
        this.local = local;
        this.items.addAll(items);
    }

    public double subtotal(){
        return 0; //TODO: lo puse asi pa q no rompa categ, desps modificar
    }

    public Duration tiempoEntrega(){
        validarPedidoEntregado();
        return Duration.between(horaInicio, horaFin);
    }

    private void validarPedidoEntregado(){
        if(horaFin==null) throw new PedidoNoEntregadoException(this);
    }

    public LocalDateTime getFechaInicio() {
        return horaInicio;
    }

    public Boolean mismoMesQue(LocalDate fechaActual) {
        return horaInicio.getMonth() == fechaActual.getMonth()
            && horaInicio.getYear() == fechaActual.getYear();
    }

    public Local getLocal() {
        return local;
    }

    public List<Item> getItems(){
        return items;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
