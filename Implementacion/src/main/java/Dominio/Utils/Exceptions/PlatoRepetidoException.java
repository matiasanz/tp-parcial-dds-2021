package Dominio.Utils.Exceptions;

public class PlatoRepetidoException extends RuntimeException {
    public PlatoRepetidoException(String nombre) {
        super("Ya tiene un plato registrado con el nombre <<"+nombre+">>");
    }
}
