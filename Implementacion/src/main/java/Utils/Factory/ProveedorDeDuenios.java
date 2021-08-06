package Utils.Factory;

import Local.Duenio;
import Local.*;
import MediosContacto.NotificadorPush;
import Pedidos.Carrito;
import Pedidos.Item;
import Platos.Plato;
import Platos.PlatoSimple;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ProveedorDeDuenios {

    public static Duenio romina(){
        Local local = ProveedorDeLocales.siNoCorroMePizza();
        Duenio yo = new Duenio("romi", "123", "romina", "gutierrez", "_@mail.com", local);
        yo.setLocal(local);
        yo.agregarMedioDeContacto(new NotificadorPush());
        new Carrito(ProveedorDeClientes.matias(), local).conDireccion("la esquina de mi barrio").conItem(new Item(new PlatoSimple("algo rico", "-", 40.0, new ArrayList<>()), 89, "-")).build();
        return yo;
    }

    public static Duenio contactoStub(){
        Duenio stub = new Duenio("jorge", "salomon", "laley@argentina.arcor", "pepe", "piripipi", null);
        stub.agregarMedioDeContacto(new NotificadorPush());

        return stub;
    }
}
