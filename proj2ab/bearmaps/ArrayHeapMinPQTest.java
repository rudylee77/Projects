package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    @Test
    public void sizeTest() {
        ArrayHeapMinPQ<String> stringHeap = new ArrayHeapMinPQ<>();
        stringHeap.add("a", 0.0);
        stringHeap.add("b", 0.1);
        stringHeap.add("c", 0.2);
        assertEquals(3, stringHeap.size());
    }

    @Test
    public void addAndRemoveTest() {
        ArrayHeapMinPQ<String> stringHeap = new ArrayHeapMinPQ<>();
        stringHeap.add("b", 0.1);
        stringHeap.add("a", 0.0);
        stringHeap.add("d", 0.3);
        stringHeap.add("f", 0.5);
        stringHeap.add("c", 0.2);
        stringHeap.add("e", 0.4);
        assertEquals("a", stringHeap.getSmallest());
        String smallest = stringHeap.removeSmallest();
        assertEquals("a", smallest);
        assertEquals(5, stringHeap.size());
        assertEquals("b", stringHeap.getSmallest());
    }

    @Test
    public void containTest() {
        ArrayHeapMinPQ<String> stringHeap = new ArrayHeapMinPQ<>();
        stringHeap.add("b", 0.1);
        stringHeap.add("a", 0.0);
        stringHeap.add("d", 0.3);
        stringHeap.add("f", 0.5);
        stringHeap.add("c", 0.2);
        stringHeap.add("e", 0.4);
        assertTrue(stringHeap.contains("a"));
        stringHeap.removeSmallest();
        assertFalse(stringHeap.contains("a"));
    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> stringHeap = new ArrayHeapMinPQ<>();
        stringHeap.add("b", 0.2);
        stringHeap.add("a", 0.1);
        stringHeap.add("d", 0.4);
        stringHeap.add("f", 0.6);
        stringHeap.add("c", 0.3);
        stringHeap.add("e", 0.5);
        stringHeap.changePriority("d", 0.0);
        assertEquals("d", stringHeap.getSmallest());
        stringHeap.removeSmallest();
        assertEquals("a", stringHeap.getSmallest());
    }

    @Test
    public void timeTest() {
        ArrayHeapMinPQ<Integer> intHeap = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();


        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            intHeap.add(i,10000-i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("ArrayHeap add time:" + (end1 - start1) + "ms.");

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            naiveMinPQ.add(i,10000-i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("NaiveMinPQ add time:" + (end2 - start2) + "ms.");


        long start3 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            intHeap.contains(i);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("ArrayHeap contain time:" + (end3 - start3) + "ms.");

        long start4 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            naiveMinPQ.contains(i);
        }
        long end4 = System.currentTimeMillis();
        System.out.println("NaiveMinPQ contain time:" + (end4 - start4) + "ms.");


        long start5 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            intHeap.changePriority(i, i+10);;
        }
        long end5 = System.currentTimeMillis();
        System.out.println("ArrayHeap changePriority time:" + (end5 - start5) + "ms.");

        long start6 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            naiveMinPQ.changePriority(i, i+10);;
        }
        long end6 = System.currentTimeMillis();
        System.out.println("NaiveMinPQ changePriority time:" + (end6 - start6) + "ms.");


        long start7 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            intHeap.getSmallest();;
        }
        long end7 = System.currentTimeMillis();
        System.out.println("ArrayHeap get time:" + (end7 - start7) + "ms.");

        long start8 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            naiveMinPQ.getSmallest();;
        }
        long end8 = System.currentTimeMillis();
        System.out.println("NaiveMinPQ get time:" + (end8 - start8) + "ms.");


        long start9 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            intHeap.removeSmallest();
        }
        long end9 = System.currentTimeMillis();
        System.out.println("ArrayHeap remove time:" + (end9 - start9) + "ms.");

        long start10 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            naiveMinPQ.removeSmallest();;
        }
        long end10 = System.currentTimeMillis();
        System.out.println("NaiveMinPQ remove time:" + (end10 - start10) + "ms.");
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }
}
