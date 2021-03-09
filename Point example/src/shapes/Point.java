/*
 * TCSS 305
 * 
 * A simple mutable 2D Point class to demonstrate some class design
 * considerations.
 */

package shapes;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Represents a 2-dimensional point on a plane.
 * 
 * @author Alan Fowler acfowler@uw.edu
 * @author Charles Bryan
 * @version Winter 2020
 */
public class Point {

    // constants (static final fields)
    
    /** A amount of tolerance for equality. */
    public static final double TOLERANCE = 0.000001; 
    
    /** A default value for the x coordinate. */
    public static final double DEFAULT_X = 0.0;

    /** A default value for the y coordinate. */
    public static final double DEFAULT_Y = 0.0;

    /** A format string for decimal numbers. */
    private static final String FORMAT = "%.2f"; // used to display 2 decimal places

    // instance fields

    /** The x coordinate. */
    private double myX;

    /** The y coordinate. */
    private double myY;

//     constructors
//    /**
//     * Constructs a point at the origin (0, 0).
//     */
    public Point() {
        this(DEFAULT_X, DEFAULT_Y);
//         myX = DEFAULT_X;
//         myY = DEFAULT_Y;
    }
    
    /**
     * Constructs a point using the provided coordinates.
     * 
     * @param theX The x coordinate to assign to this point.
     * @param theY The y coordinate to assign to this point.
     */
    public Point(final double theX, final double theY) {
        super();
        myX = theX;
        myY = theY;

        // OR if the setters are 'final'
//        setX(theX);
//        setY(theY);
    }
    
    /**
     * This copy constructor creates a new Point as a copy of the specified
     * Point.
     * 
     * @param theOtherPoint a Point object to copy.
     * @throws NullPointerException if the parameter is null
     */
    public Point(final Point theOtherPoint) {
        super();
        // or use a Java 7 feature to test for null
        myX = Objects.requireNonNull(theOtherPoint).myX;
        myY = theOtherPoint.myY;

        // OR call the overloaded constructor
//         this(theOtherPoint.myX, theOtherPoint.myY);
    }
    
    // queries (sometimes called 'accessors'; sometimes called 'getters')

    /**
     * What is the x coordinate?
     * 
     * @return the x coordinate
     */
    public double getX() {
        return myX;
    }

    /**
     * What is the y coordinate?
     * 
     * @return the y coordinate
     */
    public double getY() {
        return myY;
    }

    /**
     * What is the distance to the specified point?
     * 
     * precondition: theOtherPoint is not null
     * 
     * @param theOtherPoint The other point.
     * @return the distance from the current point to the specified point
     * @throws NullPointerException if theOtherPoint is null
     */
    public double calculateDistance(final Point theOtherPoint) {        
        final double dx = myX - Objects.requireNonNull(theOtherPoint).myX;
        final double dy = myY - theOtherPoint.myY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // commands (sometimes called 'mutators'; sometimes called 'setters')

    /** 
     * Set the x-coordinate to the specified value!
     * 
     * @param theX the new x value to set
     */
    public final void setX(final double theX) {
        myX = theX;
    }

    /**
     * Set the y-coordinate to the specified value!
     * 
     * @param theY the new y value to set
     */
    public final void setY(final double theY) {
        myY = theY;
    }

    /**
     * Translate by the specified values!
     * 
     * @param theX The x distance to translate by.
     * @param theY The y distance to translate by.
     * @return a new Point translated by ...
     */
    public Point translate(final double theX, final double theY) {
        return new Point(myX + theX, myY + theY);
    }
    
    /**
     * Translate by the specified function!
     * 
     * @param theTranslationFunction the function to translate this (x, y) by
     * @return a new Point translated by the input function
     */
    public Point translate(final BiFunction<Double, Double, Point> theTranslationFunction) {
        return theTranslationFunction.apply(myX, myY);
    }

    // overridden methods from class Object
    
    /**
     * Returns a String containing the coordinate pair with a label : Point (x, y).
     * The coordinates are formatted to show 2 decimal places.
     */
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder(); // size 16 buffer by default
        sb.append(getClass().getSimpleName()); // the class name without the
                                               // package name
        sb.append(" (");
        sb.append(String.format(FORMAT, myX)); // display 2 decimal places
        sb.append(", ");
        sb.append(String.format(FORMAT, myY)); // display 2 decimal places
        sb.append(')'); // use single quotes '' to indicate a char
                        // use double quotes "" to indicate a String
        return sb.toString();

        /*
         * In modern VMs this compiles to the same byte code as return "(" +
         * String.format(FORMAT, myX) + ", " + String.format(FORMAT, myY) + ")";
         * because modern compilers can optimize string concatenations which
         * occur on a single line of code.
         */
    }
    
    /**
     * Returns true if the parameter is a Point with identical coordinates
     * as the current Point; false otherwise.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object theOther) { // always Object, not this class! 
     
        boolean result = false;
        if ((theOther != null) && (theOther.getClass() == this.getClass())) {
            // cast theOther to the correct type
            final Point otherPoint = (Point) theOther;
            
            // now compare the x and y coordinates for equality
            
            /*
             * There is no clean way to compare primitive doubles or floats for equality.
             * Floating point calculations are not exact - there are often round-off errors
             * and errors due to imprecise representation in memory.
             * (For example, 0.1 cannot be exactly represented in binary floating point.)
             * Because of this, directly comparing two floating point values for equality is
             * usually not a good idea, because they can be different by a small amount,
             * depending upon how they were computed.
             * 
             * Below I show three ways to do it.
             */
            
            // Double.compare() return 0 for equals values
//            result = Double.compare(myX, otherPoint.myX) == 0
//                  && Double.compare(myY, otherPoint.myY) == 0;
            
            //OR
//            result = Double.doubleToLongBits(myX) == Double.doubleToLongBits(otherPoint.myX)
//                  && Double.doubleToLongBits(myY) == Double.doubleToLongBits(otherPoint.myY);
            
            //OR
//            // Define a tolerance to use.
            final double tolerance = .00001;
            result = Math.abs(myX - otherPoint.myX) < tolerance
                  && Math.abs(myY - otherPoint.myY) < tolerance;
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(myX, myY); // hash the same fields that .equals() uses.       
    }

}
















