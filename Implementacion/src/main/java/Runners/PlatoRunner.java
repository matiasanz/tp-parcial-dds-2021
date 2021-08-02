package Runners;

import Platos.PlatoSimple;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;

public class PlatoRunner {
    public static void main(String[] args){
        PlatoSimple platoSimple = new PlatoSimple();
        platoSimple.setPrecio(897.5);
        platoSimple.setNombre("hamburguesa");
        platoSimple.setDescripcion("Medallon de carne entre panes");
        platoSimple.setIngredientes(Arrays.asList("pan", "carne"));

        EntityManager en = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = en.getTransaction();

        transaction.begin();
        en.persist(platoSimple);
        transaction.commit();
    }
}
