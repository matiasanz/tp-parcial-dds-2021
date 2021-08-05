package db.Inserciones;

import Local.Local;
import Utils.Factory.ProveedorDeLocales;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LocalRunner {
    public static void main(String[] args){
        Local local = ProveedorDeLocales.cincoEsquinas();

        EntityManager en = PerThreadEntityManagers.getEntityManager();
        EntityTransaction transaction = en.getTransaction();

        transaction.begin();
        en.persist(local);
        transaction.commit();
    }
}
