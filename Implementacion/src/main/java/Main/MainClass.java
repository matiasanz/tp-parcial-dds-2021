package Main;

public class MainClass {
    public static void main(String[] args){
        Bootstrap.main(args);
        new RoutesClientes(8080).execute();
        new RoutesLocales(8081).execute();
    }
}