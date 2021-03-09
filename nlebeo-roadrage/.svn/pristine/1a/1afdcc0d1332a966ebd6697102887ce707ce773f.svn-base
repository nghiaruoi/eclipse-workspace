package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Bicycle class. Bicycle can travel on streets and through lights and 
 * crosswalk lights, but they prefer to travel on trails.
 * @author nle01
 * @version Winter 2021
 */
public class Bicycle extends AbstractVehicle {
    
    /**
     * Amount of time this object stay dead.
     */
    private static final int MY_DEATH_TIME = 35;

    /**
     * Constructor for bicycle object.
     * 
     * <p>Bicycles can travel on streets and through lights and crosswalk light,
     * but they prefer to travel on trails
     * 
     * @param theX the starting X coordinate of the object
     * @param theY the starting Y coordinate of the object
     * @param theDir the starting direction of the object
     */
    
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false;

        if (theTerrain == Terrain.TRAIL) {
            result = true;
        } else if (theTerrain == Terrain.STREET) {
            result = true;
        } else if (theTerrain == Terrain.LIGHT || theTerrain == Terrain.CROSSWALK) {
            if (theLight == Light.GREEN) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        Direction result = this.getDirection();

        final Map<Direction, Terrain> frontNeighbors = 
            new HashMap<Direction, Terrain>(theNeighbors);
        final Direction reverseNeighbor = this.getDirection().reverse();
        
        frontNeighbors.remove(this.getDirection().reverse());

        if (theNeighbors.get(this.getDirection()) == Terrain.TRAIL) {
            result = this.getDirection();
            
        } else if (theNeighbors.get(this.getDirection().left()) == Terrain.TRAIL) {
            result = this.getDirection().left();
            
        } else if (theNeighbors.get(this.getDirection().right()) == Terrain.TRAIL) {
            result = this.getDirection().right();
            
        } else if (isPassable(frontNeighbors, this.getDirection())) {
            result = this.getDirection(); 
            
        } else if (isPassable(frontNeighbors, this.getDirection().left())
                    || isPassable(frontNeighbors, this.getDirection().right())) {
            frontNeighbors.remove(this.getDirection());
            
            do {
                result = (Direction) frontNeighbors.keySet().toArray()
                        [new Random().nextInt(frontNeighbors.keySet().toArray().length)];
            } while (!isPassable(frontNeighbors, result));
            
        } else {
            result = reverseNeighbor;
        }
        
        return result;
    }
    
    private boolean isPassable(final Map<Direction, Terrain> theNeighbors, 
                               final Direction theDir) {
        
        boolean result = false;
        
        if (theNeighbors.get(theDir) == Terrain.STREET
                || theNeighbors.get(theDir) == Terrain.CROSSWALK
                || theNeighbors.get(theDir) == Terrain.LIGHT) {
            result = true;
            
        }
        
        return result;
    }
}
