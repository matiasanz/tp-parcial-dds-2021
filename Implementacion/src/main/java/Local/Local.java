package Local;

import Platos.Plato;

import java.util.List;

public class Local {
    String nombre;
    String direccion;
    Contacto contacto;
    List<Plato> menu;
    //List<Pedido> pedidos;
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
