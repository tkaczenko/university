package services;

import enumerations.Sex;
import enumerations.Zodiac;
import models.Card;
import models.Student;
import models.Teacher;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service of students that do several features using Stream API
 *
 * @author Andrii Tkachenko
 */
public class StudentsService {
    /**
     * List of all students
     *
     * @see Student
     */
    private List<Student> students;

    /**
     * Calculate proportion successful to ordinary students
     *
     * @return {@code double} proportion of students
     */
    public double getProportionSuccessfulToOrdinary() {
        long countOfExcellent = students.parallelStream()
                .filter(isSuccessfulStudent(true))
                .count();

        return (double) countOfExcellent / (students.size() - countOfExcellent);
    }

    /**
     * Calculate proportion successful men to women
     *
     * @return {@code double} proportion of students
     */
    public double getProportionSuccessfulMenToWowen() {
        long countOfMen = countSuccessfulStudentByGender(Sex.MALE);
        long countOfWomen = countSuccessfulStudentByGender(Sex.FEMALE);

        return (double) countOfMen / countOfWomen;
    }

    /**
     * Calculate successful zodiac
     *
     * @return {@code String} of successful zodiac
     * @see Zodiac
     */
    public String getSuccessfulZodiac() {
        Map<Zodiac, Long> map = students.parallelStream()
                .filter(isSuccessfulStudent(true))
                .collect(Collectors.groupingBy(Student::getZodiac, Collectors.counting()));

        return map.entrySet().parallelStream()
                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .get().getKey().zodiac();
    }

    /**
     * Calculate the teacher who has got difficult subjects
     *
     * @return {@code Teacher} who has got difficult subjects
     * @see Teacher
     */
    public Teacher getTeacherByDifficultSubject() {
        Map<Teacher, Long> teachers = students.parallelStream()
                .flatMap(student -> student.getDisciplineCardMap().entrySet().stream())
                .filter(disciplineCardEntry -> disciplineCardEntry.getValue().getGrade().getValue() < 3)
                .collect(Collectors.groupingBy(entry -> entry.getValue().getTeacher(), Collectors.counting()));

        return teachers.entrySet().parallelStream()
                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .get().getKey();
    }

    /**
     * Calculate successful students by gender
     *
     * @param sex If it's {@code true} then calculate successful men else
     *            successful women
     * @return Number of all successful students
     * @see Sex
     */
    private long countSuccessfulStudentByGender(Sex sex) {
        return students.parallelStream()
                .filter(isSuccessfulStudent(true))
                .filter(student -> student.getGender() == sex)
                .count();
    }

    /**
     * Check successful student
     *
     * @param p true or false for success
     * @return Predicate
     * @see Card
     */
    private Predicate<Student> isSuccessfulStudent(boolean p) {
        return student -> {
            Collection<Card> t = student.getDisciplineCardMap().values();
            return t.size() == t.parallelStream()
                    .filter(Student.isExcellentGrade(p))
                    .count();
        };
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
