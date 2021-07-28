package MediosContacto;

import java.time.LocalDateTime;
public class Notificacion {
    String asunto;
    String cuerpo;
    LocalDateTime fechaHora = LocalDateTime.now();

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

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

    public String toString(){
            return "["+getAsunto()+"] "+getCuerpo();
        }
}
