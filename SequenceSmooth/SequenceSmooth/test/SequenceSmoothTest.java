import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Sample JUnit test fixture for SequenceSmooth.
 *
 * @author Nuo Xu
 *
 */
public final class SequenceSmoothTest {

    /**
     * Constructs and returns a sequence of the integers provided as arguments.
     *
     * @param args
     *            0 or more integer arguments
     * @return the sequence of the given arguments
     * @ensures createFromArgs= [the sequence of integers in args]
     */
    private Sequence<Integer> createFromArgs(Integer... args) {
        Sequence<Integer> s = new Sequence1L<Integer>();
        for (Integer x : args) {
            s.add(s.length(), x);
        }
        return s;
    }

//    /**
//     * Test smooth with s1 = <2, 4, 6>.
//     */
//    @Test
//    public void test1() {
//        /*
//         * Set up variables and call method under test
//         */
//        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
//        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
//        Sequence<Integer> seq2 = SequenceSmooth.smooth(seq1);
//        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5);
//        /*
//         * Assert that values of variables match expectations
//         */
//        assertEquals(expectedSeq1, seq1);
//        assertEquals(expectedSeq2, seq2);
//    }
//
//    /**
//     * Test smooth with s1 = <7>.
//     */
//    @Test
//    public void test2() {
//        /*
//         * Set up variables and call method under test
//         */
//        Sequence<Integer> seq1 = this.createFromArgs(7);
//        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
//        Sequence<Integer> seq2 = SequenceSmooth.smooth(seq1);
//        Sequence<Integer> expectedSeq2 = this.createFromArgs();
//        /*
//         * Assert that values of variables match expectations
//         */
//        assertEquals(expectedSeq1, seq1);
//        assertEquals(expectedSeq2, seq2);
//    }
//
//    /**
//     * Test smooth with s1 = <7, 9>.
//     */
//    @Test
//    public void test3() {
//        /*
//         * Set up variables and call method under test
//         */
//        Sequence<Integer> seq1 = this.createFromArgs(7, 9);
//        Sequence<Integer> expectedSeq1 = this.createFromArgs(7, 9);
//        Sequence<Integer> seq2 = SequenceSmooth.smooth(seq1);
//        Sequence<Integer> expectedSeq2 = this.createFromArgs(8);
//        /*
//         * Assert that values of variables match expectations
//         */
//        assertEquals(expectedSeq1, seq1);
//        assertEquals(expectedSeq2, seq2);
//    }
//
//    /**
//     * Test smooth with s1 = <1, 3 ,4, 3, 1>.
//     */
//    @Test
//    public void test4() {
//        /*
//         * Set up variables and call method under test
//         */
//        Sequence<Integer> seq1 = this.createFromArgs(1, 3, 4, 3, 1);
//        Sequence<Integer> expectedSeq1 = this.createFromArgs(1, 3, 4, 3, 1);
//        Sequence<Integer> seq2 = SequenceSmooth.smooth(seq1);
//        Sequence<Integer> expectedSeq2 = this.createFromArgs(2, 3, 3, 2);
//        /*
//         * Assert that values of variables match expectations
//         */
//        assertEquals(expectedSeq1, seq1);
//        assertEquals(expectedSeq2, seq2);
//    }
//
//    /**
//     * Test smooth with s1 = <-10, -20, -30> and s2 = <2, 4, 6>.
//     */
//    @Test
//    public void test5() {
//        /*
//         * Set up variables and call method under test
//         */
//        Sequence<Integer> seq1 = this.createFromArgs(-10, -20, -30);
//        Sequence<Integer> expectedSeq1 = this.createFromArgs(-10, -20, -30);
//        Sequence<Integer> seq2 = SequenceSmooth.smooth(seq1);
//        Sequence<Integer> expectedSeq2 = this.createFromArgs(-15, -25);
//        /*
//         * Assert that values of variables match expectations
//         */
//        assertEquals(expectedSeq1, seq1);
//        assertEquals(expectedSeq2, seq2);
//    }

    @Test
    public void test1() {
        int j = 5;
        int k = 8;
        int average1 = 6;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

    @Test
    public void test2() {
        int j = -5;
        int k = -8;
        int average1 = -6;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

    @Test
    public void test3() {
        int j = 11;
        int k = -4;
        int average1 = 3;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

    @Test
    public void test4() {
        int j = -3;
        int k = 2;
        int average1 = 0;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

    /**
    *
    */
    @Test
    public void test5() {
        int j = 3;
        int k = 5;
        int average1 = 4;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

    @Test
    public void test6() {
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE - 1;
        int average1 = Integer.MAX_VALUE - 1;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

    @Test
    public void test7() {
        int j = Integer.MIN_VALUE;
        int k = Integer.MIN_VALUE;
        int average1 = Integer.MIN_VALUE;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

    @Test
    public void test8() {
        int j = Integer.MIN_VALUE;
        int k = Integer.MIN_VALUE + 1;
        int average1 = Integer.MIN_VALUE + 1;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

    @Test
    public void test9() {
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE;
        int average1 = Integer.MAX_VALUE;
        int average2 = SequenceSmooth.average(j, k);
        assertEquals(average1, average2);

    }

}