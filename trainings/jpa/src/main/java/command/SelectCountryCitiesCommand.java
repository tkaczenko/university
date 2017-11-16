package command;

import model.City;

import java.util.List;

/**
 * Created by tkaczenko on 24.11.16.
 */
public class SelectCountryCitiesCommand extends Command {
    private String countryName;

    public SelectCountryCitiesCommand(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public void execute() {
        List<City> cities = service.getCitiesByCountry(countryName);
        System.out.printf("%20s%3s%38s", "City.name", "City.countrycode", "Country.name");
        cities.forEach(city -> System.out.printf(
                "%3s%38s%38s\n", city.getName(), "sds"  /*city.getCountry().getCode(), city.getCountry().getName()*/
                )
        );
    }
}
