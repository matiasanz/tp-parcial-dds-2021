package Mongo;

import Repositorios.Templates.Colecciones.MongoDB;
import org.bson.Document;

public class Loggers {
    public static Logger loggerSeguridad = mongoLogger("seguridad");
    public static Logger loggerEventos = mongoLogger("eventos");
    public static Logger loggerReportes = mongoLogger("reportes");

    public static Logger mongoLogger(String asunto){
        return new Logger(asunto, new MongoDB<>(asunto, Document.class));
    }
}
