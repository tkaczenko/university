package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkaczenko on 21.10.16.
 */
abstract class Key {
    private String name;
    private List<String> columnNames = new ArrayList<>();

    public Key(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

}
