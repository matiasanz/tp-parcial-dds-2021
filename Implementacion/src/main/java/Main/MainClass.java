package Main;

import Mongo.EventLogger;
import TareaProgramada.ReporteSaldoAFavorEjecutable;
import org.quartz.SchedulerException;

public class MainClass {
    public static void main(String[] args) throws SchedulerException {
        Bootstrap.main(args);
        EventLogger.mongoHabilitado = false;
        ReporteSaldoAFavorEjecutable.main(args);
        new RoutesClientes(8080).execute();
        new RoutesLocales(8081).execute();
    }
}
