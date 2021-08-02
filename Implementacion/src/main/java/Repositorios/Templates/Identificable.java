package Repositorios.Templates;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Identificable {

    @Id
    @GeneratedValue
    private Long id; // = idGenerado++;

    public void setId(long id){
        this.id= id;
    }

    public Long getId(){
        return id;
    }

    public Boolean matchId(long id){
        return  this.id==id;
    }
}