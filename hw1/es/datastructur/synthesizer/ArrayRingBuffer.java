package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    private class ArrayRingBufferIterator implements Iterator<T> {
        private int position;

        public ArrayRingBufferIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return fillCount < rb.length;
        }

        public T next() {
            return rb[first];
        }
    }
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        fillCount++;
        rb[last] = x;
        if (last == capacity() - 1) {
            last = 0;
        } else {
            last++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T oldest = rb[first];
        rb[first] = null;
        if (first == capacity() - 1) {
            first = 0;
        } else {
            first++;
        }
        fillCount--;
        return oldest;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public Iterator<T> iterator() {
        ArrayRingBuffer<T> ring = new ArrayRingBuffer(capacity());
        return ring.iterator();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }
}
