import MediosContacto.NotificadorPush;
import Pedidos.Pedido;
import Usuarios.Categorias.*;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Utils.Exceptions.PendingException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class CategoriasTest {
    Primerizo primerizo = new Primerizo();
    Ocasional ocasional = new Ocasional();
    Habitual habitual = new Habitual();
    Frecuente frecuente = new Frecuente();

    Cliente cliente;

    @Before
    public void init() {
        cliente = new Cliente();
        cliente.agregarMedioDeContacto(new NotificadorPush());
    }

    @Test
    public void clienteAlCrearseObtieneCategoriaInicial() {
        assertEquals(primerizo.getNombre(), cliente.getCategoria().getNombre());
    }

    @Test
    public void dePrimerizoPasaAOcasional(){
        int pedidosPrimerizo = Primerizo.pedidosParaCambio;
        subirDeCategoriaValidando(primerizo, cliente, pedidosPrimerizo);
        assertEquals(ocasional.getNombre(), cliente.getCategoria().getNombre());
        assertEquals(1, cliente.getNotificacionesPush().size());
    }

    @Test
    public void deOcasionalPasaAFrecuente(){
        int pedidosOcasional = Ocasional.pedidosParaCambio;
        cliente.setCategoria(new Ocasional());
        subirDeCategoriaValidando(ocasional, cliente, pedidosOcasional);
        assertEquals(frecuente.getNombre(), cliente.getCategoria().getNombre());
        assertEquals(1, cliente.getNotificacionesPush().size());
    }

    @Test
    public void deFrecuentePasaAHabitual(){
        int pedidosFrecuente = Frecuente.pedidosParaCambio;
        cliente.setCategoria(new Frecuente());
        subirDeCategoriaValidando(frecuente, cliente, pedidosFrecuente);

        assertEquals(habitual.getNombre(), cliente.getCategoria().getNombre());
        assertEquals(1, cliente.getNotificacionesPush().size());
    }

    @Test
    public void primerizoNoTieneNingunDescuento(){
        rangoPrecios().forEach(
            precio ->
            assertEquals(0.0, primerizo.descuentoPorCategoria(precio), 0)
        );
    }

    @Test
    public void ocasionalCalculaBienElDescuento(){
        double maxError = 0.00001;
        assertEquals(10.0, ocasional.descuentoPorCategoria(100.0), maxError);
        assertEquals(50.0, ocasional.descuentoPorCategoria(500.0), maxError);
        assertEquals(100.0, ocasional.descuentoPorCategoria(1000.0), maxError);
    }

    @Test
    public void frecuenteCalculaBienElDescuento(){
        throw new PendingException("TO-DO");
    }

    @Test
    public void habitualCalculaBienElDescuento(){
        throw new PendingException("TO-DO");
    }

    private List<Double> rangoPrecios(){
        return IntStream.range(0, 100).mapToDouble(d->d*100).boxed().collect(Collectors.toList());
    }
    private void subirDeCategoriaValidando(CategoriaCliente categoriaActual, Cliente cliente, int pedidosCambio){
        for(int i=1; i<=pedidosCambio; i++){
            assertEquals(categoriaActual.getNombre(), cliente.getCategoria().getNombre());
            cliente.agregarPedido(new Pedido());
        }
    }
}