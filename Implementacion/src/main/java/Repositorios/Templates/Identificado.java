package Repositorios.Templates;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Identificado implements Identificable{
    @Id
    @GeneratedValue
    private Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }
}