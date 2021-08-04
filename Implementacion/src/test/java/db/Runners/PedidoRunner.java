package db.Runners;

import Pedidos.EstadoPedido;
import Pedidos.Item;
import Pedidos.Pedido;
import Usuarios.Cliente;
import Utils.Factory.ProveedorDeLocales;
import Utils.Factory.ProveedorDePlatos;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;

public class PedidoRunner {
    public static void main(String[] args){
        List<Item> items = Arrays.asList(
                new Item(ProveedorDePlatos.hamburguesa(), 2, null)
                , new Item(ProveedorDePlatos.guarnicion(), 2, "sin cascara")
        );

        Pedido pedido = new Pedido();
        pedido.setEstado(EstadoPedido.PENDIENTE);
        Cliente cliente = new Cliente("matiasanz", "123", "matias", "godinez", "_@mail.com", "Mi casa");
        pedido.setPrecio(550.98);
        pedido.setCliente(cliente);
        pedido.setDireccion("rivadavia 8973");
        pedido.setItems(items);
        pedido.setLocal(ProveedorDeLocales.cincoEsquinas());

        EntityManager en = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = en.getTransaction();

        transaction.begin();
        en.persist(pedido);
        transaction.commit();
    }
}
