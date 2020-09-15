public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int prev;
    private int next;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        prev = 4;
        next = 5;
    }

    private void resize(int i) {
        int index = (prev + 1) % array.length;
        T[] newArray = (T[]) new Object[i];
        for (int j = 0; j < size; j++) {
            newArray[j] = array[index];
            index = (index + 1) % array.length;
        }
        prev = i - 1;
        next = size;
        array = newArray;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[prev] = item;
        prev = (prev - 1 + array.length) % array.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[next] = item;
        next = (next + 1) % array.length;
        size -= 1;
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
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        prev = (prev + 1) % array.length;
        T element = array[prev];
        array[prev] = null;
        size -= 1;
        if (array.length >= 16 && size < (array.length / 4)) {
            resize(array.length/2);
        }
        return element;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        next = (next - 1 + array.length) % array.length;
        T element = array[next];
        array[next] = null;
        size -= 1;
        if (array.length >= 16 && size < (array.length / 4)) {
            resize(array.length / 2);
        }
        return element;
    }

    public T get(int index) {
        if (size < index) {
            return null;
        }
        int count = (prev + 1) % array.length;
        return array[(count + index) % array.length];
    }
}