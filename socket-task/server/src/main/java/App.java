import server.Server;

import java.util.Scanner;

/**
 * Created by tkaczenko on 26.03.17.
 */
public class App {
    private static final int DEFAULT_PORT = 9000;

    private static int port = DEFAULT_PORT;

    public static void main(String[] args) {
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        Server server = new Server(port);
        new Thread(server).start();
        showMenu();
        String command;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.print("Pls, write command: ");
            command = scanner.nextLine().toLowerCase();
            if ("stop".equals(command)) {
                flag = false;
            } else if ("help".equals(command)) {
                showCommands();
                showMenu();
            }
        }
        System.out.println("Stopping Server");
        server.stop();
    }

    private static void showMenu() {
        System.out.println("Menu:");
        String sb = "stop – остановить сервер\n" +
                "help – показать запросы, которые поддерживает сервер\n";
        System.out.println(sb);
    }

    private static void showCommands() {
        System.out.println("Supported commands:");
        StringBuilder sb = new StringBuilder();
        sb.append("save – записать сообщение\n");
        System.out.println(sb.toString());
    }
}
