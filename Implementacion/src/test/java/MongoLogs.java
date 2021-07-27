import MediosContacto.MongoHandler;
import MediosContacto.Notificacion;
import org.bson.Document;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MongoLogs {
    Notificacion notificacion = new Notificacion("Prueba","estoy viendo si loguea");

    @Test
    public void loguear(){
        MongoHandler mongoHandler = new MongoHandler();
        mongoHandler.iniciarConexion();
        mongoHandler.obtenerDb("test");
        mongoHandler.crearColeccion("notificaciones");
        Document document = new Document("asunto", notificacion.getAsunto()).
            append("cuerpo", notificacion.getCuerpo()).
            append("fecha", notificacion.getFechaHora());
        mongoHandler.insertarUnDocumento(document);
    }

}
