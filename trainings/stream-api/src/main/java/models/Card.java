package models;

import enumerations.Grade;

/**
 * Model of information about the discipline
 */
public class Card {
    /**
     * Teacher who teach the discipline
     *
     * @see Teacher
     */
    private Teacher teacher;
    /**
     * Grade of the discipline
     *
     * @see Grade
     */
    private Grade grade;
    private int term;
    private int year;

    /**
     * Constructor for Card with basic information
     *
     * @param teacher
     * @param grade
     * @param term
     * @param year
     */
    public Card(Teacher teacher, Grade grade, int term, int year) {
        this.teacher = teacher;
        this.grade = grade;
        this.term = term;
        this.year = year;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
