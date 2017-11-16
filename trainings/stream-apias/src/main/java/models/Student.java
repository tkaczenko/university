package models;

import enumerations.Sex;
import enumerations.Zodiac;

import java.util.Map;
import java.util.function.Predicate;

/**
 * Model of student's data
 *
 * @author Andrii Tkachenko
 */
public class Student {
    private String firstName;
    private String middleName;
    private String lastName;

    private short age;

    /**
     * Zodiac of the student
     *
     * @see Zodiac
     */
    private Zodiac zodiac;

    /**
     * Gender of the student
     *
     * @see Sex
     */
    private Sex gender;

    /**
     * Map of discipline and information about this discipline
     *
     * @see Discipline
     * @see Card
     */
    private Map<Discipline, Card> disciplineCardMap;

    /**
     * Check successful student
     *
     * @param p {@code true} or {@code false} for success
     * @return Predicate
     * @see Card
     */
    public static Predicate<Card> isExcellentGrade(boolean p) {
        return p ? card -> card.getGrade().getValue() > 3
                : card -> card.getGrade().getValue() < 3;
    }

    /**
     * Constructor for student with basic information
     *
     * @param firstName
     * @param middleName
     * @param lastName
     * @param age
     * @param zodiac
     * @param gender
     */
    public Student(String firstName, String middleName, String lastName, short age, Zodiac zodiac, Sex gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.zodiac = zodiac;
        this.gender = gender;
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

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public Zodiac getZodiac() {
        return zodiac;
    }

    public void setZodiac(Zodiac zodiac) {
        this.zodiac = zodiac;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public Map<Discipline, Card> getDisciplineCardMap() {
        return disciplineCardMap;
    }

    public void setDisciplineCardMap(Map<Discipline, Card> disciplineCardMap) {
        this.disciplineCardMap = disciplineCardMap;
    }


}
