package Dominio.Utils.Exceptions;

public class NombreOcupadoException extends RuntimeException {
    public NombreOcupadoException(String deQue, String nombre) {
        super("Ya existe un "+deQue+" con el nombre "+nombre);
    }
}
