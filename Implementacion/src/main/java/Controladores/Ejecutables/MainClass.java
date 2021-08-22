package Controladores.Ejecutables;

import Controladores.Locales.ReporteDeSaldo.ReporteSaldoAFavorEjecutable;
import Controladores.Routes.RoutesClientes;
import Controladores.Routes.RoutesLocales;
import org.quartz.SchedulerException;

public class MainClass {

    public static void main(String[] args) throws SchedulerException {
        ReporteSaldoAFavorEjecutable.execute();
        new RoutesClientes(8080).execute();
        new RoutesLocales(8081).execute();
    }
}