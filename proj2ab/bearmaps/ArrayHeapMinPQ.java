package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> heap;
    private HashMap<T, Integer> map = new HashMap<>();

    /*@Source NaiveMinPQ.java class*/
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
        swimUp(size());
        map.put(item, size());
    }

    private void swap(int index1, int index2) {
        PriorityNode replace1 = heap.get(index1);
        PriorityNode replace2 = heap.get(index2);
        map.put(replace1.getItem(), index2);
        map.put(replace2.getItem(), index1);
        heap.set(index1, replace2);
        heap.set(index2, replace1);
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
        return map.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return heap.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        T smallest = getSmallest();
        heap.set(1, heap.get(size()));
        heap.remove(size());
        map.remove(smallest);
        swimDown(1);
        return smallest;
    }

    @Override
    public int size() {
        return heap.size() - 1;
    }

    @Override
    public void changePriority(T item, double priority) {
        int index = map.get(item);
        PriorityNode replace = heap.get(index);
        double oldPriority = replace.getPriority();
        replace.setPriority(priority);
        if (oldPriority > priority) {
            swimUp(index);
        } else {
            swimDown(index);
        }
    }
}
