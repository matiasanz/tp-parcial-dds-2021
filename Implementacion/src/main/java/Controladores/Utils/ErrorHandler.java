package Controladores.Utils;

import Mongo.Logger;
import Mongo.Loggers;
import spark.Request;

import java.util.Optional;

import static Utils.Factory.ProveedorDeLogs.logFalloAutenticacion;

public class ErrorHandler {
    private final String ERROR_TOKEN = "error";

    Logger logger = Loggers.loggerSeguridad;

    public void setMensaje(Request req, String mensaje){
        req.session().attribute(ERROR_TOKEN, mensaje);
    }

    public String getMensaje(Request req){
        String mensaje = req.session().attribute(ERROR_TOKEN);
        req.session().removeAttribute(ERROR_TOKEN);
        return mensaje;
    }

    private final String INTENTOS_TOKEN ="intentos";

    public void notificarIntentoFallido(Request req){
        int intentos = getIntentosAcumulados(req)+1;

        if(intentos>=5){
            logger.loguear(logFalloAutenticacion(intentos,req.queryParams("username")));
        }

        req.session().attribute(INTENTOS_TOKEN, intentos);
    }

    public void notificarIntentoCorrecto(Request req){
        if(sessionAttribute(INTENTOS_TOKEN, req)!=null){
            req.session().removeAttribute(INTENTOS_TOKEN);
        }
    }

    private int getIntentosAcumulados(Request req){
        return Optional.ofNullable((Integer) sessionAttribute(INTENTOS_TOKEN, req))
                .orElse(0);
    }

    private <T> T sessionAttribute(String token, Request req){
        return req.session().attribute(token);
    }
}
