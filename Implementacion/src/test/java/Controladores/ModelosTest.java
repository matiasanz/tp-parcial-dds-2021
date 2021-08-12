package Controladores;

import Controladores.Utils.Modelo;
import Local.CategoriaLocal;
import Pedidos.EstadoPedido;
import Usuarios.Categorias.Frecuente;
import Usuarios.Categorias.Habitual;
import Usuarios.Categorias.Ocasional;
import Usuarios.Categorias.Primerizo;
import org.junit.Test;

import static Controladores.Utils.Modelos.unparseEnum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static Controladores.Utils.Modelos.parseModel;

public class ModelosTest {
    @Test
    public void numerosSeInsertanCorrectamente(){
        assertFalse(new Modelo("i", 3).get("i") instanceof Double);
        assertEquals(3.57, new Modelo("d", 3.567).get("d"));
    }

    @Test
    public void categoriaLocalSeParseaBien(){
        String nombreAmigable = "Comida rapida";
        assertEquals(nombreAmigable, parseModel(CategoriaLocal.COMIDA_RAPIDA));
        assertEquals(CategoriaLocal.COMIDA_RAPIDA, CategoriaLocal.valueOf(unparseEnum(nombreAmigable)));
    }

    @Test
    public void EstadoPedidoSeParseaBien(){
        String nombreAmigable = "Pendiente";
        assertEquals(nombreAmigable, parseModel(EstadoPedido.PENDIENTE));
        assertEquals(EstadoPedido.PENDIENTE, EstadoPedido.valueOf(unparseEnum(nombreAmigable)));
    }

    @Test
    public void nombresDeCategorias(){
        assertEquals("Primerizo", new Primerizo().getNombre());
        assertEquals("Ocasional", new Ocasional().getNombre());
        assertEquals("Habitual", new Habitual().getNombre());
        assertEquals("Frecuente", new Frecuente().getNombre());
    }
}
