package Local;

import Pedidos.Direccion;
import Pedidos.EstadoPedido;
import Pedidos.Pedido;
import Platos.ComboBorrador;
import Platos.Plato;
import Repositorios.Templates.Identificable;
import Usuarios.Usuario;
import Utils.Exceptions.PlatoInexistenteException;
import static Utils.Factory.ProveedorDeNotif.notificacionConfirmacionPedido;
import static Utils.Factory.ProveedorDeNotif.notificacionRechazoPedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Local extends Identificable {
    String nombre;
    Direccion direccion;
    Contacto contacto;
    List<Pedido> pedidosRecibidos = new LinkedList<>();
    List<Plato> menu = new ArrayList<>();
    List<String> fotos = new LinkedList<>();
    List<CategoriaLocal> categorias = new ArrayList<>();
    ComboBorrador borrador = new ComboBorrador(this);

    public Local(String nombre, Direccion direccion, Contacto contacto, CategoriaLocal categoria) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.categorias.add(categoria);
    }

    public void notificarPedido(Pedido pedido){
        pedidosRecibidos.add(pedido);
    }

    public String getNombre(){
        return nombre;
    }

    public List<CategoriaLocal> getCategorias(){
        return categorias;
    }

    public List<Pedido> getPedidosRecibidos(){
        return pedidosRecibidos;
    }

    public List<Plato> getMenu(){
        return menu;
    }

    public List<Pedido> pedidosDelMes(LocalDate fechaActual) {
        return getPedidosRecibidos().stream().filter(pedido -> pedido.mismoMesQue(fechaActual)).collect(Collectors.toList());
    }

    public Plato getPlato(Long idPlato) {
        return getMenu().stream().filter(plato->plato.matchId(idPlato)).findAny().orElseThrow(PlatoInexistenteException::new);
    }

    public void agregarPlato(Plato plato){
        menu.add(plato);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void aceptarPedido(Pedido pedido){
        pedido.setEstado(EstadoPedido.CONFIRMADO);
        pedido.getCliente().notificar(notificacionConfirmacionPedido(pedido.getCliente()));
    }

    public void rechazarPedido(Pedido pedido){
        pedido.setEstado(EstadoPedido.RECHAZADO);
        pedido.getCliente().notificar(notificacionRechazoPedido(pedido.getCliente()));
    }

    public ComboBorrador getBorrador(){
        return borrador;
    }

    public void resetBorrador(){
        borrador = new ComboBorrador(this);
    }
}