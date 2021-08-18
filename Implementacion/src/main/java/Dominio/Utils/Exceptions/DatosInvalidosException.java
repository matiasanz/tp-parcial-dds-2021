package Dominio.Utils.Exceptions;

public class DatosInvalidosException extends RuntimeException {
    public DatosInvalidosException(){
        super("Algunos datos no se recibieron correctamente");
    }
}
