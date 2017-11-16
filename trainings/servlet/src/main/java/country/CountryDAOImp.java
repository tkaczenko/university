package country;

import common.JpaDAOImp;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by tkaczenko on 02.12.16.
 */
public class CountryDAOImp extends JpaDAOImp<Country> implements CountryDAO {
    public CountryDAOImp() {
        super();
        setPersistentClass(Country.class);
    }

    @Override
    public List<Country> findAllByContinent(String continent) {
        Query query = entityManager.createQuery("SELECT c FROM Country c WHERE c.continent = :continent");
        query.setParameter("continent", continent);
        return query.getResultList();
    }

    @Override
    public void deleteAllCountriesByContinent(String continent) {
        List<Country> countries = findAllByContinent(continent);
        countries.forEach(this::delete);
    }

    @Override
    public Country find(String code) {
        return entityManager.find(Country.class, code);
    }
}
