package Utils.Factory;

import Controladores.Utils.Modelos;
import Pedidos.Pedido;
import org.bson.Document;

import javax.print.Doc;
import java.time.LocalDateTime;

public class ProveedorDeLogs {
    public static Document logPedidoRechazado(Pedido pedido){
        return documentoFechado()
            .append("precio",pedido.getPrecioAbonado())
            .append("direccion",pedido.getDireccion())
            .append("local",pedido.getLocal().getNombre())
            .append("cliente",pedido.getCliente().getUsername());
    }

    public static Document logFalloAutenticacion(int intentos, String usuarioIngresado) {
        return documentoFechado()
            .append("usuario",usuarioIngresado)
            .append("intentos",intentos);
    }


    private static Document documentoFechado(){
        LocalDateTime fecha = LocalDateTime.now();
        return new Document(Modelos.parseModel(fecha));
    }
}
