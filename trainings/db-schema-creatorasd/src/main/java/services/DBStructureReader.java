package services;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tkaczenko on 05.10.16.
 */
public class DBStructureReader {
    private static final Map<String, String> postgreTypes = new HashMap<>();

    static {
        postgreTypes.put("bpchar", "character");
        postgreTypes.put("bool", "boolean");
        postgreTypes.put("float4", "real");
        postgreTypes.put("int2", "smallint");
        postgreTypes.put("int4", "integer");
    }

    private Connection connection;
    private Database database;
    private DatabaseMetaData metaData;

    private String catalog;
    private String schemaPattern;
    private String tableNamePattern;

    private String tableTypes[] = {"TABLE"};

    public DBStructureReader(Connection connection) {
        this.connection = connection;
    }

    public void read() throws SQLException {
        database = new Database(connection.getCatalog());

        metaData = connection.getMetaData();

        List<Table> tables = new ArrayList<>();
        ResultSet resultSet = metaData.getTables(catalog, schemaPattern, tableNamePattern, tableTypes);
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");

            List<PrimaryKey> primaryKeys = getPrimaryKeys(tableName);
            List<ForeignKey> foreignKeys = getForeignKeys(tableName);

            List<Column> columns = getColumns(tableName, null);
            Table table = new Table(tableName, columns, primaryKeys);
            table.setForeignKeys(foreignKeys);

            tables.add(table);
        }

        database.setTables(tables);
    }

    private List<PrimaryKey> getPrimaryKeys(String tableName) throws SQLException {
        List<PrimaryKey> keys = new ArrayList<>();
        ResultSet resultSet = metaData.getPrimaryKeys(catalog, schemaPattern, tableName);
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            String pkName = resultSet.getString("PK_NAME");

            boolean updated = false;
            for (PrimaryKey key :
                    keys) {
                if (key.getName().equals(pkName)) {
                    key.getColumnNames().add(columnName);
                    updated = true;
                }
            }

            if (!updated) {
                PrimaryKey primaryKey = new PrimaryKey(pkName);
                primaryKey.getColumnNames().add(columnName);
                keys.add(primaryKey);
            }
        }
        return keys;
    }

    private List<ForeignKey> getForeignKeys(String tableName) throws SQLException {
        List<ForeignKey> keys = new ArrayList<>();
        ResultSet resultSet = metaData.getImportedKeys(catalog, schemaPattern, tableName);
        while (resultSet.next()) {
            String pkTableName = resultSet.getString("PKTABLE_NAME");
            String pkColumnName = resultSet.getString("PKCOLUMN_NAME");
            String fkColumnName = resultSet.getString("FKCOLUMN_NAME");

            String fkName = resultSet.getString("FK_NAME");

            boolean updated = false;
            for (ForeignKey key :
                    keys) {
                if (key.getName().equals(fkName)) {
                    key.getColumnNames().add(fkColumnName);
                }
            }

            if (!updated) {
                ForeignKey foreignKey = new ForeignKey(fkName);
                foreignKey.setPkTableName(pkTableName);
                foreignKey.setPkColumnName(pkColumnName);
                foreignKey.getColumnNames().add(fkColumnName);
                keys.add(foreignKey);
            }
        }
        return keys;
    }

    private List<Column> getColumns(String tableName, String columnNamePattern) throws SQLException {
        List<Column> columns = new ArrayList<>();
        ResultSet resultSet = metaData.getColumns(catalog, schemaPattern, tableName, columnNamePattern);
        Statement statement = connection.createStatement();
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            String dataType = resultSet.getString("TYPE_NAME");

            int nullableType = resultSet.getInt("NULLABLE");
            int columnSize = resultSet.getInt("COLUMN_SIZE");
            int scale = resultSet.getInt("DECIMAL_DIGITS");

            boolean nullable = false;
            if (nullableType == metaData.columnNullable) {
                nullable = true;
            } else if (nullableType == metaData.columnNoNulls) {
                nullable = false;
            } else if (nullableType == metaData.columnNullableUnknown) {
                nullable = true;
            }

            Column column = new Column(columnName,
                    postgreTypes.containsKey(dataType) ? postgreTypes.get(dataType) : dataType, columnSize,
                    nullable, columnSize, scale);

            columns.add(column);
        }
        return columns;
    }

    public Database getDatabase() {
        return database;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getSchemaPattern() {
        return schemaPattern;
    }

    public void setSchemaPattern(String schemaPattern) {
        this.schemaPattern = schemaPattern;
    }

    public String getTableNamePattern() {
        return tableNamePattern;
    }

    public void setTableNamePattern(String tableNamePattern) {
        this.tableNamePattern = tableNamePattern;
    }

    public String[] getTableTypes() {
        return tableTypes;
    }

    public void setTableTypes(String[] tableTypes) {
        this.tableTypes = tableTypes;
    }
}
