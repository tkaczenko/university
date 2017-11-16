package MyMap;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Custom implementation of HashMap with Stream API
 * @author tkacz-
 * @version 1.0
 * @see java.util.HashMap
 */
public class MyMap<K, V> implements Map<K, V> {

    /**
     * Hash table for lists of map elements
     */
    private ArrayList<MyEntry<K, V>>[] table;

    /**
     * Default capacity of the hash table
     */
    private final int defaultSize = 10;
    private int size = 0;

    private static class MyEntry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }
    }

    /**
     * Returns index of bucket in hash table
     * @param   key key of the value
     * @return      index of buckt in hash table
     */
    private int indexFor(Object key) {
        return key.hashCode() % table.length;
    }

    public MyMap() {
        table = new ArrayList[defaultSize];
        for (int i = 0; i < defaultSize; i++) {
            table[i] = new ArrayList<>();
        }
    }

    /**
     * Returns size of collection
     * @return  size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Check if the map is empty
     * @return  true or false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if the key is in map
     * @param key   key of value
     * @return  true or false
     * @throws NullPointerException If the key is null
     */
    @Override
    public boolean containsKey(Object key) {
        requireNonNull(key);

        int index = indexFor(key);

        return table[index].stream()
                .anyMatch((e) -> e.getKey().equals(key));
    }

    /**
     * Check if the value is in map
     * @param value value which must be in map
     * @return  true or false
     * @throws NullPointerException If the value is null
     */
    @Override
    public boolean containsValue(Object value) {
        requireNonNull(value);

        return Arrays.stream(table)
                .anyMatch((l) -> l.stream()
                        .anyMatch((e) -> e.getValue().equals(value)));
    }

    /**
     * Ruturns value by the key
     * @param key   the key of value
     * @return  value by the key or null
     * @throws NullPointerException If the key is null
     */
    @Override
    public V get(Object key) {
        requireNonNull(key);

        int index = indexFor(key);

        for (MyEntry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Put the key and the value in map.
     * @param key   key of the value
     * @param value value of the map element
     * @return  old value by the key or null
     * @throws  NullPointerException If a key or value is null
     */
    @Override
    public V put(K key, V value) {
        requireNonNull(key);
        requireNonNull(value);

        int index = indexFor(key);

        for (MyEntry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        table[index].add(new MyEntry<K, V>(key, value));
        size++;
        return null;
    }

    /**
     * Remove value from map by the key
     * @param key   key of the value which will be removed
     * @return  old value be the key or null
     */
    @Override
    public V remove(Object key) {
        requireNonNull(key);

        int index = indexFor(key);

        for (MyEntry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                table[index].remove(entry);
                return oldValue;
            }
        }
        return null;
    }

    /**
     * Put the map into the MyMap
     * @param m the map which will be put
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        requireNonNull(m);

        if (!m.isEmpty()) {
            m.keySet().stream()
                    .forEach((k) -> put(k, m.get(k)));
        }
    }

    /**
     * Clear MyMap
     */
    @Override
    public void clear() {
        Arrays.stream(table)
                .forEach((l) -> l.clear());
        size = 0;
    }

    /**
     * Returns the set of keys from the map
     * @return  the set of keys
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = Arrays.stream(table)
                .flatMap((l) -> l.stream()
                        .map((e) -> e.getKey()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return set;
    }

    /**
     * Returns collection of values from the map
     * @return collection of values
     */
    @Override
    public Collection<V> values() {
        Collection<V> collection = Arrays.stream(table)
                .flatMap((l) -> l.stream()
                        .map((e) -> e.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        return collection;
    }

    /**
     * Returns set of keys and values from the map
     * @return  set of keys and values from the map
     */
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = Arrays.stream(table)
                .flatMap((l) -> l.stream()
                        .map((e) -> new AbstractMap.SimpleEntry<K, V>(e.getKey(), e.getValue())))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return set;
    }

    /**
     * Put the map into String
     * @return  string of keys and values from the map
     */
    @Override
    public String toString() {
        Set<Map.Entry<K, V>> set = entrySet();

        String result = "{";
        result += set.stream()
                .map((e) -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining(", "));
        result += "}";

        return result;
    }
}