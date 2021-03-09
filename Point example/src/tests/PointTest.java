/*
 * Unit test demo code for TCSS 305
 */

package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import shapes.Point;

/**
 * Tests of the Point class.
 * 
 * @author Charles Bryan
 * @author Alan Fowler
 * @version Autumn 2019
 */
public class PointTest {
    
    /*
     * NOTE about double values and unit tests:
     * 
     * There is no clean way to compare primitive doubles or floats for equality.
     * Floating point calculations are not exact - there are often round-off errors
     * and errors due to imprecise representation in memory.
     * (For example, 0.1 cannot be exactly represented in binary floating point.)
     * Because of this, directly comparing two floating point values for equality is
     * usually not a good idea, because they can be different by a small amount,
     * depending upon how they were computed.
     * 
     * assertEquals provides an overloaded version which accepts an 'epsilon' value
     * or 'tolerance' to use when comparing primitive floating point values for equality.
     * Below I declare a constant to be used throughout this test class whenever there is a
     * need to compare floating point values for equality.
     */
    
    /** A tolerance used when comparing double values for equality. */
    private static final double TOLERANCE = .000001;
    
    // The test fixture. The Objects I will use in the tests.
    /** A Point to use in the tests. */
    private Point myPoint;
    

    /**
     * A method to initialize the test fixture before each test.
     */
    @Before // This method runs before EACH test method.
    public void setUp() { // no need to throw any exceptions!
        myPoint = new Point();
    }
    
    /**
     * Uses JUnit 4 Syntax when checking for an exception.
     */
    @Test (expected = NullPointerException.class)
    public void testPointPointNPE() {
        new Point(null);
        //never get here....
    }
    
    
    /**
     * Uses JUnit 5 Syntax when checking for an exception.
     */
    @Test
    public void testPointPointNPEJUnit5() {
        assertThrows(NullPointerException.class, () -> new Point(null));
    }


    /**
     * Test method for {@link Point#calculateDistance(Point)}.
     * Uses JUnit 4 Syntax
     */
    @Test
    public void testCalculateDistance() {
        assertEquals("testCalculateDistance failed!", 5,
                     myPoint.calculateDistance(new Point(3, 4)), TOLERANCE);

        assertEquals("testCalculateDistance failed!", 5,
                     myPoint.calculateDistance(new Point(-3, -4)), TOLERANCE);

        assertEquals("testCalculateDistance failed!", 0,
                     myPoint.calculateDistance(new Point(0, 0)), TOLERANCE);
    }
    
    /**
     * Test method for {@link Point#calculateDistance(Point)}.
     * Uses JUnit 5 Syntax
     */
    @Test
    public void testCalculateDistanceJUnit5() {
        assertAll("testCalculateDistance failed!", 
            () -> assertEquals(5, myPoint.calculateDistance(new Point(3, 4)), TOLERANCE),
            () -> assertEquals(5, myPoint.calculateDistance(new Point(-3, -4)), TOLERANCE),
            () -> assertEquals(0, myPoint.calculateDistance(new Point(0, 0)), TOLERANCE)
        );
    }   

    /**
     * Test method for calculateDistance(Point) when the parameter is null}.
     * Uses JUnit 4 Syntax when checking for an exception.
     */
    @Test(expected = NullPointerException.class)
    public void testCalculateDistanceNull() {
        // No assertion is needed when testing an expected exception.
        // Just call code which should result in the expected exception.
        myPoint.calculateDistance(null);
    }
    
    /**
     * Test method for calculateDistance(Point) when the parameter is null}.
     * Uses JUnit 4 Syntax when checking for an exception.
     */
    public void testCalculateDistanceNullJUnit5() {
        assertThrows(NullPointerException.class, () -> myPoint.calculateDistance(null));
    }

    
    /** 
     * Test of the equals() method. 
     * Uses JUnit 4 Syntax
     */
    @Test
    public void testEquals() {
        // an object is equal to itself - reflexive property
        assertEquals("equals() fails a test of the reflexive property.", myPoint, myPoint);

        // .equals() should return false if the parameter is null        
        assertNotEquals("equals() fails to return false when passed a null parameter",
                        myPoint, null);

        // .equals() should return false if the parameter is a different type
        assertNotEquals("equals() fails to return false when passed the wrong parameter type",
                    myPoint, new Color(10, 20, 30));

        // the symmetric property should hold
        final Point point2 = new Point(); // location (0, 0) same as myPoint
        assertEquals("equals() fails a test of the symmetric property.",
                     myPoint, point2);
        assertEquals("equals() fails a test of the symmetric property.",
                     point2, myPoint);

        // Points with different x coordinates should not be considered equal
        assertFalse("equals() fails to return false when x coordinates do not match.",
                    myPoint.equals(new Point(1, 0)));

        // Points with different y coordinates should not be considered equal
        assertFalse("equals() fails to return false when y coordinates do not match.",
                    myPoint.equals(new Point(0, 1)));
    }
    
    /** 
     * Test of the equals() method. 
     * Uses JUnit 5 Syntax
     */
    @Test
    public void testEqualsJUnit5() {
        final Point point2 = new Point(); // location (0, 0) same as myPoint
        
        assertAll("Tests for .equals()",
                  
            // an object is equal to itself - reflexive property
            () -> assertEquals(
                "equals() fails a test of the reflexive property.", 
                myPoint, myPoint),

            // .equals() should return false if the parameter is null
            () -> assertNotEquals(
                "equals() fails to return false when passed null",
                myPoint, null),
            
            // .equals() should return false if the parameter is a different type
            () ->   assertNotEquals(
                "equals() fails to return false when passed the wrong parameter type",
                myPoint, new Color(10, 20, 30)),

            // the symmetric property should hold
            () -> assertEquals(
                "equals() fails a test of the symmetric property.",
                myPoint, point2),        
            () -> assertEquals(
                "equals() fails a test of the symmetric property.",
                point2, myPoint),

            // Points with different x coordinates should not be considered equal
            () -> assertFalse(
                "equals() fails to return false when x coordinates do not match.",
                myPoint.equals(new Point(1, 0))),

            // Points with different y coordinates should not be considered equal
            () -> assertFalse(
                "equals() fails to return false when y coordinates do not match.",
                myPoint.equals(new Point(0, 1))));
    }

    /**
     * Test method for {@link Point#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("toString() produced an unexpected result!",
                     "Point (0.00, 0.00)", myPoint.toString());
    }
    
    /** Test of the hashCode() method. */
    @Test
    public void testHashCode() {
        // Equal objects should have equal hashCode values.
        final Point point2 = new Point();

        assertEquals("hashCode() fails to produce identical values for"
                        + " equal ImmutablePoints",
                     myPoint.hashCode(), point2.hashCode());
    }
    
}
