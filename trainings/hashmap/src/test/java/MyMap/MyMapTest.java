package MyMap;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by andrei on 29.04.16.
 */
public class MyMapTest {
    private Map<String, String> instance;

    /**
     * Create a new MyMap for the all tests
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        instance = new MyMap<String, String>();
    }

    /**
     * Check size of an empty MyMap
     * @result Should be zero
     * @throws Exception
     */
    @Test
    public void newMapShouldHasZeroSize() throws Exception {
        assertEquals(instance.size(), 0);
    }

    /**
     * When add one element size should grows accordingly
     * @result Size equals one
     * @throws Exception
     */
    @Test
    public void whenAddOneElementSizeShouldGrowsAccordingly() throws Exception {
        instance.put("key", "value");
        assertEquals(instance.size(), 1);
    }

    /**
     * A new map should be empty
     * @result true
     * @throws Exception
     */
    @Test
    public void newMapShouldBeEmpty() throws Exception {
        assertTrue(instance.isEmpty());
    }

    /**
     * When add one element, map should be non empty
     * @result false
     * @throws Exception
     */
    @Test
    public void whenAddOneElementMapShouldBeNonEmpty() throws Exception {
        instance.put("key", "value");
        assertFalse(instance.isEmpty());
    }

    /**
     * Searching for the null key should be threw NullPointerException
     * @result NullPointerException
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void searchForNullKeyShouldBeThrewNullPointerException() throws Exception {
        instance.containsKey(null);
    }

    /**
     * Searching for the key when key is not in map should be not found key
     * @result false
     * @throws Exception
     */
    @Test
    public void searchForKeyWhenKeyIsNotInMapShouldBeNotFoundKey() throws Exception {
        instance.put("key", "value");
        assertFalse(instance.containsKey("0"));
    }

    /**
     * Searching for key when key is in map should be found key
     * @result true
     * @throws Exception
     */
    @Test
    public void searchForKeyWhenKeyIsInMapShouldBeFoundKey() throws Exception {
        instance.put("key", "value");
        assertTrue(instance.containsKey("key"));
    }

    /**
     * Searching for value when value is in map should be found value
     * @result true
     * @throws Exception
     */
    @Test
    public void searchForValueWhenValueIsInMapShouldBeFoundValue() throws Exception {
        instance.put("key", "value");
        assertTrue(instance.containsValue("value"));
    }

    /**
     * Searching for value when value is not in map should be not found value
     * @result false
     * @throws Exception
     */
    @Test
    public void searchForValueWhenValueIsNotInMapShouldBeNotFoundValue() throws Exception {
        instance.put("key", "value");
        assertFalse(instance.containsValue("0"));
    }

    /**
     * Searching for null value should be threw NullPointerException
     * @result NullPointerException
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void searchForNullValueShouldBeThrewNullPointerException() throws Exception {
        instance.containsValue(null);
    }

    /**
     * Getting value be the key when key is in map should return correct value
     * @result expResult
     * @throws Exception
     */
    @Test
    public void getValueByKeyWhenKeyIsInMapShouldReturnCorrectValue() throws Exception {
        instance.put("key", "value");
        String expResult = "value";
        String result = instance.get("key");
        assertEquals(expResult, result);
    }

    /**
     * Getting value by the key when key is not in map should return null value
     * @result null
     * @throws Exception
     */
    @Test
    public void getValueByKeyWhenKeyIsNotInMapShouldReturnNullValue() throws Exception {
        instance.put("key", "value");
        String expResult = null;
        String result = instance.get("yek");
        assertEquals(expResult, result);
    }

    /**
     * Getting value by the key when the key is null should be threw NullPointerException
     * @result NullPointerException
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void getValueByKeyWhenKeyIsNullShouldBeThrewNullPointerException() throws Exception {
        instance.get(null);
    }

    /**
     * Putting value into head of the list of map should return old value
     * @result expResult
     * @throws Exception
     */
    @Test
    public void putValueInToHeadOfListOfMapShouldReturnOldValue() throws Exception {
        instance.put("key1", "value1");
        String expResult = "value1";
        String result = instance.put("key1", "value2");
        assertEquals(expResult, result);
    }

    /**
     * Putting value into tail of the list of map should return null
     * @result null
     * @throws Exception
     */
    @Test
    public void putValueInToTailOfListOfMapShouldReturnNull() throws Exception {
        String expResult = null;
        String result = instance.put("key", "value");
        assertEquals(expResult, result);
    }

