package Utils.Factory;

import Local.Duenio;
import Local.*;
import MediosContacto.NotificadorPush;
import Pedidos.Carrito;
import Pedidos.Item;
import Platos.Plato;
import Platos.PlatoSimple;

import java.awt.*;
import java.util.Collections;

public class ProveedorDeContactos {

    public static Duenio romina(){
        Local local = new Local("Si no corro me Pizza", "al fondo a la derecha", CategoriaLocal.PIZZERIA);
        Duenio yo = new Duenio("romi", "123", "romina", "gutierrez", "_@mail.com", local);
        yo.setLocal(local);
        new Carrito(ProveedorDeClientes.matias(), local).conDireccion("la").conItem(new Item(new PlatoSimple("algo rico", "-", 40.0, Collections.EMPTY_LIST), 89, "-")).build();
        return yo;
    }

    public static Duenio contactoStub(){
        Duenio stub = new Duenio("jorge", "salomon", "laley@argentina.arcor", "pepe", "piripipi", null);
        stub.agregarMedioDeContacto(new NotificadorPush());

        return stub;
    }
}
