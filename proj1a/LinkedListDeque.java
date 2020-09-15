public class LinkedListDeque<T> {
    private TList sentinel;
    private int size = 0;

    public class TList {
        public TList prev;
        public T item;
        public TList next;

        public TList(TList p, T i, TList n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new TList(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        sentinel.prev = new TList(sentinel, item, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.next.prev = new TList(sentinel.next, item, sentinel);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel.prev.item != null) {
            return false;
        }
        return true;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TList print = sentinel.prev;
        while (print.prev != sentinel.prev) {
            System.out.print(print.item + "");
            print = print.prev;
        }
        System.out.println();
    }

    public T removeFirst() {
        T first = sentinel.prev.item;
        if (first != null) {
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return first;
        }
        return null;
    }

    public T removeLast() {
        T last = sentinel.next.item;
        if (last != null) {
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return last;
        }
        return null;
    }

    private T helper(int index, TList node) {
        if (node.prev.item == null) {
            return null;
        } else if (index == 0) {
            return node.prev.item;
        }
        return helper(index - 1, node.prev);
    }

    public T get(int index) {
        int count = 0;
        TList node = sentinel;
        while (count != index) {
            if (node.prev.item == null) {
                return null;
            }
            count += 1;
            node = node.prev;
        }
        return node.prev.item;
    }

    public T getRecursive(int index) {
        return helper(index, sentinel);
    }
}