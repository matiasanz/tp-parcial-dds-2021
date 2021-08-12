package Utils.Factory;

import Local.Encargado;
import Local.*;
import MediosContacto.NotificadorPush;
import Pedidos.Carrito;
import Pedidos.Item;
import Platos.PlatoSimple;

import java.util.ArrayList;

public class ProveedorDeEncargados {

    public static Encargado romina(){
        Local local = ProveedorDeLocales.siNoCorroMePizza();
        Encargado yo = new Encargado("romi", "123", "romina", "gutierrez", "_@mail.com", local);
        yo.setLocal(local);
        yo.agregarMedioDeContacto(new NotificadorPush());
        new Carrito(ProveedorDeClientes.matias(), local).conDireccion("la esquina de mi barrio").conItem(new Item(new PlatoSimple("algo rico", "-", 40.0, new ArrayList<>()), 89, "-")).build();
        return yo;
    }

    public static Encargado contactoStub(){
        Encargado stub = new Encargado("jorge", "salomon", "laley@argentina.arcor", "pepe", "piripipi", null);
        stub.agregarMedioDeContacto(new NotificadorPush());

        return stub;
    }
}
