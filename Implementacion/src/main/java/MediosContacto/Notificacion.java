package MediosContacto;

import java.time.LocalDateTime;

public class Notificacion {
    Long id;
    String asunto;
    String cuerpo;
    LocalDateTime fechaHora = LocalDateTime.now();

    public Notificacion(String asunto, String cuerpo){
        this.asunto=asunto;
        this.cuerpo=cuerpo;
    }

    public Long getId() {
        return id;
    }

    public String getAsunto() {
            return asunto;
        }

    public String getCuerpo() {
            return cuerpo;
        }

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }
}
