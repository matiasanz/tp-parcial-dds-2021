package Controladores.Utils;

public interface URIs {
    String LOGIN = "/";
    String HOME = "/home";
    String LOCALES = "/locales";
    String SIGNUP = "/signup";
    String PEDIDOS = "/pedidos";

    static String LOCAL(Long idLocal) {
        return LOCALES+"/"+idLocal;
    }

    static String PEDIDO(Long id) {
        return PEDIDOS+"/"+id;
    }
}
