package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Implement Truck class from AbtractVehicle class.
 * 
 * <p>truck can move in any direction on street, light, or crosswalk
 * 
 * @author nlebeo
 * @version Winter 2021
 */

public class Truck extends AbstractVehicle {
    
    /**
     * The amount of moves that this object stay dead.
     */
    private static final int MY_DEATH_TIME = 0;
    
    /**
     * List contains valid Terrain for Truck.
     */
    private ArrayList<Terrain> myValidTerrain = 
        new ArrayList<Terrain>(Arrays.asList(Terrain.CROSSWALK,
                                            Terrain.LIGHT,
                                            Terrain.STREET));
    
    
    /**
     * Constructor for Truck class using parent's constructor.
     * 
     * @param theX the original X coordinate of the object
     * @param theY the original Y coordinate of the object
     * @param theDir the original direction of the object
     */
    
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);

    }


    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        boolean result = false;
        if (myValidTerrain.contains(theTerrain)) {
            if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
                result = true;
            } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
                result = false;
            } else {
                result = true;
            }
        }
        
        return result;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = this.getDirection();
        
        final Map<Direction, Terrain> frontNeighbors = 
                        new HashMap<Direction, Terrain>(theNeighbors);
        
        final Direction reverse = this.getDirection().reverse();
        
        
        frontNeighbors.remove(reverse);
        
        if (frontNeighbors.containsValue(Terrain.CROSSWALK)
            || frontNeighbors.containsValue(Terrain.STREET)
            || frontNeighbors.containsValue(Terrain.LIGHT)) {
            do {
                result = Direction.random();
            } while (result == reverse
                        || (frontNeighbors.get(result) != Terrain.STREET
                        && frontNeighbors.get(result) != Terrain.CROSSWALK
                        && frontNeighbors.get(result) != Terrain.LIGHT));
        } else {
            result = reverse;
        }
        return result;
    }
    
}
