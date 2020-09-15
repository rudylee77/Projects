public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int prev;
    private int next;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        prev = 0;
        next = 8;
    }

    public void addFirst(T item) {
        prev = (prev - 1 + array.length) % array.length;
        array[prev] = item;
        size += 1;
    }

    public void addLast(T item) {
        next = next % array.length;
        array[next] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T element = array[prev];
        prev = (prev + 1) % array.length;
        size -= 1;
        return element;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T element = array[next];
        next = (next - 1 + array.length) % array.length;
        size -= 1;
        return element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int count = prev + 1;
        while (index > 0) {
            count += 1;
            index -= 1;
        }
        return array[count % array.length];
    }
}