package models;

import java.util.List;

/**
 * Model of teacher's data
 */
public class Teacher {
    private String firstName;
    private String middleName;
    private String lastName;

    /**
     * List of the teacher's disciplines
     *
     * @see Discipline
     */
    private List<Discipline> disciplines;

    /**
     * Constructor for Teacher with basic information
     *
     * @param firstName
     * @param middleName
     * @param lastName
     */
    public Teacher(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }
}
