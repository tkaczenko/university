package models;

import java.util.List;

/**
 * Created by tkaczenko on 05.10.16.
 */
public class Database {
    private String name;
    private List<Table> tables;

    public Database(String name) {
        this.name = name;
    }

    public Database(String name, List<Table> tables) {
        this.name = name;
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
