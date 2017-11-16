import models.Student;
import models.Teacher;
import org.junit.Before;
import org.junit.Test;
import services.StudentsService;
import services.StudentsServiceWithoutParallel;

import java.util.List;
import java.util.Random;

/**
 * Created by tkaczenko on 22.09.16.
 */
public class SpeedTest {
    public static final int DEFAULT_SIZE = 1024;
    public static final int NAME_LENGTH = 10;

    private StudentsServiceWithoutParallel service;
    private StudentsService parallelService;
    private StudentGenerator generator;

    private List<Student> students;
    private Teacher teacher;

    @Before
    public void setUp() throws Exception {
        Random random = new Random();
        parallelService = new StudentsService();
        service = new StudentsServiceWithoutParallel();
        generator = new StudentGenerator();
        teacher = new Teacher(generator.generateRandomString(random, NAME_LENGTH),
                generator.generateRandomString(random, NAME_LENGTH), generator.generateRandomString(random, NAME_LENGTH));
    }

    @Test
    public void testSpeed() throws Exception {
        students = generator.generate(DEFAULT_SIZE, 11, teacher);

        int res = binarySearch(iteration(students), 0, DEFAULT_SIZE);
        System.out.println("Size of students' collection to be a good parallel = " + res);
    }

    private boolean iteration(List<Student> students) {
        long time = 0, timeForParallel = 0;
        long start;
        double res;

        service.setStudents(students);
        parallelService.setStudents(students);

        start = System.nanoTime();
        res = service.getProportionSuccessfulMenToWowen();
        time = System.nanoTime() - start;

        start = System.nanoTime();
        res = parallelService.getProportionSuccessfulMenToWowen();
        timeForParallel = System.nanoTime() - start;

        return timeForParallel > time;
    }

    private int binarySearch(boolean searchValue, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >>> 1;
        if (searchValue && right - left == 2) {
            return mid;
        } else if (searchValue) {
            return binarySearch(iteration(generator.generate(mid - 1, 11, teacher)), left, mid - 1);
        } else {
            return binarySearch(iteration(generator.generate(mid + 1, 11, teacher)), mid + 1, right);
        }
    }
}