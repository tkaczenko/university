package repository;

import model.City;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tkaczenko on 07.11.16.
 */
public class CityDAO extends AbstractJpaDAO<City> {
    public CityDAO(EntityManager entityManager) {
        super(entityManager);
        setClazz(City.class);
    }

    public List<City> findAllByCountryName(String countryName) {
        Query query = entityManager.createQuery("SELECT c FROM City c WHERE c.country.name = :name");
        query.setParameter("name", countryName);
        return query.getResultList();
    }
}
