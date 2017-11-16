package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by tkaczenko on 07.12.16.
 */
public class EntityManagerUtil {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPATask");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
