package Local;

import Pedidos.Pedido;
import Platos.Plato;
import Repositorios.Templates.Identificable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Local extends Identificable {
    String nombre;
    String direccion;
    Contacto contacto;
    List<Pedido> pedidosRecibidos = new LinkedList<>();
    List<Plato> menu = new ArrayList<>();
    List<String> fotos = new LinkedList<>();
    List<CategoriaLocal> categorias;

    public Local(String nombre, String direccion, Contacto contacto, List<CategoriaLocal> categorias) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.categorias = categorias;
    }

    public void notificarPedido(Pedido pedido){
        pedidosRecibidos.add(pedido);
    }
}
