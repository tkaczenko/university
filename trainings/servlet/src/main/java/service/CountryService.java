package service;

import country.Country;

import java.util.List;

/**
 * Created by tkaczenko on 03.12.16.
 */
public interface CountryService {
    void create(Country country);

    Country delete(String countryCode);

    Country findOne(String countryCode);

    List<Country> findAll();
}
