package command;

import model.Country;

/**
 * Created by tkaczenko on 24.11.16.
 */
public class InsertCountryCommand extends Command {
    private String code;
    private String name;

    public InsertCountryCommand(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public void execute() {
        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        service.insertCountry(country);
    }
}
