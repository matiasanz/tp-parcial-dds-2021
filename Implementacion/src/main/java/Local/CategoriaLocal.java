package Local;

import java.util.Arrays;

public enum CategoriaLocal {
    PARRILLA,
    DESAYUNO,
    COMIDA_RAPIDA,
    VEGANO,
    ARABE,
    ORIENTAL,
    VEGANA;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
