package model;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Implements Human class from abstract class AbstractVehicle.
 * 
 * <p>Human moves in random direction on grass or cross walks.
 * 
 * <p>Human never reverses direction unless there is no other option
 * 
 * @author nlebeo
 * @version Winter 2021
 */

public class Human extends AbstractVehicle {
    
    /**
     * Amount of moves this object stays dead.
     */
    private static final int MY_DEATH_TIME = 45;
    
    /**
     * Constructor for Human class, initiate using parent's class constructor.
     * 
     * @param theX the X coordinate of the object
     * @param theY the Y coordinate of the object
     * @param theDir the direction of the object
     */
    
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }

    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        final Map<Direction, Terrain> movableNeighbor = 
                new HashMap<Direction, Terrain>(theNeighbors);
        final Direction reverseDir = this.getDirection().reverse();
        
        Direction result = this.getDirection();
        
        movableNeighbor.remove(this.getDirection().reverse());
        
        if (movableNeighbor.containsValue(Terrain.CROSSWALK)) {
            do {
                result = getRandomDirection(movableNeighbor);
            } while (!movableNeighbor.get(result).equals(Terrain.CROSSWALK));
            
        } else if (movableNeighbor.containsValue(Terrain.GRASS)) {
            do {
                result = getRandomDirection(movableNeighbor);
            } while (!(movableNeighbor.get(result) == Terrain.GRASS));
            
        } else if (!movableNeighbor.containsValue(Terrain.GRASS) 
                    && !movableNeighbor.containsValue(Terrain.CROSSWALK)) {
            result = reverseDir;
        }
    
            
        return result;
    }

    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        boolean result = false;
        
        
        if (theTerrain == Terrain.GRASS) {
            result = true;
        } else if (theTerrain.equals(Terrain.CROSSWALK)) {
            if (theLight == Light.RED || theLight == Light.YELLOW) {
                result = true;
            } else {
                result = false;
            }
        }
        
        return result;
    }

    
    private Direction getRandomDirection(final Map<Direction, Terrain> theNeighbors) {
        return (Direction) theNeighbors.keySet().toArray()
            [new Random().nextInt(theNeighbors.keySet().toArray().length)];
    }
}
