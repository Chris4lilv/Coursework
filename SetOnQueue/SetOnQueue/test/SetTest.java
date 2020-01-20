import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Nuo Xu
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * test for constructor
     */
    @Test
    public void testConstructor() {
        Set<String> set = this.constructorTest();
        Set<String> setExpected = this.constructorRef();

        assertEquals(set, setExpected);
    }

    /**
     * test for add method.
     */
    @Test
    public void testAdd() {
        Set<String> set = this.createFromArgsTest("a", "b", "c");
        Set<String> setExpected = this.createFromArgsRef("a", "b", "c", "d");

        set.add("d");
        assertEquals(set, setExpected);
    }

    /**
     * test for remove method.
     */
    @Test
    public void testRemove() {
        Set<String> set = this.createFromArgsTest("a", "b", "c");
        Set<String> setExpected = this.createFromArgsRef("a", "b");

        set.remove("c");
        assertEquals(set, setExpected);
    }

    /**
     * test for removeAny method.
     */
    @Test
    public void testRemoveAny() {
        Set<String> set = this.createFromArgsTest("a", "b", "c");
        Set<String> setExpected = this.createFromArgsRef("a", "b", "c");

        String random = set.removeAny();

        assertTrue(setExpected.contains(random));
        setExpected.remove(random);
        assertEquals(set, setExpected);
    }

    /**
     * test for contains method.
     */
    @Test
    public void testContains() {
        Set<String> set = this.createFromArgsTest("a", "b", "c");

        boolean entry = set.contains("c");
        assertEquals(entry, true);
    }

    /**
     * test for contains method when set is empty.
     */
    @Test
    public void testContainsEmpty() {
        Set<String> set = this.createFromArgsTest();

        boolean entry = set.contains("c");
        assertEquals(entry, false);
    }

    /**
     * test for size method.
     */
    @Test
    public void testSize() {
        Set<String> set = this.createFromArgsTest("a", "b", "c");
        int length = set.size();
        assertEquals(length, 3);
    }
}