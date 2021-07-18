import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class PruebaTest {
    @Test
    public void esteTestDeberiaPasar(){
        assertEquals("DdeS", new Prueba().materia());
    }
}
