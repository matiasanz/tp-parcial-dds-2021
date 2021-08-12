package Utils.Factory;

import Platos.Combo;
import Platos.Plato;
import Platos.PlatoSimple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProveedorDePlatos {
    public static PlatoSimple hamburguesa(){
        return new PlatoSimple("Hamburguesa"
            , "Medallon de carne entre panes"
            , 300.0
            , Arrays.asList("pan", "carne")
        );
    }

    public static PlatoSimple guarnicion(){
        return new PlatoSimple("papas rusticas", "papas con cascara", 90.0,
            Arrays.asList("papa")
        );
    }

    public static Combo combo(){
        return new Combo("Combomba", "Explosion de sabor"
            , Arrays.asList(guarnicion(), hamburguesa())
        );
    }

    public static Plato platoConNombre(String s) {
        Plato plato = hamburguesa();
        plato.setNombre(s);
        return plato;
    }

    public static PlatoSimple platoConPrecio(double precio) {
        return new PlatoSimple("Matambrito", null, precio, ingredientes());
    }

    protected static List<String> ingredientes(String ... ingredientes){
        return new ArrayList<>(Arrays.asList(ingredientes));
    }

    public static Combo comboConPlatos(Plato ... platos) {
        return new Combo("picadita", "cositas varias", new ArrayList<>(Arrays.asList(platos)));
    }
}
