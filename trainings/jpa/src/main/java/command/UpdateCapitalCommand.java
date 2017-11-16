package command;

import model.City;

/**
 * Created by tkaczenko on 24.11.16.
 */
public class UpdateCapitalCommand extends Command {
    private String countryCode;
    private int capitalID;

    public UpdateCapitalCommand(String countryCode, int capitalID) {
        this.countryCode = countryCode;
        this.capitalID = capitalID;
    }

    @Override
    public void execute() {
        City capital = new City();
        capital.setId(capitalID);
        service.updateCapitalOfCountry(countryCode, capital);
    }
}
