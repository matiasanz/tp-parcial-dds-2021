package MediosContacto;

import com.mongodb.client.MongoDatabase;

import java.time.LocalDateTime;

public class Notificacion {
    String asunto;
    String cuerpo;
    LocalDateTime fechaHora = LocalDateTime.now();
    MongoHandler logger = new MongoHandler();

    public Notificacion(String asunto, String cuerpo){
        this.asunto=asunto;
        this.cuerpo=cuerpo;
    }

    public String getAsunto() {
            return asunto;
        }

    public String getCuerpo() {
            return cuerpo;
        }

    public String getFechaHora() {
        return String.valueOf(fechaHora);
    }

    public void loguearme(){
        logger.loguearNotificacion("LogsNotificacionesPush", "notificaciones",this);
    }

    public String toString(){
            return "["+getAsunto()+"] "+getCuerpo();
        }


}
