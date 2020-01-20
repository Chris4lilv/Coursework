import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Erik Gustafson, Nuo Xu
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /**
     * Sample test case adding from empty.
     */
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * Test case adding when not empty.
     */
    @Test
    public final void testAddNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue");
        m.add("blue");
        assertEquals(mExpected, m);
    }

    /**
     * Test case changing to extraction with empty.
     */
    @Test
    public final void testChangeToExtractionEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test case changing to extraction with data.
     */
    @Test
    public final void testChangeToExtractionNonEmpty1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test case changing to extraction with more data.
     */
    @Test
    public final void testChangeToExtractionNonEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green", "yellow", "orange", "purple");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "blue", "green", "yellow", "orange", "purple");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test case changing removing first leave empty.
     */
    @Test
    public final void testChangeRemoveFirstToEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        String str = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals("blue", str);
    }

    /**
     * Test case changing removing first leave non empty.
     */
    @Test
    public final void testChangeRemoveFirstToNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "blue",
                "green", "yellow", "orange", "purple");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "yellow", "orange", "purple");
        m.changeToExtractionMode();
        String str = m.removeFirst();
        assertEquals(mExpected, m);
        assertEquals("blue", str);
    }

    /**
     * Test case for is in insertion when it is.
     */
    @Test
    public final void testIsInInsertionTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        assertEquals(true, m.isInInsertionMode());

    }

    /**
     * Test case for is in insertion when it is not.
     */
    @Test
    public final void testIsInInsertionfalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        m.changeToExtractionMode();
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * Test case for size zero when it is in insertion mode.
     */
    @Test
    public final void testSizeZeroInInsertion() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        assertEquals(0, m.size());
    }

    /**
     * Test case for size zero when it is not in insertion mode.
     */
    @Test
    public final void testSizeZeroInExtraction() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        assertEquals(0, m.size());
    }

    /**
     * Test case for size 3 when it is not in insertion mode.
     */
    @Test
    public final void testSizeInExtraction() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "red",
                "green", "blue");
        assertEquals(3, m.size());
    }

    /**
     * Test case for size 3 when it is in insertion mode.
     */
    @Test
    public final void testSizeInInsertion() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red",
                "green", "blue");
        assertEquals(3, m.size());
    }
}
