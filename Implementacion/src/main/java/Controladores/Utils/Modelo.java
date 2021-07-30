package Controladores.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class Modelo extends HashMap<String, Object>{
    public Modelo(){}
    public Modelo(String key, Object value){
        this();
        con(key, value);
    }

    public Modelo con(String key, int value){ return con(key, new Integer(value)); }
    public Modelo con(String key, double value){ return con(key, new Double(value)); }

    public Modelo con(String key, Object value){
        if(value instanceof Double){
            value = redondear((Double) value);
        }

        put(key, value);
        return this;
    }

    public Modelo con(Modelo modelo){
//      validarNoRepetidos(modelo);
        this.putAll(modelo);
        return this;
    }

    private Double redondear(double precio){
        BigDecimal bd = BigDecimal.valueOf(precio);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    private void validarNoRepetidos(Modelo modelo){
        if(keySet().stream().anyMatch(modelo::containsKey))
            throw new RuntimeException("tenia "+keySet()+"y me quisieron poner "+modelo.keySet());
    }
}
