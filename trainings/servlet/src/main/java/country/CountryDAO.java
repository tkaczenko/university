package country;

import common.DAO;

import java.util.List;

/**
 * Created by tkaczenko on 03.12.16.
 */
interface CountryDAO extends DAO<Country> {
    List<Country> findAllByContinent(String continent);

    void deleteAllCountriesByContinent(String continent);

    Country find(String code);
}
