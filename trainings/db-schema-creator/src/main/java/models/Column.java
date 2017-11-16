package models;

/**
 * Created by tkaczenko on 05.10.16.
 */
public class Column {
    private String name;
    private String type;
    private boolean primaryKey;
    private boolean nullable;
    private int size;
    private int precision;
    private int scale;

    public Column(String name, String type, int size, boolean nullable, int precision, int scale) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.nullable = nullable;
        this.precision = precision;
        this.scale = scale;
    }

    public Column(String name, String type, boolean primaryKey, int size) {
        this.name = name;
        this.type = type;
        this.primaryKey = primaryKey;
        this.size = size;
    }

    public Column(String name, String type, boolean primaryKey, boolean nullable, int size) {
        this.name = name;
        this.type = type;
        this.primaryKey = primaryKey;
        this.nullable = nullable;
        this.size = size;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public boolean isNullable() {
        return nullable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
