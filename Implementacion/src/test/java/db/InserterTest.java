package db;

import Controladores.Utils.Transaccional;
import MediosContacto.MedioDeContacto;
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
import Utils.Factory.ProveedorDeLocales;
import Utils.Factory.ProveedorDeNotif;
import Utils.Factory.ProveedorDePlatos;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class InserterTest implements Transaccional {
    @After
    public void clean(){
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
        platoSimple.setTitulo("hamburguesa");
        platoSimple.setDescripcion("Medallon de carne entre panes");
        platoSimple.setIngredientes(Arrays.asList("pan", "carne"));

        PlatoSimple platoSimple2 = new PlatoSimple();
        platoSimple2.setPrecioBase(90.5);
        platoSimple2.setTitulo("papas noisette");
        platoSimple2.setDescripcion("papas redondas");
        platoSimple2.setIngredientes(Arrays.asList("papas sin cascara"));

        Combo combo = new Combo();
        combo.setTitulo("hamburguesa con papas");
        combo.setPlatos(Arrays.asList(platoSimple,platoSimple2));

        assertInsertable(combo);
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
}
