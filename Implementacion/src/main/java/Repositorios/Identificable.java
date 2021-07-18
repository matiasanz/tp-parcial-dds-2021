package Repositorios;

public abstract class Identificable {
    private Long id;

    public void setId(long id){
        this.id= id;
    }

    public Long getId(){
        return id;
    }
}