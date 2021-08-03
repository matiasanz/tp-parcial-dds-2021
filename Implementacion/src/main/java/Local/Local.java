package Local;

import Pedidos.Pedido;
import Platos.Plato;
import Repositorios.Templates.Identificado;
import Utils.Exceptions.PlatoInexistenteException;
import Utils.Exceptions.PlatoRepetidoException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="Locales")
public class Local extends Identificado {
    String nombre;
    String direccion;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="local")
    List<Pedido> pedidosRecibidos = new LinkedList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="local")
    List<Plato> menu = new ArrayList<>();
    @Enumerated(EnumType.ORDINAL)
    CategoriaLocal categoria;

    public Local(){}
    public Local(String nombre, String direccion, CategoriaLocal categoria) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.categoria = categoria;
    }

    public List<Pedido> pedidosEntreFechas(LocalDate min, LocalDate max){
        return getPedidosRecibidos()
            .stream().filter(p->p.entreFechas(min, max))
            .collect(Collectors.toList())
            ;
    }


    public void agregarPedido(Pedido pedido){
        pedidosRecibidos.add(pedido);
    }

    public String getNombre(){
        return nombre;
    }

    public CategoriaLocal getCategoria(){
        return categoria;
    }

    public List<Pedido> getPedidosRecibidos(){
        return pedidosRecibidos;
    }

    public List<Plato> getMenu(){
        return menu;
    }

    public void setMenu(List<Plato> menu) {
        this.menu = menu;
    }

    public List<Pedido> pedidosDelMes(LocalDate fechaActual) {
        return getPedidosRecibidos().stream().filter(pedido -> pedido.mismoMesQue(fechaActual)).collect(Collectors.toList());
    }

    //getters *****************************************************

    public Plato getPlato(Long idPlato) {
        return getMenu().stream().filter(plato->plato.matchId(idPlato)).findAny().orElseThrow(PlatoInexistenteException::new);
    }

    public void agregarPlato(Plato plato){
        if(getMenu().stream().anyMatch(plato::mismoNombre))
            throw new PlatoRepetidoException(plato.getNombre());

        menu.add(plato);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion=direccion;
    }

    public void setCategoria(CategoriaLocal categoria){
        this.categoria=categoria;
    }
}