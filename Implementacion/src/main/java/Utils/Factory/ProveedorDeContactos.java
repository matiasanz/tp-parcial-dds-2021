package Utils.Factory;

import Local.Contacto;
import Local.*;
import MediosContacto.NotificadorPush;
import Pedidos.Direccion;
import Usuarios.Cliente;
public class ProveedorDeContactos {

    public static Contacto romina(){
        Contacto yo = new Contacto("romi", "123", "romina", "gutierrez", "_@mail.com", null);
        Local local = new Local("Si no corro me Pizza", new Direccion("al fondo a la derecha"), yo, CategoriaLocal.PIZZERIA);
        yo.setLocal(local);

        return yo;
    }

    public static Contacto contactoStub(){
        Contacto stub = new Contacto("jorge", "salomon", "laley@argentina.arcor", "pepe", "piripipi", null);
        stub.agregarMedioDeContacto(new NotificadorPush());

        return stub;
    }
}
