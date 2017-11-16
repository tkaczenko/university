package MyMap;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by andrei on 31.05.16.
 */
public class MyMapSeveralTests {
    public static final int SIZE = 1000 * 100;
    public static final double EPS = 1e-3;
    public final int[] INTS = new int[SIZE];
    private MyMap<Integer, Integer> instance;
    private HashMap<Integer, Integer> map;

    @Before
    public void setUp() throws Exception {
        instance = new MyMap<Integer, Integer>();
        map = new HashMap<>();

        for (int i = 0; i < SIZE; i++) {
            INTS[i] = i;
        }
    }

    @Test
    public void testPutting() throws Exception {
        test();
    }

    private void test() {
        long puttingToMyMap = 0;
        long puttingToMap = 0;
        int size = SIZE;

        double res = 0;
        do {
            long start = System.nanoTime() / 1000;
            testPut(instance, size);
            puttingToMyMap = System.nanoTime() / 1000 - start;

            print("MyMap", size, puttingToMyMap);

            start = System.nanoTime() / 1000;
            testPut(map, size);
            puttingToMap = System.nanoTime() / 1000 - start;

            print("Map", size, puttingToMap);

            res = Math.abs(puttingToMap - puttingToMyMap) / size;
            System.out.println(res);

            size /= 10;
        } while (res <= EPS || size >= 10);
    }

    private void testPut(Map<Integer, Integer> map, int size) {
        for (int i = 0; i < size; i++) {
            map.put(INTS[i], INTS[i]);
        }
    }

    private void print(String name, int size, long time) {
        System.out.println(name
                + "\t\t" + Integer.toString(size)
                + "\t\t" + Long.toString(time)
        );
    }
}