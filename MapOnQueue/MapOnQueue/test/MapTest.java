import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

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
     * Testing the Map constructor
     */
    @Test
    public void testConstructor() {
        Map<String, String> mapTest = this.constructorTest();
        Map<String, String> mapRef = this.constructorRef();

        assertEquals(mapTest, mapRef);
    }

    /**
     * Testing the add method
     */
    @Test
    public void testAdd() {
        Map<String, String> mapTest = this.createFromArgsTest("A", "a", "B",
                "b", "C", "c");
        Map<String, String> mapRef = this.createFromArgsRef("A", "a", "B", "b",
                "C", "c", "D", "d");

        mapTest.add("D", "d");

        assertEquals(mapTest, mapRef);
    }

    /**
     * Testing the remove method
     */
    @Test
    public void testRemove() {
        Map<String, String> mapTest = this.createFromArgsTest("A", "a", "B",
                "b", "C", "c");
        Map<String, String> mapRef = this.createFromArgsRef("A", "a", "B", "b");

        mapTest.remove("C");

        assertEquals(mapTest, mapRef);
    }

    /**
     * Testing the removeAny method
     */
    @Test
    public void testRemoveAny() {
        Map<String, String> mapTest = this.createFromArgsTest("A", "a", "B",
                "b", "C", "c");
        Map<String, String> mapRef = this.createFromArgsRef("A", "a", "B", "b",
                "C", "c");

        Pair<String, String> entry = mapTest.removeAny();

        assertTrue(mapTest.hasKey(entry.key()));
        mapRef.remove(entry.key());
        assertEquals(mapTest, mapRef);
    }

    /**
     * Testing the value method
     */
    @Test
    public void testValue() {
        Map<String, String> mapTest = this.createFromArgsTest("A", "a", "B",
                "b", "C", "c");

        String valueExpected = "a";
        String value = mapTest.value("A");
        assertEquals(valueExpected, value);
    }

    /**
     * Testing the hasKey method with true
     */
    @Test
    public void testHasKey() {
        Map<String, String> mapTest = this.createFromArgsTest("A", "a", "B",
                "b", "C", "c");
        boolean resultExpected = true;
        boolean result = mapTest.hasKey("B");
        assertEquals(resultExpected, result);
    }

    /**
     * Testing the hasKey method with false
     */
    @Test
    public void testHasKey1() {
        Map<String, String> mapTest = this.createFromArgsTest("A", "a", "B",
                "b", "C", "c");
        boolean resultExpected = false;
        boolean result = mapTest.hasKey("D");
        assertEquals(resultExpected, result);
    }

    /**
     * Testing the size method
     */
    @Test
    public void testSize() {
        Map<String, String> test = this.createFromArgsTest("one", "1", "two",
                "2");

        assertTrue(test.size() == 2);
    }

    /**
     * Testing the size method when size is zero
     */
    @Test
    public void testSize1() {
        Map<String, String> mapTest = this.createFromArgsTest();

        int sizeExpected = 0;
        int size = mapTest.size();

        assertEquals(sizeExpected, size);
    }
}