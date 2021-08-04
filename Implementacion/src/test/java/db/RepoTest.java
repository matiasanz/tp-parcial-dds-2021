package db;

import Local.Duenio;
import Repositorios.Templates.Colecciones.DB;
import Usuarios.Cliente;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeDuenios;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.Assert.assertEquals;

public class RepoTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    private DB<Cliente> clientes = new DB<>(Cliente.class);
    private DB<Duenio> duenios = new DB<>(Duenio.class);

    Cliente cliente = ProveedorDeClientes.matias();
    Duenio duenio = ProveedorDeDuenios.romina();

    @Before
    public void init(){
        clientes.agregar(cliente);
        duenios.agregar(duenio);
    }

    @Test
    public void elementoSeObtiene(){
        Long id = cliente.getId();
        assertEquals(id, entityManager().find(Cliente.class, id).getId());
    }

    @Test
    public void elementosSeObtienenCorrectamenteDeFormaNormal(){
        assertEquals(1, entityManager().createQuery("from Cliente").getResultList().size());
    }

    @Test
    public void elementosSeObtienenCorrectamente(){
        //assertEquals(1, clientes.getAll().size());
        assertEquals(1, duenios.getAll().size());
    }
}
