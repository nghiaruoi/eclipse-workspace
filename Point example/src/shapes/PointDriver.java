package shapes;

import java.util.function.BiFunction;

/**
 * Practice with some Points.
 * 
 * @author Charles Bryan
 * @version Autumn 2019
 */
public final class PointDriver {
    
    /** Private constructor to prevent instantiation. */
    private PointDriver() {
        //do nothing
    }

    /**
     * The main method. 
     * @param theArgs command line arguments. 
     */
    public static void main(final String[] theArgs) {        
        demoTranslate();
    }
    
    public static void demoTranslate() {
        final Point p1 = new Point(2.0, 4.0);
        System.out.println(p1);
        
        //Simulate: f(x * 5.0 + 1.0, -y)
        final BiFunction<Double, Double, Point> translation = (dx, dy) -> 
            new Point(dx * 5.0 + 1.0, -1.0 * dy);
        
        final Point p2 = p1.translate(translation);
        System.out.println(p2);
         
    }

}
