import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Nuo Xu, Eric Gustafson
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Testing the constructor. This also test the map constructor with hashing
     * table size of 1009 if map4test1009 is ran.
     */
    @Test
    public void testConstructor1() {
        Map<String, String> ref = this.createFromArgsRef();
        Map<String, String> test = this.createFromArgsTest();

        assertEquals(ref, test);
    }

    /**
     * Testing the constructor with initial values. This also test the map
     * constructor with hashing table size of 1009 if map4test1009 is ran.
     */
    @Test
    public void testConstructor2() {
        Map<String, String> ref = this.createFromArgsRef("one", "1");
        Map<String, String> test = this.createFromArgsTest("one", "1");

        assertEquals(ref, test);
    }

    /**
     * Testing add with an initially empty map.
     */
    @Test
    public void testAdd1() {
        Map<String, String> ref = this.createFromArgsRef();
        Map<String, String> test = this.createFromArgsTest();
        ref.add("one", "1");
        test.add("one", "1");

        assertEquals(ref, test);
    }

    /**
     * Testing add with a map that has initial values.
     */
    @Test
    public void testAdd2() {
        Map<String, String> ref = this.createFromArgsRef("one", "1");
        Map<String, String> test = this.createFromArgsTest("one", "1");
        ref.add("two", "2");
        test.add("two", "2");

        assertEquals(ref, test);
    }

    /**
     * Testing add with the empty string as the key.
     */
    @Test
    public void testAdd3() {
        Map<String, String> ref = this.createFromArgsRef("one", "1");
        Map<String, String> test = this.createFromArgsTest("one", "1");
        ref.add("", "empty");
        test.add("", "empty");

        assertEquals(ref, test);
    }

    /**
     * Testing remove with maps with one value.
     */
    @Test
    public void testRemove1() {
        Map<String, String> ref = this.createFromArgsRef("one", "1");
        Map<String, String> test = this.createFromArgsTest("one", "1");
        Map.Pair<String, String> sref = ref.remove("one");
        Map.Pair<String, String> stest = test.remove("one");

        assertEquals(ref, test);
        assertEquals(sref, stest);
    }

    /**
     * Testing remove with maps with several values.
     */
    @Test
    public void testRemove2() {
        Map<String, String> ref = this.createFromArgsRef("one", "1", "two", "2",
                "three", "3");
        Map<String, String> test = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map.Pair<String, String> sref = ref.remove("two");
        Map.Pair<String, String> stest = test.remove("two");

        assertEquals(ref, test);
        assertEquals(sref, stest);
    }

    /**
     * Testing remove with maps with empty string key.
     */
    @Test
    public void testRemove3() {
        Map<String, String> ref = this.createFromArgsRef("one", "1", "two", "2",
                "three", "3", "", "empty");
        Map<String, String> test = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3", "", "empty");
        Map.Pair<String, String> sref = ref.remove("");
        Map.Pair<String, String> stest = test.remove("");

        assertEquals(ref, test);
        assertEquals(sref, stest);
    }

    /**
     * Testing remove any.
     */
    @Test
    public void testRemoveAny1() {
        Map<String, String> ref = this.createFromArgsRef("one", "1", "two", "2",
                "three", "3", "", "empty");
        Map<String, String> test = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3", "", "empty");

        Map.Pair<String, String> stest = test.removeAny();
        Map.Pair<String, String> sref = ref.remove(stest.key());

        assertEquals(ref, test);
        assertEquals(sref, stest);
    }

    /**
     * Testing value.
     */
    @Test
    public void testValue1() {
        Map<String, String> ref = this.createFromArgsRef("one", "1", "two", "2",
                "three", "3", "", "empty");
        Map<String, String> test = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3", "", "empty");

        String sref = ref.value("three");
        String stest = test.value("three");

        assertEquals(ref, test);
        assertEquals(sref, stest);
    }

    /**
     * Testing size.
     */
    @Test
    public void testSize1() {
        Map<String, String> ref = this.createFromArgsRef("one", "1", "two", "2",
                "three", "3", "", "empty");
        Map<String, String> test = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3", "", "empty");

        assertEquals(ref.size(), test.size());
    }

    /**
     * Testing size when size is 0.
     */
    @Test
    public void testSize2() {
        Map<String, String> ref = this.createFromArgsRef();
        Map<String, String> test = this.createFromArgsTest();

        assertEquals(ref.size(), test.size());
    }

    /**
     * Testing hasKey when true.
     */
    @Test
    public void testHasKey1() {
        Map<String, String> test = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3", "", "empty");

        assertEquals(test.hasKey("one"), true);
    }

    /**
     * Testing hasKey when false.
     */
    @Test
    public void testHasKey2() {
        Map<String, String> test = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3", "", "empty");

        assertEquals(test.hasKey("four"), false);
    }

}
