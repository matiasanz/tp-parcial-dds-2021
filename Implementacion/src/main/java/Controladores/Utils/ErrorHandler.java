package Controladores.Utils;

import MediosContacto.MongoHandler;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class ErrorHandler {
    private final String ERROR_TOKEN = "error";
    //TODO: Ver si se puede pasar algun redirect: hay bastante codigo repetido

    MongoHandler mongoHandler = new MongoHandler();

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
        System.out.println("usuario :" + req.queryParams("username"));
        if(intentos>=2){
            mongoHandler.insertarUsuario(intentos,req.queryParams("username"));
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
