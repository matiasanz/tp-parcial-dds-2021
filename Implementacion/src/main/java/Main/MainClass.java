package Main;

import Mongo.EventLogger;

public class MainClass {
    public static void main(String[] args){
        Bootstrap.main(args);
        EventLogger.mongoHabilitado = false;
        new RoutesClientes(8080).execute();
        new RoutesLocales(8081).execute();
    }
}
