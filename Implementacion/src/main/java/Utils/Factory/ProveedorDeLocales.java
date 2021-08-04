package Utils.Factory;

import Local.*;
import Platos.Combo;
import Platos.Plato;
import Platos.PlatoSimple;

import java.util.Arrays;

public class ProveedorDeLocales {
    private static Plato platoStub = new PlatoSimple("Fideos con tuco","Tallarines con salsa de tomate y ajo",  900.0,Arrays.asList("fideos", "tuco"));

    public static Local cincoEsquinas(){
        Local local = new Local("5 esquinas", "Calle falsa 123", CategoriaLocal.PARRILLA);
        local.agregarPlato(platoStub);

        return local;
    }

    public static Local leble(){
        Local local2 = new Local("Leble", "Gualeguychu 123", CategoriaLocal.DE_AUTOR);
        local2.agregarPlato(platoStub);
        return local2;
    }

    public static Local mcConals(){
        Local local3 = new Local("McConals", "alla a la vuelta", CategoriaLocal.COMIDA_RAPIDA);
        return local3;
    }

    public static Local siNoCorroMePizza() {
        Local local = new Local("Si no corro me Pizza", "al fondo a la derecha", CategoriaLocal.PIZZERIA);
        Combo combo = ProveedorDePlatos.combo();
        combo.getPlatos().forEach(local::agregarPlato);
        local.agregarPlato(combo);
        return local;
    }
}
