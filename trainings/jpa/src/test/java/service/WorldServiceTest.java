package service;

import model.City;
import model.Country;
import org.junit.Before;
import org.junit.Test;
import repository.CityDAO;
import repository.CountryDAO;

import java.util.Collection;
import java.util.List;

import static java.util.Optional.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by tkaczenko on 24.11.16.
 */
public class WorldServiceTest {
    private WorldService service;

    @Before
    public void setUp() throws Exception {
        service = new WorldService();
    }

    @Test
    public void getCountryWithCapitals() throws Exception {
        List<Country> countries = service.getCountryWithCapitals();
        assertThat((Collection) countries, is(not(empty())));
    }

    @Test
    public void getCitiesByCountry() throws Exception {
        List<City> cities = service.getCitiesByCountry("Ukraine");
        assertThat((Collection) cities, is(not(empty())));
    }

    @Test
    public void insertCountry() throws Exception {
        Country expected = new Country(
                "OOO", "Mykolaiv", "Europe", "Solanyje", 1.2, 10000, "solanyje", "monarchyja", "mk"
        );
        service.insertCountry(expected);
        CountryDAO countryDAO = new CountryDAO(service.getEntityManager());
        Country actual = countryDAO.findOne("OOO");
        service.getEntityManager().getTransaction().begin();
        countryDAO.delete(actual);
        service.getEntityManager().getTransaction().commit();
        assertEquals(expected, actual);
    }

    @Test
    public void updateCapitalOfCountry() throws Exception {
        CountryDAO countryDAO = new CountryDAO(service.getEntityManager());
        CityDAO cityDAO = new CityDAO(service.getEntityManager());
        City expected = cityDAO.findOne(3434);
        service.updateCapitalOfCountry("UKR", expected);
        City actual = countryDAO.findOne("UKR").getCapital();
        assertEquals(expected, actual);
    }

    @Test
    public void deleteContinent() throws Exception {
        CountryDAO countryDAO = new CountryDAO(service.getEntityManager());
        String continent = "Africa";
        service.deleteContinent(continent);
        List<Country> countries = countryDAO.findAllByContinent(continent);
        assertEquals(0, countries.size());
    }

}