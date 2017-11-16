import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.DBStructureReader;
import services.SQLBuilder;
import services.SQLExecutor;
import services.SQLWriter;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Created by tkaczenko on 11.10.16.
 */
public class AppTest {
    private static final String SQL_SOURCE = "src/test/resources/world.sql";
    private static final String SQL_DESTINATION = "src/test/resources/worldOwn.sql";

    private static final String BASE_DB = "jdbc:postgresql://localhost:5432/";
    private static final String SOURCE_DB = "world";
    private static final String DESTINATION_DB = "world_own";

    private SQLBuilder sqlBuilder;
    private SQLExecutor sqlExecutor;
    private List<String> queries;

    @Before
    public void setUp() throws Exception {
        File file = new File(SQL_DESTINATION);
        file.delete();
        Connection connection = DriverManager.getConnection(BASE_DB,
                DBApp.DEFAULT_USER, DBApp.DEFAULT_PASS);
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP DATABASE " + SOURCE_DB);
        statement.executeUpdate("DROP DATABASE " + DESTINATION_DB);
        statement.executeUpdate("CREATE DATABASE " + SOURCE_DB);
        statement.executeUpdate("CREATE DATABASE " + DESTINATION_DB);
        connection.close();
        sqlBuilder = new SQLBuilder();
        sqlExecutor = new SQLExecutor();
    }

    @Test
    public void testSomething() throws Exception {
        createWorldDB();
        writeStructureToFile(readWorldDB());
        createWorldOwn();
    }

    private void createWorldDB() throws IOException, SQLException {
        queries = sqlBuilder.createQueries(SQL_SOURCE);
        sqlExecutor.createConnection(
                BASE_DB + SOURCE_DB, DBApp.DEFAULT_USER, DBApp.DEFAULT_PASS);
        sqlExecutor.execute(queries);
        sqlExecutor.getConnection().close();
    }

    private Database readWorldDB() throws SQLException, IOException, TemplateException {
        Connection connection = DriverManager.getConnection(
                BASE_DB + SOURCE_DB, DBApp.DEFAULT_USER, DBApp.DEFAULT_PASS);
        DBStructureReader reader = new DBStructureReader(connection);
        reader.read();
        return reader.getDatabase();
    }

    private void writeStructureToFile(Database database) throws IOException, TemplateException, SQLException {
        sqlBuilder.setDatabase(database);
        Map<Map<String, Object>, Template> inputTemplate = sqlBuilder.createTemplateTables();
        SQLWriter.writeToSqlFile(inputTemplate, SQL_DESTINATION);
        inputTemplate = sqlBuilder.primaryTemplateTables();
        SQLWriter.writeToSqlFile(inputTemplate, SQL_DESTINATION);
        inputTemplate = sqlBuilder.foreignTemplateTables();
        SQLWriter.writeToSqlFile(inputTemplate, SQL_DESTINATION);
    }

    private void createWorldOwn() throws SQLException, IOException, TemplateException {
        sqlExecutor.createConnection(
                BASE_DB + DESTINATION_DB, DBApp.DEFAULT_USER, DBApp.DEFAULT_PASS);
        List<String> queries = sqlBuilder.createQueries(SQL_DESTINATION);
        sqlExecutor.execute(queries);
        sqlExecutor.getConnection().close();
    }

    @After
    public void tearDown() throws Exception {
        sqlExecutor.getConnection().close();
    }

}