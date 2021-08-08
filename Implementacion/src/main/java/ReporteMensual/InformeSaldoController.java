package ReporteMensual;

import Controladores.Utils.Transaccional;
import Local.*;
import Mongo.Logger;
import Pedidos.EstadoPedido;
import Pedidos.Pedido;
import Repositorios.RepoDuenios;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import static Utils.Factory.ProveedorDeLogs.logSaldoAFavor;

import java.time.LocalDate;

import static Utils.Factory.ProveedorDeNotif.notificacionSaldoAFavor;

public class InformeSaldoController implements Transaccional {
    RepoDuenios repoDuenios;
    Logger logger;

    public InformeSaldoController(RepoDuenios repoDuenios, Logger logger){
        this.repoDuenios=repoDuenios;
        this.logger = logger;
    }

    public void execute(){
        LocalDate fechaActual = LocalDate.now();

        for (Duenio duenio : repoDuenios.getAll()) {
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
