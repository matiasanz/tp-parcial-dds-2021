package Repositorios.Templates;

public abstract class Identificable {
    private Long id;

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