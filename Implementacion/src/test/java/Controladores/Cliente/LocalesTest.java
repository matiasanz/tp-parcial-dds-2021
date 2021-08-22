package Controladores.Cliente;

import Dominio.Local.Platos.Plato;
import Dominio.Utils.Factory.ProveedorDePlatos;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocalesTest {
    Plato plato = ProveedorDePlatos.hamburguesa();
    LocalDate fechaDeReferencia = LocalDate.of(2021, 8, 21);
    LocalController controller = new LocalController(null, null);

    @Test
    public void unPlatoDeHace6DiasEsDeLaSemana() {
        plato.setFechaCreacion(LocalDate.of(2021, 8, 15));
        assertTrue(controller.platoDeLaSemana(plato, fechaDeReferencia));
    }

    @Test
    public void unPlatoDeSemanaAnteriorNoEsDeEstaSemana(){
        plato.setFechaCreacion(LocalDate.of(2021, 8, 14));
        assertFalse(controller.platoDeLaSemana(plato, fechaDeReferencia));
    }
}
