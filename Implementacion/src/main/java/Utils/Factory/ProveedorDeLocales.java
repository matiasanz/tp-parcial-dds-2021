package Utils.Factory;

import Local.*;
import Pedidos.Direccion;
import Platos.Plato;
import Platos.PlatoSimple;
import static Utils.Factory.ProveedorDeContactos.contactoStub;

import java.util.Arrays;

public class ProveedorDeLocales {
    private static Plato platoStub = new PlatoSimple("Fideos con tuco","Tallarines con salsa de tomate y ajo",  900.0,Arrays.asList("fideos", "tuco"));

    public static Local cincoEsquinas(){
        Local local = new Local("5 esquinas", new Direccion("Calle falsa 123"), CategoriaLocal.PARRILLA);
        local.agregarPlato(platoStub);

        return local;
    }

    public static Local leble(){
        Local local2 = new Local("Leble", new Direccion("por ahi"), CategoriaLocal.DE_AUTOR);
        local2.agregarPlato(platoStub);
        return local2;
    }

    public static Local mcConals(){
        Local local3 = new Local("McConals", new Direccion("alla a la vuelta"), CategoriaLocal.COMIDA_RAPIDA);
        return local3;
    }
}
