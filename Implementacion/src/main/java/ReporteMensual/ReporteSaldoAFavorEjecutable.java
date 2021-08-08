package ReporteMensual;

import Mongo.Logger;
import Mongo.Loggers;
import Repositorios.RepoDuenios;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class ReporteSaldoAFavorEjecutable {
    private static final RepoDuenios repoDuenios = RepoDuenios.getInstance();
    private static final Logger logger = Loggers.loggerReportes;

    public static void main(String[] args) throws SchedulerException {
        JobDetail job = newJob(RecomendacionSemanal.class)
            .withIdentity("reporte_saldo_a_favor")
            .build();

        Trigger trigger = newTrigger().withIdentity("trigger")
            .withSchedule(cronSchedule("0/15 * * * * ?"))
//            .withSchedule(cronSchedule("0 59 23 L * ?"))
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
