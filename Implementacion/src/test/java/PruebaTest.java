import Repositorios.Templates.Identificable;
import Repositorios.Templates.RepoMemoria;
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
        class Repo extends RepoMemoria<UnIdentificable> {}

        UnIdentificable i = new UnIdentificable();
        Repo repo = new Repo();
        repo.agregar(i);

        assertEquals(i, repo.find(i.getId()).get());
    }
}
