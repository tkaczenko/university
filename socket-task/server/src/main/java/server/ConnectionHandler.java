package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by tkaczenko on 26.03.17.
 */
class ConnectionHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);
    private final Service service = new Service();

    private Socket socketToHandle;
    private PrintWriter streamWriter;
    private BufferedReader streamReader;

    private volatile boolean isRunning = true;

    ConnectionHandler(Socket socketToHandle) {
        this.socketToHandle = socketToHandle;
    }

    @Override
    public void run() {
        try {
            streamWriter = new PrintWriter(socketToHandle.getOutputStream());
            streamReader = new BufferedReader(new InputStreamReader(socketToHandle.getInputStream()));

            String clientCommand = "";
            while (isRunning) {
                try {
                    clientCommand = streamReader.readLine();
                } catch (IOException e) {
                    logger.error("Cannot write service", e);
                }
                if (clientCommand == null || clientCommand.isEmpty()) {
                    continue;
                }
                if ("exit".equals(clientCommand.toLowerCase())) {
                    System.out.println("Disconnect");
                    break;
                } else {
                    check(clientCommand);
                }
            }
        } catch (IOException e) {
            logger.error("Cannot get streams", e);
        } finally {
            streamWriter.close();
            try {
                streamReader.close();
            } catch (IOException e) {
                logger.error("Cannot close InputStream", e);
            }
        }
    }

    private void check(String command) throws IOException {
        if (command.contains("save")) {
            String[] commandDetails = command.split(" ");
            String folderName = socketToHandle.getRemoteSocketAddress().toString();
            String fileName = commandDetails[1];
            String mess = commandDetails[2];
            String message;
            if (service.save(folderName, fileName, mess)) {
                message = "Message is saved at " + folderName + " in " + fileName;
            } else {
                message = "Is not saved";
            }
            write(message);
        }
    }

    private void write(String message) {
        streamWriter.println(message);
        streamWriter.flush();
    }
}
