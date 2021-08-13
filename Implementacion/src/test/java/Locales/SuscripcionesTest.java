package Locales;

import Local.Local;
import MediosContacto.Notificacion;
import MediosContacto.NotificadorPush;
import Usuarios.Cliente;
import Utils.Factory.ProveedorDeClientes;
import Utils.Factory.ProveedorDeLocales;
import Utils.Factory.ProveedorDePlatos;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SuscripcionesTest {
    Cliente suscriptor1;
    Cliente suscriptor2;
    Local local;

    @Before
    public void init() {
        suscriptor1 = ProveedorDeClientes.clienteNotificable("marcos");
        suscriptor1.setId(1L);
        suscriptor2 = ProveedorDeClientes.clienteNotificable("alfonso");
        suscriptor2.setId(2L);
        local = ProveedorDeLocales.localSinPlatos();
        local.agregarSuscriptor(suscriptor1);
        local.agregarSuscriptor(suscriptor2);
    }

    @Test
    public void alCambiarLaDireccionSeNotificaSuscriptores() {
        String asunto = "Cambio de Direccion";
        local.actualizarDireccion("saraza 275");
        assertUnicaNotificacionRecibida(asunto, suscriptor1);
        assertUnicaNotificacionRecibida(asunto, suscriptor2);
    }

    @Test
    public void alAgregarNuevoPlatoSeNotificaSuscriptores() {
        String asunto = "Nuevo Plato";
        local.agregarPlato(ProveedorDePlatos.hamburguesa());
        assertUnicaNotificacionRecibida(asunto, suscriptor1);
        assertUnicaNotificacionRecibida(asunto, suscriptor2);
    }

    @Test
    public void unSuscriptorSeDesuscribeYNoRecibeMasNotificaciones(){
        local.eliminarSuscriptor(suscriptor1);
        local.actualizarDireccion("en frente");
        local.agregarPlato(ProveedorDePlatos.combo());
        assertEquals(0, suscriptor1.getNotificacionesPush().size());
        assertEquals(2, suscriptor2.getNotificacionesPush().size());
        assertNotificacionRecibida("Cambio de Direccion", suscriptor2);
        assertNotificacionRecibida("Nuevo Plato", suscriptor2);
    }

    private void assertUnicaNotificacionRecibida(String asunto, Cliente cliente){
        assertEquals(1, cliente.getNotificacionesPush().size());
        assertNotificacionRecibida(asunto, cliente);
    }

    private void assertNotificacionRecibida(String asunto, Cliente cliente){
        List<Notificacion> notificaciones = cliente.getNotificacionesPush();
        assertTrue(notificaciones.stream().map(Notificacion::getAsunto).anyMatch(asunto::equals));
    }
}
