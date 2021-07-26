package Repositorios.Templates;

public abstract class Identificable {
    static long idGenerado = 0L; //TODO: Sacar

    private Long id = idGenerado++;

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