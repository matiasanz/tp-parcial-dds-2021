package MediosContacto;

import java.time.LocalDateTime;
public class Notificacion {
    Long id;
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

    public String toString(){
            return "["+getAsunto()+"] "+getCuerpo();
        }


}
