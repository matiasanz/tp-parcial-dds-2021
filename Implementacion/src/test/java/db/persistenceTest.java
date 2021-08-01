package db;

import Local.Local;
import Pedidos.Cupones.CuponDescuento;
import Pedidos.Cupones.CuponDescuentoPorcentaje;
import Pedidos.Cupones.CuponSaldo;
import Pedidos.Cupones.SinCupon;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Combo;
import Platos.PlatoSimple;
import Repositorios.Templates.Identificable;
import Usuarios.Categorias.Frecuente;
import Usuarios.Categorias.Habitual;
import Usuarios.Categorias.Ocasional;
import Usuarios.Categorias.Primerizo;
import Usuarios.Cliente;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeDuenios;
import Utils.Factory.ProveedorDeLocales;
import Utils.Factory.ProveedorDePlatos;
import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class persistenceTest extends AbstractPersistenceTest implements WithGlobalEntityManager{

    @Test
    public void persistirUnItem(){
        Item item = new Item(ProveedorDePlatos.guarnicion(), 2, "sin cascara");
        entityManager().persist(item);
        assertNotNull(item.getId());
    }

    @Test
    public void persistirUnPedido(){
        List<Item> items = Arrays.asList(
            new Item(ProveedorDePlatos.hamburguesa(), 2, null)
            , new Item(ProveedorDePlatos.guarnicion(), 2, "sin cascara")
        );

        Pedido pedido = new Pedido(2500.0, "a mi casa", ProveedorDeLocales.cincoEsquinas() , items, ProveedorDeClientes.matias());

        entityManager().persist(pedido);
        assertNotNull(pedido.getId());
        pedido.getItems().forEach(item -> assertNotNull(item.getId()));
    }

    @Test
    public void persistirUnPlatoSimple(){
        PlatoSimple platoSimple = ProveedorDePlatos.hamburguesa();
        entityManager().persist(platoSimple);
        assertNotNull(platoSimple.getId());
    }

    @Test
    public void persistirUnCombo(){
        Combo combo = ProveedorDePlatos.combo();
        entityManager().persist(combo);
        assertNotNull(combo.getId());
        combo.getPlatos().forEach(plato -> {
            assertNotNull(plato.getId());
        });
    }

    @Test
    public void persistirUnCliente(){
        assertPersistible(ProveedorDeClientes.matias());
    }

    @Test
    public void persistirUnDuenio(){
        assertPersistible(ProveedorDeDuenios.romina());
    }

    @Test
    public void persistirUnLocal(){
        Local local = ProveedorDeLocales.cincoEsquinas();
        assertPersistible(local);
        local.getMenu().forEach(this::assertPersistible);
    }

    @Test
    public void persistirDescuentoPorcentaje(){
        CuponDescuento descuento = new CuponDescuentoPorcentaje(.5f, 1);
        assertPersistible(descuento);
    }

    @Test
    public void persistirDescuentoSaldo(){
        CuponDescuento descuento = new CuponSaldo(30.0);
        entityManager().persist(descuento);
        assertNotNull(descuento.getId());
    }

    @Test
    public void persistirSinDescuento(){
        assertPersistible(new SinCupon());
    }

    private void assertPersistible(CuponDescuento obj){
        entityManager().persist(obj);
        assertNotNull(obj.getId());
    }

    @Test
    public void persistirCategorias(){
        assertPersistible(new Frecuente());
        assertPersistible(new Habitual());
        assertPersistible(new Ocasional());
        assertPersistible(new Primerizo());
    }

    private void assertPersistible(Identificable obj){
        entityManager().persist(obj);
        assertNotNull(obj.getId());
    }
}
