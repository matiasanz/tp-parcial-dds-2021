package MediosContacto;

import Cliente.Cliente;

public interface MedioDeContacto {
    public void notificar(Cliente cliente, Notificacion notificacion);
}
