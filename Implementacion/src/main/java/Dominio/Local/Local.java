package Dominio.Local;

import Dominio.MediosContacto.Notificacion;
import Dominio.Pedidos.Pedido;
import Dominio.Platos.Plato;
import Repositorios.Templates.Identificado;
import Dominio.Usuarios.Cliente;
import Dominio.Utils.Exceptions.PlatoInexistenteException;
import Dominio.Utils.Exceptions.PlatoRepetidoException;
import Dominio.Utils.Exceptions.UsuarioYaSuscritoException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static Dominio.Utils.Factory.ProveedorDeNotif.notificacionCambioDeDireccion;
import static Dominio.Utils.Factory.ProveedorDeNotif.notificacionNuevoPlato;

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

    public List<Pedido> pedidosDelMes(LocalDate fechaActual) {
        return getPedidosRecibidos().stream().filter(pedido -> pedido.delMes(fechaActual)).collect(Collectors.toList());
    }

    public Double getPuntuacionMedia(){
        return pedidosRecibidos.stream()
            .filter(Pedido::estaCalificado)
            .mapToDouble(Pedido::getPuntuacion)
            .average()
            .orElse(0);
    }

    //Suscripciones
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

    public Double promedioDePrecios(){
        return getMenu().stream().mapToDouble(Plato::getPrecio)
            .average().orElse(0);
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

    public void actualizarDireccion(String nuevaDireccion){
        String direccionAnterior = getDireccion();
        setDireccion(nuevaDireccion); //No puedo ponerlo en el setter, porque lo usa hibernate
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

    private void setMenu(List<Plato> menu) {
        this.menu = menu;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    private void setDireccion(String nuevaDireccion) {
        this.direccion = nuevaDireccion;
    }
}