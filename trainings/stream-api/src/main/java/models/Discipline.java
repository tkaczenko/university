package models;

/**
 * Model of discipline's data
 */
public class Discipline {
    private int id;
    private String name;

    /**
     * Constructor for discipline with basic information
     *
     * @param id
     * @param name
     */
    public Discipline(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
