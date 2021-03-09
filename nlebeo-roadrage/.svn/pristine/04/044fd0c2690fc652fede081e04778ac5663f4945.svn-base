package model;

import java.util.Map;

/**
 * Atv class. ATVs can travel on any terrain except walls.
 * 
 * @author nle01
 * @version Winter 2021
 *
 */
public class Atv extends AbstractVehicle {

    /**
     * The time the vehicle needs to stay dead.
     */
    private static final int MY_DEATH_TIME = 25;
    
    /**
     * Constructor for ATV object, using parent class constructor.
     * 
     * <p>ATV can travel to any terrain except walls.
     * @param theX the starting X coordinate of the object
     * @param theY the starting Y coordinate of the object
     * @param theDir the starting direction of the object
     */
    
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME); 
        
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false;
        
        if (theTerrain != Terrain.WALL) {
            result = true;
        }
        return result;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = this.getDirection();
        
        do {
            result = Direction.random();
        } while (theNeighbors.get(result) == Terrain.WALL 
                    || result == this.getDirection().reverse());
        
        return result;
    }

}
