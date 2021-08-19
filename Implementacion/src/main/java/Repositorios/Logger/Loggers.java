package Repositorios.Logger;

import Repositorios.Templates.Colecciones.MongoDB;
import org.bson.Document;

public class Loggers {
    public static Logger logger = mongoLogger("logs");

    public static Logger mongoLogger(String nombreColeccion){
        return new Logger(new MongoDB<>(nombreColeccion, Document.class));
    }
}
