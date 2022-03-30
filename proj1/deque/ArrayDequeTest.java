package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        ArrayDeque<Integer> alist = new ArrayDeque();
        AListNoResizing<Integer> blist = new AListNoResizing();

        alist.addLast(4);
        blist.addLast(4);
        alist.addLast(5);
        blist.addLast(5);
        alist.addLast(6);
        blist.addLast(6);


        assertEquals(alist.removeLast(), blist.removeLast());
        assertEquals(alist.removeLast(), blist.removeLast());
        assertEquals(alist.removeLast(), blist.removeLast());
    }


    @Test
    public void addFirstLast() {
        ArrayDeque<Integer> deque = new ArrayDeque();
        for (int i = 0; i < 15; i++) {
            deque.addFirst(i);
        }

        assertEquals(Integer.valueOf(0), deque.get(14));
        assertEquals(Integer.valueOf(14), deque.get(0));

        for (int i= 15; i < 30; i++) {
            deque.addLast(i);
        }

        assertEquals(Integer.valueOf(15), deque.get(15));
        assertEquals(Integer.valueOf(29), deque.get(29));

    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        AListNoResizing<Integer> B = new AListNoResizing<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(size, B.size());
            } else if (operationNumber == 2) {
                //getLast

                if (L.size() > 0 ) {
                    int last = L.removeLast();
                    int bLast = B.removeLast();
                    assertEquals(last, bLast);
                }
            }
        }
    }

    @Test
    /* test get*/
    public void get() {
        ArrayDeque<Integer> L = new ArrayDeque<>();

        L.addLast(0);
        L.addLast(1);
        L.addFirst(3);
        L.addLast(4);
        int zero = L.get(0);
        assertEquals(3, zero);


        L.removeFirst();
        L.addLast(7);
        L.removeFirst();
        L.addLast(9);
        int three = L.get(3);
        assertEquals(9, three);




    }


}