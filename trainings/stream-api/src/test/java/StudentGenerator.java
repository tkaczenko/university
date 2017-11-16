import enumerations.Grade;
import enumerations.Sex;
import enumerations.Zodiac;
import models.Card;
import models.Discipline;
import models.Student;
import models.Teacher;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Random generator of students
 *
 * @author Andrii Tkachenko
 * @see Student
 */
public class StudentGenerator {
    private long countOfSuccessfulStudent = 0;
    private long countOfSuccessfulMen = 0;

    /**
     * Generate list of students.
     * If student is a man then his zodiac will be {@code Zodiac.LEO} else
     * his zodiac will be {@code Zodiac.VIRGO}
     * If student is unsuccessful then his teacher will be {@code teacherWithDifficultSubjects} else
     * he will be randomized
     *
     * @param sizeOfList
     * @param sizeOfMap
     * @param teacherWithDifficultSubjects
     * @return New {@code List<Student>} of the generated students
     */
    public List<Student> generate(long sizeOfList, long sizeOfMap, Teacher teacherWithDifficultSubjects) {
        List<Student> students = new ArrayList<>();
        Random random = new Random();

        boolean flagOfSuccess, flagOfMan;
        for (long i = 0; i < sizeOfList; i++) {
            Student student;
            Map<Discipline, Card> map = new HashMap<>();

            flagOfSuccess = random.nextBoolean();
            if (flagOfSuccess) {
                flagOfMan = random.nextBoolean();
                if (flagOfMan) {
                    student = generateStudent(random, true, true);
                    countOfSuccessfulMen++;
                } else {
                    student = generateStudent(random, true, false);
                }
                for (long j = 0; j < sizeOfMap; j++) {
                    map.put(generateDiscipline(random),
                            generateCard(random, true, null));
                }
                countOfSuccessfulStudent++;
            } else {
                student = generateStudent(random, false, random.nextBoolean());
                for (long j = 0; j < sizeOfMap; j++) {
                    map.put(generateDiscipline(random),
                            generateCard(random, false, teacherWithDifficultSubjects));
                }
            }
            student.setDisciplineCardMap(map);

            students.add(student);
        }

        return students;
    }

    /**
     * Generate random String in Java using Stream API
     *
     * @param random {@code java.util.Random}
     * @param length Size of the string
     * @return Generated new {@code String}
     */
    public String generateRandomString(Random random, int length) {
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * Generate new {@code Student}
     *
     * @param random  {@code java.util.Random}
     * @param success If it's {@code true} then the student will be successful else
     *                he will be randomized
     * @param man     If it's {@code true} then the student will be a man else
     *                he will be a woman
     * @return Generated new {@code Student}
     * @see Student
     */
    private Student generateStudent(Random random, boolean success, boolean man) {
        if (success) {
            return new Student(generateRandomString(random, 5), generateRandomString(random, 5),
                    generateRandomString(random, 10), (short) random.nextInt(30), man ? Zodiac.get(5) : Zodiac.get(6),
                    man ? Sex.get(0) : Sex.get(1));
        } else {
            return new Student(generateRandomString(random, 5), generateRandomString(random, 5),
                    generateRandomString(random, 10), (short) random.nextInt(30), Zodiac.get(random.nextInt(12)),
                    Sex.get(random.nextInt(1)));
        }
    }

    /**
     * Generate new {@code Student}
     *
     * @param random  {@code java.util.Random}
     * @param success If it's {@code true} then the student will be successful else
     *                he will be unsuccessful
     * @param teacher If {@code success} is {@code false} then the student's teacher will be {@code teacher} else
     *                his teacher will be randomized
     * @return Generated new {@code Student}
     * @see Card
     */
    private Card generateCard(Random random, boolean success, Teacher teacher) {
        return new Card(success ? generateTeacher(random) : teacher,
                success ? Grade.get(ThreadLocalRandom.current().nextInt(4, 6)) :
                        Grade.get(ThreadLocalRandom.current().nextInt(1, 4)), random.nextInt(2), random.nextInt(2016));
    }

    /**
     * Generate randomized teacher
     *
     * @param random {@code java.util.Random}
     * @return Generated new {@code Teacher}
     * @see Teacher
     */
    private Teacher generateTeacher(Random random) {
        return new Teacher(generateRandomString(random, 5), generateRandomString(random, 5),
                generateRandomString(random, 10));
    }

    /**
     * Generate randomized discipline
     *
     * @param random {@code java.util.Random}
     * @return Generated new {@code Discipline}
     * @see Discipline
     */
    private Discipline generateDiscipline(Random random) {
        return new Discipline(random.nextInt(), generateRandomString(random, 12));
    }

    public long getCountOfSuccessfulStudent() {
        return countOfSuccessfulStudent;
    }

    public long getCountOfSuccessfulMen() {
        return countOfSuccessfulMen;
    }
}