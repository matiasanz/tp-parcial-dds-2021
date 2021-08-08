package Main;

import ReporteMensual.ReporteSaldoAFavorEjecutable;
import org.quartz.SchedulerException;

public class MainClass {

    public static void main(String[] args) throws SchedulerException {
        //Bootstrap.main(args);
        ReporteSaldoAFavorEjecutable.main(args);
        new RoutesClientes(8080).execute();
        new RoutesLocales(8081).execute();
    }
}