    /**
     * Putting null value should be threw null pointer exception
     * @result NullPointerException
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void putNullValueShouldBeThrewNullPointerException() throws Exception {
        instance.put(null, "value");
    }

    /**
     * Removing value when the values are in map should return old value
     * @result expResult
     * @throws Exception
     */
    @Test
    public void removeValueWhenValuesAreInMapShouldReturnOldValue() throws Exception {
        instance.put("key1", "value1");
        instance.put("key2", "value2");
        instance.put("key3", "value3");

        String expResult = "value2";
        String result = instance.remove("key2");
        assertEquals(expResult, result);
    }

    /**
     * Removing value when the values are not in map should return null
     * @result null
     * @throws Exception
     */
    @Test
    public void removeValueWhenValuesAreNotInMapShouldReturnNull() throws Exception {
        String expResult = null;
        String result = instance.remove("0");
        assertEquals(expResult, result);
    }

    /**
     * Removing null value should be threw NullPointerException
     * @result NullPointerException
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void removeNullValueShouldBeThrewNullPointerException() throws Exception {
        instance.remove(null);
    }

    /**
     * When add all elements size should be grows accordingly
     * @result expResult
     * @throws Exception
     */
    @Test
    public void whenAddAllElementsSizeShouldGrowsAccordingly() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        instance.putAll(map);
        assertEquals(3, instance.size());
    }

    /**
     * When clear the map, the map should has zero size
     * @result zero
     * @throws Exception
     */
    @Test
    public void whenClearMapMapShouldHasZeroSize() throws Exception {
        instance.put("key1", "value1");
        instance.put("key2", "value2");
        instance.clear();
        assertEquals(0, instance.size());
    }

    /**
     * When search all keys in empty map should return empty set of keys
     * @result expResult
     * @throws Exception
     */
    @Test
    public void whenSearchAllKeysInEmptyMapShouldReturnEmptySetOfKeys() throws Exception {
        Set<String> expResult = new HashSet<>();
        Set<String> result = instance.keySet();
        assertEquals(expResult, result);
    }

    /**
     * When search all keys in the map should be return the set of keys
     * @result expResult
     * @throws Exception
     */
    @Test
    public void whenSearchAllKeysInMapShouldReturnSetOfKeys() throws Exception {
        instance.put("key1", "value1");
        instance.put("key2", "value2");

        Set<String> expResult = new HashSet<>();
        expResult.add("key1");
        expResult.add("key2");

        assertEquals(expResult, instance.keySet());
    }

    /**
     * When search all values in empty map should return empty set of values
     * @result expResult
     * @throws Exception
     */
    @Test
    public void whenSearchAllValuesInEmptyMapShouldReturnEmptySetOfValues() throws Exception {
        Collection<String> expResult = new HashSet<>();
        Collection<String>  result = instance.keySet();
        assertEquals(expResult, result);
    }

    /**
     * When search all values in the map should return the set of values
     * @result expResult
     * @throws Exception
     */
    @Test
    public void whenSearchAllValuesInMapShouldReturnSetOfValues() throws Exception {
        instance.put("key1", "value1");
        instance.put("key2", "value2");

        Collection<String> expResult = new ArrayList<>();
        expResult.add("value1");
        expResult.add("value2");

        assertEquals(expResult, instance.values());
    }

    /**
     * When search all keys and values should return set of keys and values
     * @result expResult
     * @throws Exception
     */
    @Test
    public void whenSearchAllKeysAndValuesShouldRuturnSetOfKeysAndValues() throws Exception {
        String key1 = "key1";
        String key2 = "key2";
        String value1 = "value1";
        String value2 = "value2";
        instance.put(key1, value1);
        instance.put(key2, value2);

        Set<Map.Entry<String, String>> expResult = new LinkedHashSet<>();
        expResult.add(new AbstractMap.SimpleEntry<String, String>(key1, value1));
        expResult.add(new AbstractMap.SimpleEntry<String, String>(key2, value2));

        assertEquals(expResult, instance.entrySet());
    }

    /**
     * Printing map should return format string of keys and values from the map
     * @result expResult
     * @throws Exception
     */
    @Test
    public void printMapShouldReturnFormatStringOfKeysAndValuesOfMap() throws Exception {
        String key1 = "key1";
        String key2 = "key2";
        String value1 = "value1";
        String value2 = "value2";
        instance.put(key1, value1);
        instance.put(key2, value2);

        int index1 = key1.hashCode() % 100;
        int index2 = key2.hashCode() % 100;

        String expResult = "{";
        expResult += (index1 < index2) ? key1 + "=" + value1 + ", " : key2 + "=" + value2 + ", ";
        expResult += (index1 < index2) ? key2 + "=" + value2 : key1 + "=" + value1;
        expResult += "}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}