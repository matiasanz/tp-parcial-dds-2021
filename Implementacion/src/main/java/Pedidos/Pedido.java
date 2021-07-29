package Pedidos;

import Platos.Plato;
import Usuarios.Cliente;
import Utils.Exceptions.PedidoNoEntregadoException;
import Local.Local;
import Repositorios.Templates.Identificable;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import static Utils.Factory.ProveedorDeNotif.notificacionResultadoPedido;

public class Pedido extends Identificable {
    Double precio;
    List<Item> items = new LinkedList<>();
    Local local;
    EstadoPedido estado = EstadoPedido.PENDIENTE;
    LocalDateTime horaInicio = LocalDateTime.now();
    LocalDateTime horaFin;
    String direccion;
    Cliente cliente;

    public Pedido(double precio, String direccion, Local local, List<Item> items, Cliente cliente){
        this.precio = precio;
        this.direccion=direccion;
        this.local = local;
        this.items.addAll(items);
        this.cliente = cliente;
    }

    public Double getImporte(){
        return precio;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        cliente.notificar(notificacionResultadoPedido(cliente, estado));
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

    public String getDireccion() {
        return direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
