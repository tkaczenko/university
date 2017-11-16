package models;

import java.util.List;

/**
 * Created by tkaczenko on 05.10.16.
 */
public class Table {
    private String name;
    private List<Column> columns;
    private List<PrimaryKey> primaryKeys;
    private List<ForeignKey> foreignKeys;

    public Table(String name) {
        this.name = name;
    }

    public Table(String name, List<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    public Table(String name, List<Column> columns, List<PrimaryKey> primaryKeys) {
        this.name = name;
        this.columns = columns;
        this.primaryKeys = primaryKeys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<PrimaryKey> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(List<PrimaryKey> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }
}
