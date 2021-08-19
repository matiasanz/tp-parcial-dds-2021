package Controladores.Locales.ReporteDeSaldo;

import Controladores.Utils.Transaccional;
import Dominio.Local.*;
import Repositorios.Logger.Logger;
import Dominio.Pedidos.EstadoPedido;
import Dominio.Pedidos.Pedido;
import Repositorios.RepoEncargados;
import Dominio.Usuarios.Encargado;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import static Dominio.Utils.Factory.ProveedorDeLogs.logSaldoAFavor;

import java.time.LocalDate;

import static Dominio.Utils.Factory.ProveedorDeNotif.notificacionSaldoAFavor;

public class InformeSaldoController implements Transaccional {
    RepoEncargados repoDuenios;
    Logger logger;

    public InformeSaldoController(RepoEncargados repoDuenios, Logger logger){
        this.repoDuenios=repoDuenios;
        this.logger = logger;
    }

    public void execute(){
        LocalDate fechaActual = LocalDate.now();

        for (Encargado duenio : repoDuenios.getAll()) {
            Local local = duenio.getLocal();

            PerThreadEntityManagers.getEntityManager().refresh(local);

            Double saldo = calcularSaldoAFavor(local, fechaActual);

            withTransaction(()->{
                duenio.notificar(notificacionSaldoAFavor(duenio, saldo));
            });

            logger.loguear(logSaldoAFavor(duenio, saldo));
        }
    }

    protected Double calcularSaldoAFavor(Local local, LocalDate mes){
        return local
            .pedidosDelMes(mes)
            .stream()
            .filter(p->p.getEstado()==EstadoPedido.ENTREGADO)
            .mapToDouble(Pedido::getDescuento)
            .sum();
    }
}
