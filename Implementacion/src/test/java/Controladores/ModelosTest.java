package Controladores;

import Controladores.Utils.Modelo;
import Local.CategoriaLocal;
import org.junit.Test;

import static Controladores.Utils.Modelos.unparseEnum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static Controladores.Utils.Modelos.parseModel;

public class ModelosTest {
    @Test
    public void numerosSeInsertanCorrectamente(){
        assertFalse(new Modelo("i", 3).get("i") instanceof Double);
        assertEquals(3.57, new Modelo("d", 3.567).get("d"));
    }

    @Test
    public void cualquierEnumSeCapitaliza(){
        String nombreAmigable = "Comida rapida";
        assertEquals(nombreAmigable, parseModel(CategoriaLocal.COMIDA_RAPIDA));
        assertEquals(CategoriaLocal.COMIDA_RAPIDA, CategoriaLocal.valueOf(unparseEnum(nombreAmigable)));
    }
}
