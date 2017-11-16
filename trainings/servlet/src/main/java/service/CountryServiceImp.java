package service;

import country.Country;
import country.CountryDAOImp;

import java.util.List;

/**
 * Created by tkaczenko on 02.12.16.
 */
public class CountryServiceImp implements CountryService {
    private CountryDAOImp countryDAO;

    public CountryServiceImp() {
        countryDAO = new CountryDAOImp();
    }

    @Override
    public void create(Country country) {
        countryDAO.persist(country);
    }

    @Override
    public Country delete(String countryCode) {
        Country country = countryDAO.find(countryCode);
        countryDAO.delete(country);
        return country;
    }

    @Override
    public Country findOne(String countryCode) {
        return countryDAO.find(countryCode);
    }

    @Override
    public List<Country> findAll() {
        return countryDAO.findAll();
    }
}