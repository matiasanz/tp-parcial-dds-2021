import Controladores.Utils.Modelo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ModelosTest {
    @Test
    public void numerosSeInsertanCorrectamente(){
        assertFalse(new Modelo("i", 3).get("i") instanceof Double);
        assertEquals(3.57, new Modelo("d", 3.567).get("d"));
    }
}
