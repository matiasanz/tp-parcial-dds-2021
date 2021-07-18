package MediosContacto;

import Usuarios.Cliente;

public interface MedioDeContacto {
    public void notificar(Cliente cliente, Notificacion notificacion);
}
