import enumerations.Zodiac;
import models.Student;
import models.Teacher;
import org.junit.Before;
import org.junit.Test;
import services.StudentsService;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by tkacz- on 14.07.16.
 *
 * @author Andrii Tkachenko
 * @see StudentsService
 */
public class StudentsServiceTest {
    public static final int NAME_LENGTH = 10;
    private final double DELTA = 1E-3;

    /**
     * Mock object for StudentsService
     *
     * @see StudentsService
     */
    private StudentsService service;

    /**
     * Random generator of students, teachers,
     * card and etc.
     *
     * @see StudentGenerator
     */
    private StudentGenerator generator;

    private List<Student> students;
    private Teacher teacher;

    /**
     * Set up the all objects for checking StudentsService
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Random random = new Random();
        service = new StudentsService();
        generator = new StudentGenerator();
        teacher = new Teacher(generator.generateRandomString(random, NAME_LENGTH),
                generator.generateRandomString(random, NAME_LENGTH), generator.generateRandomString(random, NAME_LENGTH));
        students = generator.generate(100, 11, teacher);
        service.setStudents(students);
    }

    /**
     * When list exists, propotion successful to ordinary students
     * should be equal expected result
     *
     * @throws Exception
     * @result The proportion equals expected result
     * @see StudentsService#getProportionSuccessfulToOrdinary()
     */
    @Test
    public void whenListExistsProportionSuccessfulToOrdinaryShouldBeEqualToExpectedResult() throws Exception {
        long countOfSuccessfulStudent = generator.getCountOfSuccessfulStudent();
        double expected = (double) countOfSuccessfulStudent / (students.size() - countOfSuccessfulStudent);
        double actual = service.getProportionSuccessfulToOrdinary();
        assertEquals(expected, actual, DELTA);
    }

    /**
     * When list exists, proportion successful mans to successful women
     * should be equal expected result
     *
     * @throws Exception
     * @result The proportion equals expected result
     * @see StudentsService#getProportionSuccessfulMenToWowen()
     */
    @Test
    public void whenListExistsProportionSuccessfulMenToWowenShouldBeEqualExpectedResult() throws Exception {
        long countOfSuccessfulStudent = generator.getCountOfSuccessfulStudent();
        long countOfSuccessfulMen = generator.getCountOfSuccessfulMen();
        double expected = (double) countOfSuccessfulMen / (countOfSuccessfulStudent - countOfSuccessfulMen);
        double actual = service.getProportionSuccessfulMenToWowen();
        assertEquals(expected, actual, DELTA);
    }

    /**
     * When list exists successful zodiac
     * should be equal expected result
     *
     * @throws Exception
     * @result Successful zodiac equals expected result
     * @see StudentsService#getSuccessfulZodiac()
     */
    @Test
    public void whenListExistsSuccessfulZodiacShouldBeEqualExpectedResult() throws Exception {
        long countOfSuccessfulStudent = generator.getCountOfSuccessfulStudent();
        long countOfSuccessfulMen = generator.getCountOfSuccessfulMen();
        String expected = (countOfSuccessfulMen >
                (countOfSuccessfulStudent - countOfSuccessfulMen)) ? Zodiac.LEO.zodiac() : Zodiac.VIRGO.zodiac();
        String actual = service.getSuccessfulZodiac();
        assertEquals(expected, actual);
    }

    /**
     * When list exists teacher who has got difficult subjects
     * should be equal expected result
     *
     * @throws Exception
     * @result Teacher's full name equals expected result
     * @see StudentsService#getTeacherByDifficultSubject()
     */
    @Test
    public void whenListExistsTeacherWithDifficultSubjectShouldBeEqualExpectedResult() throws Exception {
        Teacher actual = service.getTeacherByDifficultSubject();
        assertEquals(teacher.getFirstName(), actual.getFirstName());
        assertEquals(teacher.getMiddleName(), actual.getMiddleName());
        assertEquals(teacher.getLastName(), actual.getLastName());
    }

}