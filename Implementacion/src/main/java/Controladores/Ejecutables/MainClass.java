package Controladores.Ejecutables;

import Controladores.Locales.ReporteDeSaldo.ReporteSaldoAFavorEjecutable;
import Controladores.Routes.RoutesClientes;
import Controladores.Routes.RoutesLocales;
import org.quartz.SchedulerException;

public class MainClass {

    public static void main(String[] args) throws SchedulerException {
        Bootstrap.main(args);
        ReporteSaldoAFavorEjecutable.execute(cadaNSegundos(120));
        new RoutesClientes(8080).execute();
        new RoutesLocales(8081).execute();
    }

    private static String cadaNSegundos(int segundos){
        return "0/" +segundos+ " * * * * ?";
    }
}