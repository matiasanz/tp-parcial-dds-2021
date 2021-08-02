import Repositorios.Templates.Colecciones.ColeccionMemoria;
import Repositorios.Templates.Identificable;
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
        class UnIdentificable extends Identificable {}
        class Repo extends Repositorios.Templates.Repo<UnIdentificable> {
            public Repo() {
                super(new ColeccionMemoria<>());
            }
        }

        UnIdentificable i = new UnIdentificable();
        Repo repo = new Repo();
        repo.agregar(i);

        assertEquals(i, repo.find(i.getId()).get());
    }
}
