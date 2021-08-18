package Dominio.Usuarios.MediosContacto;

import Repositorios.Templates.Identificado;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Notificaciones")
public class Notificacion extends Identificado {

    String asunto;
    @Column(length = 480)
    String cuerpo;
    @Column
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

    private void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    private void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    private void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

}
