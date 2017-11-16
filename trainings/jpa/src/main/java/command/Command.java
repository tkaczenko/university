package command;

import service.WorldService;

/**
 * Created by tkaczenko on 24.11.16.
 */
public abstract class Command {
    protected WorldService service = new WorldService();

    public abstract void execute();
}
