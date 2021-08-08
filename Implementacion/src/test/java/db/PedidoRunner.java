package db;

import Controladores.Utils.Transaccional;
import Pedidos.EstadoPedido;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Plato;
import Platos.PlatoSimple;
import Usuarios.Cliente;
import Utils.Factory.ProveedorDeLocales;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class PedidoRunner implements Transaccional {

    public static void main(String[] args){
        new PedidoRunner().execute();
    }

    public void execute(){
        Plato plato1 = new PlatoSimple("alto guiso", "-", 15.0, new ArrayList<>());
        Plato plato2 = new PlatoSimple("chocotorta", "torta de chocolate", 210.0, new ArrayList<>());

        withTransaction(()->{
            entityManager().persist(plato1);
            entityManager().persist(plato2);
        });

        List<Item> items = new ArrayList<>();
        items.add(new Item(plato1, 2, null));
        items.add(new Item(plato2, 2, "sin cascara"));

        Pedido pedido = new Pedido();
        pedido.setEstado(EstadoPedido.PENDIENTE);
        Cliente cliente = new Cliente("matiasanz", "123", "matias", "godinez", "_@mail.com", "Mi casa");
        pedido.setPrecioAbonado(550.98);
        pedido.setCliente(cliente);
        pedido.setDireccion("rivadavia 8973");
        pedido.setItems(items);
        pedido.setLocal(ProveedorDeLocales.cincoEsquinas());

        withTransaction(()->{
            entityManager().persist(pedido);
        });

        assertNotNull(pedido.getId());

        EntityManager em = entityManager();
        withTransaction(()->{
            em.remove(pedido);
            em.remove(plato1);
            em.remove(plato2);
        });
    }
}
