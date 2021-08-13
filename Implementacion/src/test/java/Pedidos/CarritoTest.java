package Pedidos;

import Pedidos.Carrito;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Combo;
import Platos.PlatoSimple;
import Usuarios.Categorias.Primerizo;
import Utils.Exceptions.PedidoIncompletoException;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeLocales;
import Utils.Factory.ProveedorDePlatos;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CarritoTest{
    private double COTA_ERROR = 0.000001;
    static PlatoSimple platoConDescuento;
    static PlatoSimple platoSinDescuento;
    static Combo combo;
    static Carrito carrito;
    double precioTotal = 3*180.0 + 1*150.0 + 1*350.0 - 0;

    @Before
    public void init(){
        platoConDescuento = ProveedorDePlatos.platoConPrecio(200.0);
        platoConDescuento.setDescuento(0.1f);
        platoSinDescuento = ProveedorDePlatos.platoConPrecio(150.0);
        combo = ProveedorDePlatos.comboConPlatos(platoConDescuento, platoSinDescuento);
        carrito = new Carrito(ProveedorDeClientes.clienteConCategoria(new Primerizo()), ProveedorDeLocales.leble())
            .conItem(new Item(platoConDescuento, 3, null))
            .conItem(new Item(platoSinDescuento, 1, "flotando en perfume en un sombrero de hombre"))
            .conItem(new Item(combo, 1, null))
            .conDireccion("Av La plata 2338");
    }

    @Test
    public void precioDeUnPlatoSimpleConDescuentoSeCalculaBien(){
        assertEquals(200.0, platoConDescuento.getPrecioBase(), 0);
        assertEquals(180.0, platoConDescuento.getPrecio(), COTA_ERROR);
    }

    @Test
    public void precioDeUnComboSinDescuento(){
        assertEquals(350.0, combo.getPrecio(), 0.0);
    }

    @Test
    public void precioDeUnComboConDescuento(){
        combo.setDescuento(0.1f);
        assertEquals(350.0, combo.getPrecioBase(), 0.0);
        assertEquals(315.0, combo.getPrecio(), COTA_ERROR);
    }

    @Test
    public void carritoCalculaBienElPrecio(){
        assertEquals(precioTotal, carrito.getPrecioBase(), COTA_ERROR);
    }

    @Test(expected = PedidoIncompletoException.class)
    public void carritoSinDireccionNoDejaCrearPedido(){
        carrito.conDireccion(null).build();
    }

    @Test(expected = PedidoIncompletoException.class)
    public void carritoSinItemsNoDejaFinalizarPedido() {
        carrito.conItems(new ArrayList<>()).build();
    }

    @Test(expected = PedidoIncompletoException.class)
    public void carritoSinLocalNoDejaFinalizarPedido(){
        carrito.conLocal(null).build();
    }

    @Test
    public void carritoCreaBienElPedido(){
         Pedido pedido = carrito.build();
         assertEquals(precioTotal, pedido.getPrecioAbonado(), COTA_ERROR);
         assertEquals(0, pedido.getDescuento(), 0);
         assertEquals(3, pedido.getItems().size());
    }

    @Test
    public void siCambiaPrecioDeUnPlatoDespuesDePedidoNoAlteraElPrecio(){
        Pedido pedido = carrito.build();
        platoSinDescuento.setPrecioBase(900.0);
        platoConDescuento.setPrecioBase(20000.0);
        assertEquals(precioTotal, pedido.getPrecioAbonado(), COTA_ERROR);
    }
}
