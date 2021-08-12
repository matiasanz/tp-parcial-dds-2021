package Utils.Factory;

import Local.*;
import Platos.Combo;
import Platos.Plato;
import Platos.PlatoSimple;

import java.util.Arrays;
import java.util.List;

public class ProveedorDeLocales {
    private static Plato platoStub = new PlatoSimple("Fideos con tuco","Tallarines con salsa de tomate y ajo",  900.0,ingredientes("fideos", "tuco"));

    public static Local cincoEsquinas(){
        Local local = new Local("5 esquinas", "Calle falsa 123", CategoriaLocal.PARRILLA);
        local.agregarPlato(new PlatoSimple("bombon suizo", "bocha de helado bañado en chocolate", 200.0, ingredientes("helado", "chocolate")));

        return local;
    }

    public static Local leble(){
        Local local2 = new Local("Leble", "Gualeguychu 123", CategoriaLocal.DE_AUTOR);
        local2.agregarPlato(platoStub);
        return local2;
    }

    public static Local localSinPlatos(){
        return new Local("McConals", "alla a la vuelta", CategoriaLocal.COMIDA_RAPIDA);
    }

    public static Local siNoCorroMePizza() {
        Local local = new Local("Si no corro me Pizza", "al fondo a la derecha", CategoriaLocal.PIZZERIA);
        Combo combo = ProveedorDePlatos.combo();
        combo.getPlatos().forEach(local::agregarPlato);
        local.agregarPlato(combo);
        return local;
    }

    private static List<String> ingredientes(String ... ingredientes){
        return Arrays.asList(ingredientes);
    }
}
