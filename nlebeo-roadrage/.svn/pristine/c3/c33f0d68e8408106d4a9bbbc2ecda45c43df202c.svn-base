package model;

import java.util.Map;
/**
 * Car class, Cars can only travel on streets and through lights and crosswalks.
 *
 * @author nle01
 * @version Winter 2021
 */
public class Car extends AbstractVehicle {
    
    /**
     * Amount of time this object stay dead.
     */
    private static final int MY_DEATH_TIME = 15;
    
    /**
     * Constructor for car object.
     * 
     * @param theX object's original x coordinate
     * @param theY object's original y coordinate
     * @param theDir object's original direction
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false;
        
        if (theTerrain == Terrain.STREET) {
            result = true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            result = true;
        } else if (theTerrain == Terrain.LIGHT && theLight != Light.RED) {
            result = true;
        } else {
            result = false;
        }
        
        return result;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = this.getDirection();
        
        if (isPassable(theNeighbors, this.getDirection())) {
            result = this.getDirection();
        } else if (isPassable(theNeighbors, this.getDirection().left())) {
            result = this.getDirection().left();
        } else if (isPassable(theNeighbors, this.getDirection().right())) {
            result = this.getDirection().right();
        } else {
            result = this.getDirection().reverse();
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
