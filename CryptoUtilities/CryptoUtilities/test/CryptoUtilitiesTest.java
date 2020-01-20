import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Put your name here
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("0", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber m = new NaturalNumber2(21);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("3", n.toString());
        assertEquals("0", m.toString());
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("0", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("1", n.toString());
        assertTrue(!result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("2", m.toString());
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("18", p.toString());
        assertEquals("19", m.toString());
    }

    @Test
    public void testPowerMod_2_2_4() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber p = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(4);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("0", n.toString());
        assertEquals("2", p.toString());
        assertEquals("4", m.toString());
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    @Test
    public void testIsWitnessToCompositeness_2_4() {
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber four = new NaturalNumber2(4);
        boolean result = CryptoUtilities.isWitnessToCompositeness(two, four);
        assertTrue(result);

    }

    @Test
    public void testIsWitnessToCompositeness_3_5() {
        NaturalNumber three = new NaturalNumber2(3);
        NaturalNumber five = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isWitnessToCompositeness(three, five);
        assertFalse(result);

    }

    /*
     * Test of isPrime2
     */
    @Test
    public void testIsPrime2_5() {
        NaturalNumber five = new NaturalNumber2(5);
        boolean result = CryptoUtilities.isPrime2(five);
        assertFalse(result);

    }

    @Test
    public void testIsPrime2_13() {
        NaturalNumber thirteen = new NaturalNumber2(13);
        boolean result = CryptoUtilities.isPrime2(thirteen);
        assertFalse(result);

    }

    /*
     * Test of generateNextLikelyPrime
     */
    @Test
    public void testGenerateNextLikelyPrime_8() {
        NaturalNumber eight = new NaturalNumber2(8);
        CryptoUtilities.generateNextLikelyPrime(eight);
        assertEquals("11", eight.toString());
    }

    @Test
    public void testGenerateNextLikelyPrime_11() {
        NaturalNumber eleven = new NaturalNumber2(11);
        CryptoUtilities.generateNextLikelyPrime(eleven);
        assertEquals("13", eleven.toString());
    }

}