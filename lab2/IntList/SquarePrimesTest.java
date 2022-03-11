package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquirePrimesComplex() {
        IntList lst = IntList.of(2, 9, 11, 7, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 121 -> 49 -> 20", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquirePrimesNoChange() {
        IntList lst = IntList.of(4, 9, 10, 21, 121);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 10 -> 21 -> 121", lst.toString());
        assertTrue(!changed);
    }
}
