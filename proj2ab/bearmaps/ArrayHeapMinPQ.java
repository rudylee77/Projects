package bearmaps;

import java.util.ArrayList;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> heap;

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            return ((PriorityNode) o).getItem().equals(getItem());
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        heap.add(0, null);
    }

    @Override
    public void add(T item, double priority) {
        heap.add(new PriorityNode(item, priority));
        swimUp(heap.size() - 1);
    }

    private void swap(int index1, int index2) {
        PriorityNode replace = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, replace);
    }

    private void swimUp(int index) {
        if (index > 1 && heap.get(parent(index)).compareTo(heap.get(index)) > 0) {
            swap(parent(index), index);
            swimUp(parent(index));
        }
    }

    private int parent(int index) {
        return index / 2;
    }

    private int leftChild(int index) {
        return index * 2;
    }

    private int rightChild(int index) {
        return index * 2 + 1;
    }

    private void swimDown(int index) {
        if (heap.size() > leftChild(index) && heap.size() > rightChild(index)) {
            if (heap.get(rightChild(index)).compareTo(heap.get(leftChild(index))) >= 0) {
                if (heap.get(index).compareTo(heap.get(leftChild(index))) > 0) {
                    swap(index, leftChild(index));
                    swimDown(leftChild(index));
                }
            } else {
                if (heap.get(index).compareTo(heap.get(rightChild(index))) > 0) {
                    swap(index, rightChild(index));
                    swimDown(rightChild(index));
                }
            }
        } else if (heap.size() > leftChild(index)) {
            if (heap.get(index).compareTo(heap.get(leftChild(index))) > 0) {
                swap(index, leftChild(index));
                swimDown(leftChild(index));
            }
        }
    }

    @Override
    public boolean contains(T item) {
        return heap.contains(new PriorityNode(item, 0));
    }

    @Override
    public T getSmallest() {
        return heap.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        T smallest = getSmallest();
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        swimDown(1);
        return smallest;
    }

    @Override
    public int size() {
        return heap.size() - 1;
    }

    @Override
    public void changePriority(T item, double priority) {
        int index = indOf(item);
        heap.get(index).setPriority(priority);
        swimUp(index);
    }

    private int indOf(T item) {
        return heap.indexOf(new PriorityNode(item, 0));
    }
}
