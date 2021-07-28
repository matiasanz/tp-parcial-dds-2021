package Utils.Factory;

import Local.Duenio;
import Local.*;
import MediosContacto.NotificadorPush;
import Pedidos.Direccion;

public class ProveedorDeContactos {

    public static Duenio romina(){
        Duenio yo = new Duenio("romi", "123", "romina", "gutierrez", "_@mail.com", null);
        Local local = new Local("Si no corro me Pizza", new Direccion("al fondo a la derecha"), yo, CategoriaLocal.PIZZERIA);
        yo.setLocal(local);

        return yo;
    }

    public static Duenio contactoStub(){
        Duenio stub = new Duenio("jorge", "salomon", "laley@argentina.arcor", "pepe", "piripipi", null);
        stub.agregarMedioDeContacto(new NotificadorPush());

        return stub;
    }
}
