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
        int count = (prev + 1) % array.length;;
        T[] newArray = (T[]) new Object[i];
        for (int j = 0; j < size; j++) {
            newArray[j] = array[count];
            count = (count + 1) % array.length;;
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
        size += 1;
        prev = (prev - 1 + array.length) % array.length;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[next] = item;
        size += 1;
        next = (next + 1) % array.length;;
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
        if (isEmpty()) {
            return null;
        }
        prev = (prev + 1) % array.length;;
        T element = array[prev];
        array[prev] = null;
        size -= 1;
        if (array.length >= 16 && size < (array.length / 4)) {
            resize(array.length / 2);
        }
        return element;
    }

    public T removeLast() {
        if (isEmpty()) {
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
        if (index > size) {
            return null;
        } else {
            return array[(((prev + 1) % array.length) + index) % array.length];
        }
    }
}