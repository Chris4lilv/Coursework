import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Nuo Xu
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @return s2 the sequence of smoothed value
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static Sequence<Integer> smooth(Sequence<Integer> s1) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        Sequence<Integer> s2 = new Sequence1L<>();
        for (int i = 0; i < s1.length() - 1; i++) {
            int avg = (s1.entry(i) + s1.entry(i + 1)) / 2;
            s2.add(i, avg);
        }
        return s2;

//        if (s1.length() < 2) {
//            return new Sequence1L<>();
//        }
//        int first = s1.remove(0);
//        int avg = (first + s1.entry(0)) / 2;
//        Sequence<Integer> s2 = smooth(s1);
//        s2.add(0, avg);
//        s1.add(0, first);
//        return s2;
    }

    /**
     * Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    public static int average(int j, int k) {
        float num1 = j;
        float num2 = k;
        return (int) ((num1 + num2) / 2);
    }

}