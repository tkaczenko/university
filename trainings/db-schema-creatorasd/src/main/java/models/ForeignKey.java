package models;

/**
 * Created by tkaczenko on 20.10.16.
 */
public class ForeignKey extends Key {
    private String pkTableName;
    private String pkColumnName;

    public ForeignKey(String name) {
        super(name);
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }
}
