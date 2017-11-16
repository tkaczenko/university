package command;

/**
 * Created by tkaczenko on 24.11.16.
 */
public class DeleteContinentCommand extends Command {
    private String continent;

    public DeleteContinentCommand(String continent) {
        this.continent = continent;
    }

    @Override
    public void execute() {
        service.deleteContinent(continent);
    }
}
