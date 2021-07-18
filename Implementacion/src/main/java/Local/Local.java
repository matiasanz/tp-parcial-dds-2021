package Local;

import Pedidos.Pedido;
import Platos.Plato;
import Repositorios.Identificable;

import java.util.List;

public class Local extends Identificable {
    String nombre;
    String direccion;
    Contacto contacto;
    List<Plato> menu;
    List<Pedido> pedidosRealizados;
    List<String> fotos;
    List<CategoriaLocal> categorias;

    public Local(String nombre, String direccion, Contacto contacto, List<Plato> menu, List<String> fotos, List<CategoriaLocal> categorias) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.menu = menu;
        this.fotos = fotos;
        this.categorias = categorias;
    }
}
