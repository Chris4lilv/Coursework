import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Nuo Xu, Eric Gustafson
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
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
     * test constructor.
     */
    @Test
    public void testConstructor() {
        Set<String> setRef = this.createFromArgsRef();
        Set<String> setTest = this.createFromArgsTest();

        assertEquals(setRef, setTest);
    }

    /**
     * test add.
     */
    @Test
    public void testAdd() {
        Set<String> setRef = this.createFromArgsRef("a", "b", "c");
        Set<String> setTest = this.createFromArgsTest("a", "b");

        setTest.add("c");
        assertEquals(setRef, setTest);

    }

    /**
     * test add when empty.
     */
    @Test
    public void testAdd1() {
        Set<String> setRef = this.createFromArgsRef("a");
        Set<String> setTest = this.createFromArgsTest();

        setTest.add("a");

        assertEquals(setRef, setTest);
    }

    /**
     * test remove.
     */
    @Test
    public void testRemove() {
        Set<String> setRef = this.createFromArgsRef("a", "b");
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");

        String entryRemove = setTest.remove("c");
        assertEquals(entryRemove, "c");
        assertEquals(setRef, setTest);
    }

    /**
     * test remove leaving empty.
     */
    @Test
    public void testRemove1() {
        Set<String> setRef = this.createFromArgsRef();
        Set<String> setTest = this.createFromArgsTest("a");

        String entryRemove = setTest.remove("a");
        assertEquals(entryRemove, "a");
        assertEquals(setRef, setTest);

    }

    /**
     * test removeAny.
     */
    @Test
    public void testRemoveAny() {
        Set<String> setRef = this.createFromArgsRef("a", "b", "c");
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");

        String entry = setTest.removeAny();
        assertTrue(setRef.contains(entry));
        setRef.remove(entry);
        assertEquals(setRef, setTest);

    }

    /**
     * test contains when true.
     */
    @Test
    public void testContains() {
        Set<String> setRef = this.createFromArgsRef("a", "b", "c");
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");

        boolean result = setTest.contains("b");
        assertEquals(setRef, setTest);
        assertEquals(true, result);
    }

    /**
     * test contains when false.
     */
    @Test
    public void testContains1() {
        Set<String> setRef = this.createFromArgsRef("a", "b", "c");
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");

        boolean result = setTest.contains("d");
        assertEquals(setRef, setTest);
        assertEquals(false, result);
    }

    /**
     * test contains when empty.
     */
    @Test
    public void testContains2() {
        Set<String> setRef = this.createFromArgsRef();
        Set<String> setTest = this.createFromArgsTest();

        boolean result = setTest.contains("a");
        assertEquals(setRef, setTest);
        assertEquals(false, result);
    }

    /**
     * test size when size is not zero.
     */
    @Test
    public void testSize() {
        Set<String> setTest = this.createFromArgsTest("a", "b", "c");
        Set<String> setRef = this.createFromArgsRef("a", "b", "c");

        int size = setTest.size();

        assertEquals(setTest, setRef);
        assertEquals(size, 3);
    }

    /**
     * test size when size is zero.
     */
    @Test
    public void testSize1() {
        Set<String> setTest = this.createFromArgsTest();
        Set<String> setRef = this.createFromArgsRef();

        int size = setTest.size();

        assertEquals(setTest, setRef);
        assertEquals(size, 0);
    }

}
