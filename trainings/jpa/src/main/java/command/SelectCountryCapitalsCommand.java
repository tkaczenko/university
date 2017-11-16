package command;

import model.City;
import model.Country;

import java.util.List;

/**
 * Created by tkaczenko on 24.11.16.
 */
public class SelectCountryCapitalsCommand extends Command {
    @Override
    public void execute() {
        List<Country> countries = service.getCountryWithCapitals();
        System.out.printf("%3s%38s%38s\n", "ID", "Country", "Capital");
        countries.forEach(country -> {
                    City temp = country.getCapital();
                    System.out.printf(
                            "%3s%38s%38s\n", country.getCode(), country.getName(),
                            (temp != null) ? temp.getName() : "null"
                    );
                }
        );
    }
}
