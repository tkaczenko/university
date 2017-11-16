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
 * Created by tkaczenko on 22.09.16.
 */
public class StudentsServiceWithoutParallel {
    private List<Student> students;

    public double getProportionSuccessfulToOrdinary() {
        long countOfExcellent = students.stream()
                .filter(isSuccessfulStudent(true))
                .count();

        return (double) countOfExcellent / (students.size() - countOfExcellent);
    }

    public double getProportionSuccessfulMenToWowen() {
        long countOfMen = countSuccessfulStudentByGender(Sex.MALE);
        long countOfWomen = countSuccessfulStudentByGender(Sex.FEMALE);

        return (double) countOfMen / countOfWomen;
    }

    public String getSuccessfulZodiac() {
        Map<Zodiac, Long> map = students.stream()
                .filter(isSuccessfulStudent(true))
                .collect(Collectors.groupingBy(Student::getZodiac, Collectors.counting()));

        return map.entrySet().stream()
                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .get().getKey().zodiac();
    }

    public Teacher getTeacherByDifficultSubject() {
        Map<Teacher, Long> teachers = students.stream()
                .flatMap(student -> student.getDisciplineCardMap().entrySet().stream())
                .filter(disciplineCardEntry -> disciplineCardEntry.getValue().getGrade().getValue() < 3)
                .collect(Collectors.groupingBy(entry -> entry.getValue().getTeacher(), Collectors.counting()));

        return teachers.entrySet().stream()
                .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
                .get().getKey();
    }

    private long countSuccessfulStudentByGender(Sex sex) {
        return students.stream()
                .filter(isSuccessfulStudent(true))
                .filter(student -> student.getGender() == sex)
                .count();
    }

    private Predicate<Student> isSuccessfulStudent(boolean p) {
        return student -> {
            Collection<Card> t = student.getDisciplineCardMap().values();
            return t.size() == t.stream()
                    .filter(Student.isExcellentGrade(p))
                    .count();
        };
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}

