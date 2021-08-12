import Repositorios.Templates.Colecciones.ColeccionMemoria;
import Repositorios.Templates.Identificado;
import Repositorios.Templates.Repo;
import Utils.Prueba;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProyectTest {
    @Test
    public void esteTestDeberiaPasar(){
        assertEquals("DdeS", new Prueba().materia());
    }
}
