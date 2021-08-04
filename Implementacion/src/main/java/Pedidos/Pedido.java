package Pedidos;

import Usuarios.Cliente;
import Local.Local;
import Repositorios.Templates.Identificado;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Pedidos")
public class Pedido extends Identificado {
    Double precio;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="pedido")
    List<Item> items = new LinkedList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local")
    Local local;
    @Enumerated(EnumType.ORDINAL)
    EstadoPedido estado = EstadoPedido.PENDIENTE;

    @Column
    LocalDateTime fechaHora = LocalDateTime.now();

    String direccion;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    Cliente cliente;

    Float puntuacion;
    String detallePuntuacion;

    public Pedido(){}
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

    public void setCliente(Cliente cliente){
        this.cliente=cliente;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setLocal(Local local) {
        this.local=local;
    }

    public Float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDetallePuntuacion() {
        return detallePuntuacion;
    }

    public void setDetallePuntuacion(String detallePuntuacion) {
        this.detallePuntuacion = detallePuntuacion;
    }
}
