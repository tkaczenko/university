package repository;

import model.Country;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tkaczenko on 07.11.16.
 */
public class CountryDAO extends AbstractJpaDAO<Country> {
    public CountryDAO(EntityManager entityManager) {
        super(entityManager);
        setClazz(Country.class);
    }

    public List<Country> findAllByContinent(String continent) {
        Query query = entityManager.createQuery("SELECT c FROM Country c WHERE c.continent = :continent");
        query.setParameter("continent", continent);
        return query.getResultList();
    }

    public void deleteAllCountriesByContinent(String continent) {
        List<Country> countries = findAllByContinent(continent);
        countries.forEach(this::delete);
    }
}
