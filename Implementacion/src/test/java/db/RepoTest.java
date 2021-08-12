package db;

import Local.Encargado;
import Repositorios.Templates.Colecciones.ColeccionMemoria;
import Repositorios.Templates.Colecciones.DB;
import Repositorios.Templates.Identificado;
import Repositorios.Templates.Repo;
import Usuarios.Cliente;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeEncargados;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RepoTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    private DB<Cliente> clientes = new DB<>(Cliente.class);
    private DB<Encargado> duenios = new DB<>(Encargado.class);

    Cliente cliente = ProveedorDeClientes.matias();
    Encargado duenio = ProveedorDeEncargados.romina();

    @Before
    public void init(){
        clientes.agregar(cliente);
        duenios.agregar(duenio);
    }

    @After
    public void clean(){
        clientes.remove(cliente);
        duenios.remove(duenio);
    }

    @Test
    public void elementoSeObtiene(){
        Long id = cliente.getId();

        Cliente encontrado = clientes.find(id).orElse(null);
        assertNotNull(encontrado);
        assertEquals(id, encontrado.getId());
        assertEquals(cliente.getNombre(), encontrado.getNombre());
    }

    @Test
    public void identificablesSeEncuentran() {
        class UnIdentificable extends Identificado {}
        class RepoPrueba extends Repo<UnIdentificable> {
            public RepoPrueba() {
                super(new ColeccionMemoria<>());
            }
        }

        UnIdentificable i = new UnIdentificable();
        i.setId(5L);
        RepoPrueba repo = new RepoPrueba();
        repo.agregar(i);

        assertEquals(i, repo.find(i.getId()).get());
    }
}
