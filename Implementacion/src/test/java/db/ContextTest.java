package db;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static org.junit.Assert.assertNotNull;

public class ContextTest
    extends AbstractPersistenceTest //cada test se hace en una transaccion
    implements WithGlobalEntityManager /*hibernate */{
    @Test
    public void entityManagerSeObtiene(){
        assertNotNull(entityManager());
    }

    @Test
    public void transaccionesSeEjecutanCorrectamente(){
        withTransaction(()->{});
    }
}
