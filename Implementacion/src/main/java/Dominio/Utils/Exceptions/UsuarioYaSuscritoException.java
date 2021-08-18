package Dominio.Utils.Exceptions;

import Dominio.Local.Local;
import Dominio.Usuarios.Cliente;

public class UsuarioYaSuscritoException extends RuntimeException {
    public UsuarioYaSuscritoException(Local local, Cliente nuevoSuscriptor) {
        super("El usuario "+nuevoSuscriptor.getUsername()+" ya se encuentra suscripto al local "+local.getNombre());
    }
}
