package Dominio.Utils.Exceptions;

public class ContraseniasDistintasException extends RuntimeException {
    public ContraseniasDistintasException(){
        super("La segunda contraseña no se corresponde con la primera");
    }
}
