package model;

/**
 * Abstract parent class for all Vehicle.
 * 
 * @author nlebeo
 * @version 2021
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /** Vehicle's current x coordinate.*/
    private int myX;
    
    /** Vehicle's current y coordinate.*/
    private int myY;
    
    /** Vehicle's original x coordinate.*/
    private final int myOrigX;
    
    /** Vehicle's original y coordinate.*/
    private final int myOrigY;
    
    /** Vehicle's current direction.*/
    private char myDir;
    
    /** Vehicle's original direction.*/
    private final char myOrigDir;
    
    /** Vehicle death period.*/
    private final int myDeathTime;
    
    /** Vehicle's alive status. */
    private boolean myAliveStatus = true;
    
    /** Vehicle's amount of time after last death. */
    private int myPokeCount;
    
    
    /**
     * Constructor for AbstractVehicle, can only be used by its children class.
     * 
     * @param theX original X coordinate of the object
     * @param theY original Y coordinate of the object
     * @param theDir original direction of the object
     * @param theDeathTime the amount of time the object stays dead
     */
    
    protected AbstractVehicle(
        final int theX, final int theY, final Direction theDir, final int theDeathTime) {
        this.myX = theX;
        this.myOrigX = theX;
        this.myY = theY;
        this.myOrigY = theY;
        this.myDir = theDir.letter();
        this.myOrigDir = theDir.letter();
        this.myDeathTime = theDeathTime;
    }
    
    /**
     * Getter method for x coordinate.
     * 
     * @return y coordinate
     */
    
    public int getY() {
        
        return myY;
    }
    
    /**
     * Getter method for y coordinate.
     * 
     * @return x coordinate
     */
    public int getX() {
        return myX;
    }
    
    public int getDeathTime() {
        return myDeathTime;
    }
    
    /**
     * Getter method for direction.
     * 
     * @return direction of object
     */
    public Direction getDirection() {
        return Direction.valueOf(myDir);
          
    }
    
    /**
     *  Set new direction for the object.
     *  
     *  @param theDir new direction
     */
    public void setDirection(final Direction theDir) {
        myDir = theDir.letter();
    }
    

    /**
     * Set new X coordinate.
     * 
     * @param theX new X coordinate
     */
    public void setX(final int theX) {
        this.myX = theX;
    }
    
    /**
     * Set new Y coordinate.
     * 
     * @param theY new Y coordinate
     */
    public void setY(final int theY) {
        this.myY = theY; 
    }
    
    /**
     * Run the counter from when the vehicle dies.
     */
    public void poke() {
        myPokeCount++;
        if (myPokeCount > myDeathTime) {
            myAliveStatus = true;
            myPokeCount = 0;
            myDir = Direction.random().letter();
        }
    }
    
    /**
     * Return the vehicle to its original condition.
     */
    
    public void reset() {
        this.myX = this.myOrigX;
        this.myY = this.myOrigY;
        this.myDir = this.myOrigDir;
        myAliveStatus = true;
        myPokeCount = 0;
    }
    
    public boolean isAlive() {
        return myAliveStatus;
    }

    /**
     * Determine behavior of this vehicle when two vehicles collide.
     * 
     * @param theOther the other vehicle that collide with
     */
    
    public void collide(final Vehicle theOther) {
        final int tempCompare = this.getDeathTime() - theOther.getDeathTime();
        
        if (tempCompare > 0 && theOther.isAlive()) {
            this.myAliveStatus = false;
            myPokeCount++;
        } 
    }
    
    /**
     * Returning the image file of the object depends on the state of the object.
     * 
     * @return name of the image for the object
     */
    
    public String getImageFileName() {
        final String resultImg;
        
        if (this.isAlive()) {
            resultImg = this.getClass().getSimpleName().toLowerCase() + ".gif";
        } else {
            resultImg = this.getClass().getSimpleName().toLowerCase() + "_dead.gif";
        }
        
        return resultImg;
    }
}

