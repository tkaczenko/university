package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by tkaczenko on 05.10.16.
 */
public class SQLExecutor {
    private Connection connection;
    private Statement statement;
    private boolean created;

    public SQLExecutor() {

    }

    public SQLExecutor(Connection connection) {
        this.connection = connection;
    }

    public void execute(List<String> queries) throws SQLException {
        statement = connection.createStatement();
        for (String query :
                queries) {
            statement.executeUpdate(query);
            System.out.println(">>" + query);
        }
        statement.close();
        if (created) {
            connection.close();
        }
    }

    public void createConnection(String location, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(location, user, password);
        created = true;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
