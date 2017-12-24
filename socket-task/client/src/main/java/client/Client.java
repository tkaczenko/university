package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by tkaczenko on 26.03.17.
 */
public class Client extends Executor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    private final Service service = new Service(this);
    private String hostIp;
    private int hostPort;

    public Client(String hostIp, int hostPort) {
        this.hostIp = hostIp;
        this.hostPort = hostPort;
    }

    public void setUpConnection() {
        try {
            Socket client = new Socket(hostIp, hostPort);
            socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            socketWriter = new PrintWriter(client.getOutputStream());
        } catch (UnknownHostException e) {
            LOGGER.error("Error setting up socket connection: unknown host at " + hostIp + ":" + hostPort, e);
        } catch (IOException e) {
            LOGGER.error("Error setting up socket connection: ", e);
        }
    }

    public void tearDownConnection() {
        try {
            socketWriter.close();
            socketReader.close();
        } catch (IOException e) {
            LOGGER.error("Error tearing down socket connection: ", e);
        }
    }

    public String save(String command) {
        return service.save(command);
    }
}
