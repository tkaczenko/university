package city;

import common.DAO;

import java.util.List;

/**
 * Created by tkaczenko on 03.12.16.
 */
interface CityDAO extends DAO<City> {
    List<City> findAllByCountryName(String countryName);
}
