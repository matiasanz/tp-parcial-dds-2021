package Controladores.Locales.ReporteDeSaldo;

import Dominio.Logger.Logger;
import Dominio.Logger.Loggers;
import Repositorios.RepoEncargados;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class ReporteSaldoAFavorEjecutable {
    private static final RepoEncargados repoDuenios = RepoEncargados.getInstance();
    private static final Logger logger = Loggers.logger;


    public static void execute() throws SchedulerException {
        execute("0 59 23 L * ?");
    }

    public static void execute(String schedule) throws SchedulerException {
        JobDetail job = newJob(RecomendacionSemanal.class)
            .withIdentity("reporte_saldo_a_favor")
            .build();

        Trigger trigger = newTrigger().withIdentity("trigger")
            .withSchedule(cronSchedule(schedule))
            .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(job,trigger);
    }

    public static class RecomendacionSemanal implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext){
            new InformeSaldoController(repoDuenios, logger).execute();
        }
    }
}
