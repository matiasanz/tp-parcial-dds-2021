import Repositorios.Templates.Colecciones.ColeccionMemoria;
import Repositorios.Templates.Identificado;
import Repositorios.Templates.Repo;
import Utils.Prueba;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PruebaTest {
    @Test
    public void esteTestDeberiaPasar(){
        assertEquals("DdeS", new Prueba().materia());
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
