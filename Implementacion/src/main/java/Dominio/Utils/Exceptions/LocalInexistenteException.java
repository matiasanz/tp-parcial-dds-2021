package Dominio.Utils.Exceptions;

public class LocalInexistenteException extends RuntimeException {
    public LocalInexistenteException(long id) {
        super("El local "+id+" no se encuentra registrado");
    }
}
