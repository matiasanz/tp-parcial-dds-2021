package db;

import Controladores.Utils.Transaccional;
import Local.Local;
import MediosContacto.MedioDeContacto;
import MediosContacto.Notificacion;
import MediosContacto.NotificadorMail;
import MediosContacto.NotificadorPush;
import Pedidos.EstadoPedido;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Combo;
import Platos.Plato;
import Platos.PlatoSimple;
import Repositorios.Templates.Identificable;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Categorias.Frecuente;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Utils.Factory.ProveedorDeLocales;
import Utils.Factory.ProveedorDeNotif;
import Utils.Factory.ProveedorDePlatos;
import org.junit.After;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class InserterTest implements Transaccional {
    @After
    public void clean(){
        withTransaction(()->{
            deleteFrom(Notificacion.class);
            deleteFrom(MedioDeContacto.class);
            deleteFrom(Item.class);
            deleteFrom(Pedido.class);
            deleteFrom(Plato.class);
            deleteFrom(Local.class);
            deleteFrom(Usuario.class);
        });
        entityManager().clear();
        entityManager().close();
    }

    @Test
    public void insertarCliente(){
        Cliente cliente = new Cliente();
        CategoriaCliente frecuente = new Frecuente();
        cliente.setCategoria(frecuente);
        cliente.setNombre("federico");
        cliente.setApellido("martinez");
        cliente.setUsername("fm");
        cliente.setPassword("90");
        cliente.setMail("fede@gmail.com");

        cliente.agregarNotificacionPush(ProveedorDeNotif.notificacionResultadoPedido(cliente, EstadoPedido.PENDIENTE));

        NotificadorPush notificadorPush = new NotificadorPush();
        NotificadorMail notificadorMail =new NotificadorMail();

        List<MedioDeContacto> medios = Arrays.asList(notificadorMail,notificadorPush);

        cliente.setMediosDeContacto(medios);

        assertInsertable(cliente);
    }

    @Test
    public void insertarUnLocal(){
        assertInsertable(ProveedorDeLocales.cincoEsquinas());
    }

    @Test
    public void insertarUnItem(){
        Item item1 = new Item(ProveedorDePlatos.hamburguesa(),200,  "-");
        assertInsertable(item1);
    }

    @Test
    public void insertarUnPlato(){
        PlatoSimple platoSimple = new PlatoSimple();
        platoSimple.setPrecioBase(897.5);
        platoSimple.setNombre("hamburguesa");
        platoSimple.setDescripcion("Medallon de carne entre panes");
        platoSimple.setIngredientes(Arrays.asList("pan", "carne"));

        PlatoSimple platoSimple2 = new PlatoSimple();
        platoSimple2.setPrecioBase(90.5);
        platoSimple2.setNombre("papas noisette");
        platoSimple2.setDescripcion("papas redondas");
        platoSimple2.setIngredientes(Arrays.asList("papas sin cascara"));

        Combo combo = new Combo();
        combo.setNombre("hamburguesa con papas");
        combo.setPlatos(Arrays.asList(platoSimple,platoSimple2));

        assertInsertable(combo);
    }

    @Test
    public void insertarUnPedido(){
        Local local = ProveedorDeLocales.localSinPlatos();
        PlatoSimple plato1 = new PlatoSimple("alto guiso", "-", 15.0, new ArrayList<>());
        Plato plato2 = new PlatoSimple("chocotorta", "torta de chocolate", 210.0, new ArrayList<>());

        local.agregarPlato(plato1);
        local.agregarPlato(plato2);

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
        pedido.setLocal(local);

        withTransaction(()->{
            entityManager().persist(pedido);
        });

        assertNotNull(pedido.getId());
    }

    private void assertInsertable(Identificable persistible){
        withTransaction(()->{
            entityManager().persist(persistible);
        });

        assertNotNull(persistible.getId());

        withTransaction(()->{
            entityManager().remove(persistible);
        });
    }

    private void deleteFrom(Class<?> entity){
        entityManager().createQuery("delete from "+entity.getSimpleName());
    }
}
