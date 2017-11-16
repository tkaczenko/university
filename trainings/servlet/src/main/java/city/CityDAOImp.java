package city;

import common.JpaDAOImp;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by tkaczenko on 02.12.16.
 */
public class CityDAOImp extends JpaDAOImp<City> implements CityDAO {
    public CityDAOImp() {
        super();
        setPersistentClass(City.class);
    }

    @Override
    public List<City> findAllByCountryName(String countryName) {
        Query query = entityManager.createQuery("SELECT c FROM City c WHERE c.country.name = :name");
        query.setParameter("name", countryName);
        return query.getResultList();
    }
}
