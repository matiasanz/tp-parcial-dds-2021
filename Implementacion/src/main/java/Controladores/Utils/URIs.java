package Controladores.Utils;

public interface URIs {
    String LOGIN = "/";
    String HOME = "/home";
    String LOCALES = "/locales";
    String SIGNUP = "/signup";

    static String LOCAL(Long idLocal) {
        return LOCALES+"/"+idLocal;
    }

    static String CARRITO(Long id) {
        return LOCALES+"/"+id;
    }
}
