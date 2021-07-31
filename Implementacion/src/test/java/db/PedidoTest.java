package db;

import Pedidos.Item;
import Pedidos.Pedido;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeLocales;
import Utils.Factory.ProveedorDePlatos;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class PedidoTest extends AbstractPersistenceTest implements WithGlobalEntityManager{

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

}
