package Pedidos;

import Usuarios.Cliente;
import Utils.Exceptions.PedidoNoEntregadoException;
import Local.Local;
import Repositorios.Templates.Identificable;

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
    LocalDateTime fechaHora = LocalDateTime.now();
    String direccion;
    Cliente cliente;

    public Pedido(double precio, String direccion, Local local, List<Item> items, Cliente cliente){
        this.precio = precio;
        this.direccion=direccion;
        this.local = local;
        this.items.addAll(items);
        this.cliente = cliente;
    }

    public Pedido(double precio, String direccion, Local local, Cliente cliente){
        this.precio = precio;
        this.direccion=direccion;
        this.local = local;
        this.cliente = cliente;
    }

    public Double getImporte(){
        return precio;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
        cliente.notificar(notificacionResultadoPedido(cliente, estado));
    }

    public LocalDateTime getFechaInicio() {
        return fechaHora;
    }

    public Boolean mismoMesQue(LocalDate fechaActual) {
        return fechaHora.getMonth() == fechaActual.getMonth()
            && fechaHora.getYear() == fechaActual.getYear();
    }

    public Boolean entreFechas(LocalDate min, LocalDate max){
        return fechaHora.isAfter(min.atStartOfDay()) && fechaHora.isBefore(max.atStartOfDay())
            || fechaHora.equals(min.atStartOfDay()) || fechaHora.equals(max.atStartOfDay());
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
