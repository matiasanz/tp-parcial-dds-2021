package MediosContacto;

import Repositorios.Templates.Identificado;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Notificaciones")
@DiscriminatorColumn(name="medio")
public class Notificacion extends Identificado {

    String asunto;
    String cuerpo;
    @Column
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


    public Notificacion(){}

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

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }

    public String toString(){
            return "["+getAsunto()+"] "+getCuerpo();
        }


}
