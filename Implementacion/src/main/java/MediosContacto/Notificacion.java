package MediosContacto;

import Repositorios.Templates.Identificable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Table(name="Notificaciones")
public class Notificacion extends Identificable {
    String asunto;
    String cuerpo;
    @Transient
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

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }
}
