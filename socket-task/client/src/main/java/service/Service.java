package service;

import client.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

    private Executor executor;

    public Service(Executor executor) {
        this.executor = executor;
    }

    public String save(String command) {
        return executor.execute(command);
    }
}
