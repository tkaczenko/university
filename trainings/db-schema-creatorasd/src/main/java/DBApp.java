import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.Database;
import services.DBStructureReader;
import services.SQLBuilder;
import services.SQLExecutor;
import services.SQLWriter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by tkaczenko on 29.09.16.
 */
public class DBApp {
    private static final String VENDOR_NAME = "jdbc:postgresql:/";
    private static final String DEFAULT_LOCATION = "/localhost:5432/test";
    public static final String DEFAULT_USER = "postgres";
    public static final String DEFAULT_PASS = "1234";

    public static void main(String[] args) {
        String location = DEFAULT_LOCATION;
        String user = DEFAULT_USER;
        String password = DEFAULT_PASS;

        if (args.length != 1 || args.length != 3) {
            System.out.println("App will use default database");
        } else {
            location = args[0];
            if (args.length == 3) {
                user = args[1];
                password = args[2];
            }
        }

        try (Connection connection = DriverManager.getConnection(
                VENDOR_NAME + location, user, password)) {
            DBStructureReader reader = new DBStructureReader(connection);
            System.out.println("Connected...");
            try {
                reader.read();
                Database database = reader.getDatabase();
                System.out.println("Structure of database was gotten...");
                try {
                    SQLBuilder sqlBuilder = new SQLBuilder(database);
                    try {
                        Map<Map<String, Object>, Template> inputTemplate = sqlBuilder.createTemplateTables();
                        System.out.println("Queries were created...");
                        try {
                            SQLWriter.writeToSqlFile(inputTemplate, null);
                            System.out.println("SQL file was written...");

                            Scanner scanner = new Scanner(System.in);

                            System.out.println("DO YOU WANT TO EXECUTE SQL FILE?\t Y/N");
                            char c = scanner.nextLine().charAt(0);
                            if (c == 'Y' || c == 'y') {
                                SQLExecutor sqlExecutor = new SQLExecutor();

                                System.out.println("DO YOU WANT TO SET location, user and password?\t Y/N");
                                c = scanner.nextLine().charAt(0);

                                try {
                                    if (c == 'Y' || c == 'y') {
                                        System.out.println("\tWrite location: ");
                                        location = scanner.nextLine();
                                        System.out.println("\tWrite user: ");
                                        user = scanner.nextLine();
                                        System.out.println("\tWrite password: ");
                                        password = scanner.nextLine();
                                        scanner.close();
                                        sqlExecutor.createConnection(location, user, password);
                                    } else if (c == 'N' || c == 'n') {
                                        sqlExecutor.createConnection("jdbc:postgresql://localhost:5432/test1",
                                                DEFAULT_USER, DEFAULT_PASS);
                                    }
                                    System.out.println("Connected...");
                                    try {
                                        List<String> queries = sqlBuilder.createQueries(SQLWriter.DEFAULT_FILE_NAME);
                                        System.out.println("Queries were read...");
                                        try {
                                            sqlExecutor.execute(queries);
                                            System.out.println("Successful execution...");
                                        } catch (SQLException e) {
                                            System.out.println("Cannot execute queries! Check output console");
                                            e.printStackTrace();
                                            return;
                                        }
                                    } catch (IOException e) {
                                        System.out.println("Cannot read SQL file! Check output console");
                                        e.printStackTrace();
                                        return;
                                    }
                                } catch (SQLException e) {
                                    System.out.println("Connection Failed! Check output console");
                                    e.printStackTrace();
                                    return;
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Cannot write into file! Check output console");
                            e.printStackTrace();
                            return;
                        } catch (TemplateException e) {
                            System.out.println("Something was wrong with template! Check output console");
                            e.printStackTrace();
                            return;
                        }
                    } catch (IOException e) {
                        System.out.println("Cannot read template file! Check output console");
                        e.printStackTrace();
                        return;
                    }
                } catch (IOException e) {
                    System.out.println("Cannot init config for Apache FreeMarker! Check output console");
                    e.printStackTrace();
                    return;
                }
            } catch (SQLException e) {
                System.out.println("Cannot read meta data! Check output console");
                e.printStackTrace();
                return;
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
    }
}
