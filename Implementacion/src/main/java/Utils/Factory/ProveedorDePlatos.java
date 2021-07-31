package Utils.Factory;

import Platos.Combo;
import Platos.Plato;
import Platos.PlatoSimple;

import java.util.ArrayList;
import java.util.Arrays;

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
        return new Combo("Combomba"
            , Arrays.asList(guarnicion(), hamburguesa())
        );
    }

}
