
/**
 * A class that demonstrates side effects. 
 * 
 * @author Charles Bryan
 * @version 1.0
 *
 */
public final class SideEffect {

    /** Initial value for x. */
    private static final int INT_X = 5;
    
    /** A field that is side effected. */
    private static  int x = 5;

    
    /**
     * A side effecting method. 
     * 
     * @param theN add to x
     * @return theN added to x
     */
    public static int f(final int theN) {
        x = x * 2;
        return x + theN;
    }
    
    /**
     * Main method. 
     * @param theArgs command line arguments
     */
    public static void main(final String[] theArgs) {

        System.out.println(f(5));
        System.out.println(f(5));
        System.out.println(f(5));
    }

}
