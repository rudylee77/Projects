import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        clear();
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        }
        return p.value;
    }

    public V get(K key) {
       return getHelper(key, root);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        size++;
        return p;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(Node p) {
        if (p == null) {
            return;
        }
        printInOrder(p.left);
        System.out.print("(" + p.key.toString() + ", " + p.value.toString() + ")");
        printInOrder(p.right);
    }
}
