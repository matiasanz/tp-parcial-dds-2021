package Repositorios.Templates;

public interface Identificable {
    Long getId();

    default Boolean matchId(long id){
        return  getId().equals(id);
    }
}
