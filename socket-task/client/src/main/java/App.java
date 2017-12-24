import client.Client;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by tkaczenko on 26.03.17.
 */
public class App {
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 9000;

    private static String host = DEFAULT_HOST;
    private static int port = DEFAULT_PORT;
    private static Client client;

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            host = args[0];
            if (args.length >= 2) {
                port = Integer.parseInt(args[1]);
            }
        }
        client = new Client(host, port);
        client.setUpConnection();
        showMenu();
        String command;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.print("Pls, write command: ");
            command = scanner.nextLine();
            if ("exit".equals(command)) {
                flag = false;
            } else {
                check(command);
            }
        }
        client.tearDownConnection();
    }

    private static void check(String command) {
        if (command.contains("save")) {
            String message = client.save(command);
            System.out.println(message);
            showMenu();
        }
    }

    private static void showMenu() {
        System.out.println("Menu:");
        StringBuilder sb = new StringBuilder();
        sb.append("save – записать сообщение\n");
        System.out.println(sb.toString());
    }
}
