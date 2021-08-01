package MediosContacto;

import Repositorios.Templates.Identificable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Notificaciones")
@DiscriminatorColumn(name="medio")
public class Notificacion extends Identificable {
    String asunto;
    String cuerpo;
    @Transient
    LocalDateTime fechaHora = LocalDateTime.now();

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
}
