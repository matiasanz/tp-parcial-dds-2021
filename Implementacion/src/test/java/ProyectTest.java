import Dominio.Utils.Prueba;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProyectTest {
    @Test
    public void esteTestDeberiaPasar(){
        assertEquals("DdeS", new Prueba().materia());
    }
}
