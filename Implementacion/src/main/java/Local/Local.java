package Local;

import MediosContacto.Notificacion;
import Pedidos.Pedido;
import Platos.Plato;
import Repositorios.Templates.Identificado;
import Usuarios.Cliente;
import Utils.Exceptions.PlatoInexistenteException;
import Utils.Exceptions.PlatoRepetidoException;
import Utils.Exceptions.UsuarioYaSuscritoException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static Utils.Factory.ProveedorDeNotif.notificacionCambioDeDireccion;
import static Utils.Factory.ProveedorDeNotif.notificacionNuevoPlato;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Suscripciones"
        , joinColumns = @JoinColumn(name="local")
        , inverseJoinColumns = @JoinColumn(name="suscriptor"))
    List<Cliente> suscriptores = new LinkedList<>();

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
        notificarSuscriptores(notificacionNuevoPlato(plato, this));
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String nuevaDireccion) {
        String direccionAnterior = getDireccion();
        this.direccion = nuevaDireccion;

        notificarSuscriptores(notificacionCambioDeDireccion(this, direccionAnterior));
    }

    public void setCategoria(CategoriaLocal categoria){
        this.categoria=categoria;
    }

    public void setSuscriptores(List<Cliente> suscriptores){
        this.suscriptores=suscriptores;
    }

    public List<Cliente> getSuscriptores(){
        return suscriptores;
    }

    public void agregarSuscriptor(Cliente nuevoSuscriptor) {
        if(esSuscriptor(nuevoSuscriptor))
            throw new UsuarioYaSuscritoException(this, nuevoSuscriptor);

        suscriptores.add(nuevoSuscriptor);
    }

    public boolean esSuscriptor(Cliente cliente){
        return suscriptores.stream().anyMatch(cliente::matchId);
    }

    public void eliminarSuscriptor(Cliente cli) {
        suscriptores.removeIf(cli::matchId);
    }

    public void notificarSuscriptores(Notificacion notificacion){
        suscriptores.forEach(s->s.notificar(notificacion));
    }
}