import MediosContacto.MongoHandler;
import MediosContacto.Notificacion;
import Pedidos.Direccion;
import Usuarios.Cliente;
import org.junit.Test;

public class MongoTest {
    MongoHandler mongoHandler = new MongoHandler();
    Direccion direccion = new Direccion("caseros");
    Cliente usuario = new Cliente("romi","romi","romina","martinez","romi@gmail.com",direccion);
    Notificacion notificacion = new Notificacion("PROBANDO","FUNCIONA EXCELENTE");

    @Test
    public void seRegistraCorrectamente(){
        mongoHandler.insertarRegistro(notificacion);
    }

    @Test
    public void seMuestraCorrectamente(){
        mongoHandler.muestraRegistros();
    }

    @Test
    public void seVaciaColleccionCorrectamente(){
        mongoHandler.vaciarColeccion();
    }
}
