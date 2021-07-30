import Local.Local;
import MediosContacto.MongoHandler;
import Pedidos.Pedido;
import Usuarios.Cliente;
import org.junit.Test;
import Local.CategoriaLocal;

public class MongoTest {
    MongoHandler mongoHandler = new MongoHandler();
    Cliente usuario = new Cliente("romi","romi","romina","martinez","romi@gmail.com","caseros");
    Local local = new Local("farola","caseros 34", CategoriaLocal.VEGANO);
    Pedido pedido = new Pedido (890.5,"caseros 123",local,usuario);


    @Test
    public void insertaPedido(){
        mongoHandler.insertarPedido(pedido);
    }

    @Test
    public void seMuestraPedidosCorrectamente(){
        mongoHandler.muestraRegistros("pedidos","rechazados");
    }

    @Test
    public void seVaciaColleccionCorrectamente(){
        mongoHandler.vaciarColeccion("pedidos","rechazados");
    }
}
