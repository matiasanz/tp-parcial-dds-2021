import MediosContacto.MongoHandler;
import MediosContacto.Notificacion;
import Usuarios.Cliente;
import org.junit.Test;

public class MongoTest {
    MongoHandler mongoHandler = new MongoHandler();
    Cliente usuario = new Cliente("romi","romi","romina","martinez","romi@gmail.com","caseros");
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
