import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private Node<K, V>[] map;
    private int size;

    private class Node<K, V> {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    public MyHashMap() {
        clear();
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        clear();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        clear();
    }

    @Override
    public void clear() {
        map = new Node[initialSize];
        for (int i = 0; i < initialSize; i++) {
            map[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return (get(key) != null);
    }

    @Override
    public V get(K key) {
        return getHelper(key, map[hash(key)]);
    }

    private V getHelper(K key, Node n) {
        if (n == null) {
            return null;
        } else if (n.key.equals(key)) {
            return (V) n.value;
        }
        return (V) getHelper(key, n.next);
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.floorMod(key.hashCode(), map.length);
    }

    @Override
    public int size(){
        return size;
    }

    private void putHelper(K key, V value, Node n) {
        if (n.key == key) {
            n.value = value;
        } else if (n.next == null) {
            n.next = new Node<>(key, value);
            size++;
        } else {
            putHelper(key, value, n.next);
        }
    }

    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        if (map[hash] == null) {
            map[hash] = new Node<>(key, value);
            size++;
        } else {
            putHelper(key, value, map[hash]);
        }

        if (((double) size / initialSize) > loadFactor) {
            resize(initialSize * 2);
        }
    }

    private void resize(int capacity) {
        MyHashMap<K, V> replace = new MyHashMap<>(capacity);
        Set<K> keys = keySet();
        for (K key: keys) {
            replace.put(key, get(key));
        }
        initialSize = replace.initialSize;
        map = replace.map;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < initialSize; i += 1) {
            Node n = map[i];
            while (n != null) {
                keys.add((K) n.key);
                n = n.next;
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> set = keySet();
        return keySet().iterator();
    }
}
