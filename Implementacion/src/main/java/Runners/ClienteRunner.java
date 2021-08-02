package Runners;

import Local.CategoriaLocal;
import Local.Duenio;
import MediosContacto.MedioDeContacto;
import MediosContacto.NotificadorMail;
import MediosContacto.NotificadorPush;
import Pedidos.Cupones.CuponDescuento;
import Pedidos.Cupones.CuponDescuentoPorcentaje;
import Pedidos.Cupones.CuponSaldo;
import Pedidos.Cupones.SinCupon;
import Pedidos.EstadoPedido;
import Pedidos.Item;
import Pedidos.Pedido;
import Platos.Combo;
import Platos.PlatoSimple;
import Usuarios.Categorias.CategoriaCliente;
import Usuarios.Categorias.Frecuente;
import Usuarios.Cliente;
import Usuarios.Usuario;
import Utils.Factory.*;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;

public class ClienteRunner {
    public static void main(String[] args){
        Cliente cliente = new Cliente();
        CategoriaCliente frecuente = new Frecuente();
        cliente.setCategoria(frecuente);
        cliente.setNombre("federico");
        cliente.setApellido("martinez");
        cliente.setUsername("fm");
        cliente.setPassword("90");
        cliente.setMail("fede@gmail.com");

        CuponSaldo cuponSaldo = new CuponSaldo(500);
        SinCupon sinCupon = new SinCupon();
        CuponDescuentoPorcentaje cuponDescuentoPorcentaje= new CuponDescuentoPorcentaje(25,7);
        List<CuponDescuento> cupones = Arrays.asList(cuponSaldo,sinCupon,cuponDescuentoPorcentaje);

        cliente.setCupones(cupones);

        cliente.agregarNotificacionPush(ProveedorDeNotif.notificacionResultadoPedido(cliente, EstadoPedido.PENDIENTE));

        NotificadorPush notificadorPush = new NotificadorPush();
        NotificadorMail notificadorMail =new NotificadorMail();

        List<MedioDeContacto> medios = Arrays.asList(notificadorMail,notificadorPush);

        cliente.setMediosDeContacto(medios);

        EntityManager en = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = en.getTransaction();

        transaction.begin();
        en.persist(cliente);
        transaction.commit();
    }
}
