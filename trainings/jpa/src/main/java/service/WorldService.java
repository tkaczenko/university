package service;

import model.City;
import model.Country;
import repository.CityDAO;
import repository.CountryDAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by tkaczenko on 24.11.16.
 */
public class WorldService {
    private EntityManager em = Persistence.createEntityManagerFactory("JPATask").createEntityManager();

    public List<Country> getCountryWithCapitals() {
        CountryDAO countryDAO = new CountryDAO(em);
        List<Country> countries = countryDAO.findAll();
        return countries;
    }

    public List<City> getCitiesByCountry(String countryName) {
        CityDAO cityDAO = new CityDAO(em);
        return cityDAO.findAllByCountryName(countryName);
    }

    public void insertCountry(Country country) {
        CountryDAO countryDAO = new CountryDAO(em);
        em.getTransaction().begin();
        countryDAO.create(country);
        em.getTransaction().commit();
    }

    public void updateCapitalOfCountry(String countryCode, City city) {
        CountryDAO countryDAO = new CountryDAO(em);
        CityDAO cityDAO = new CityDAO(em);
        em.getTransaction().begin();
        City find = cityDAO.findOne(city.getId());
        if (find != null) {
            Country country = countryDAO.findOne(countryCode);
            country.setCapital(city);
        } else {
            cityDAO.create(city);
        }
        if (find == null) {
            cityDAO.create(city);
        }
        Country country = countryDAO.findOne(countryCode);
        country.setCapital(city);
        countryDAO.update(country);
        em.getTransaction().commit();
    }

    public void deleteContinent(String continent) {
        CountryDAO countryDAO = new CountryDAO(em);
        em.getTransaction().begin();
        countryDAO.deleteAllCountriesByContinent(continent);
        em.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
