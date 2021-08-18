package db;

import Dominio.Local.Local;
import Dominio.Pedidos.Item;
import Dominio.Pedidos.Pedido;
import Dominio.Local.Platos.Combo;
import Dominio.Local.Platos.PlatoSimple;
import Repositorios.Templates.Identificado;
import Dominio.Usuarios.Categorias.Frecuente;
import Dominio.Usuarios.Categorias.Habitual;
import Dominio.Usuarios.Categorias.Ocasional;
import Dominio.Usuarios.Categorias.Primerizo;
import Dominio.Usuarios.Cliente;
import Dominio.Utils.Factory.ProveedorDeClientes;
import Dominio.Utils.Factory.ProveedorDeEncargados;
import Dominio.Utils.Factory.ProveedorDeLocales;
import Dominio.Utils.Factory.ProveedorDePlatos;
import org.junit.After;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class persistenceTest extends AbstractPersistenceTest implements WithGlobalEntityManager{

    @After
    public void clean(){
        entityManager().getTransaction().rollback();
    }

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
        Cliente cliente = ProveedorDeClientes.matias();
        assertPersistible(cliente);
        cliente
            .getMediosDeContacto()
            .forEach(m->assertNotNull(m.getId()));
    }

    @Test
    public void persistirUnDuenio(){
        assertPersistible(ProveedorDeEncargados.romina());
    }

    @Test
    public void persistirUnLocal(){
        Local local = ProveedorDeLocales.cincoEsquinas();
        assertPersistible(local);
        local.getMenu().forEach(this::assertPersistible);
    }

    @Test
    public void persistirCategorias(){
        assertPersistible(new Frecuente());
        assertPersistible(new Habitual());
        assertPersistible(new Ocasional());
        assertPersistible(new Primerizo());
    }

    private void assertPersistible(Identificado obj){
        entityManager().persist(obj);
        assertNotNull(obj.getId());
    }
}
