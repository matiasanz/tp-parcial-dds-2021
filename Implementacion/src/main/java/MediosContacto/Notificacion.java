package MediosContacto;

import java.time.LocalDateTime;

public class Notificacion {
    String asunto;
    String cuerpo;
    LocalDateTime fechaHora = LocalDateTime.now();

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

    public String toString(){
            return "["+getAsunto()+"] "+getCuerpo();
        }

}