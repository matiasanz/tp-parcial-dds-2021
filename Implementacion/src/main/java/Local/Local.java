package Local;

import Pedidos.Direccion;
import Pedidos.EstadoPedido;
import Pedidos.Pedido;
import Platos.ComboBorrador;
import Platos.Plato;
import Repositorios.Templates.Identificable;
import Utils.Exceptions.PlatoInexistenteException;
import Utils.Exceptions.PlatoRepetidoException;
import Utils.Factory.ProveedorDeNotif;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Local extends Identificable {
    String nombre;
    Direccion direccion;
    List<Pedido> pedidosRecibidos = new LinkedList<>();
    List<Plato> menu = new ArrayList<>();
    List<String> fotos = new LinkedList<>();
    List<CategoriaLocal> categorias = new ArrayList<>();
    ComboBorrador borrador = new ComboBorrador(this);

    public Local(String nombre, Direccion direccion, CategoriaLocal categoria) {
        this.nombre = nombre;
        this.direccion = direccion;
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
        if(getMenu().stream().anyMatch(plato::mismoNombre))
            throw new PlatoRepetidoException(plato.getNombre());

        menu.add(plato);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public ComboBorrador getBorrador(){
        return borrador;
    }

    public void resetBorrador(){
        borrador = new ComboBorrador(this);
    }
}