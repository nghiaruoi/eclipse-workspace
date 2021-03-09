
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;
import org.junit.jupiter.api.Test;


/**
 * Unit test for class Truck.
 * 
 * @author nle01
 * @version Winter 2021
 *
 */
public class TruckTest {

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    
  
    
   
    @Test
    void testTruckConstructor() {
        final Truck t = new Truck(15, 20, Direction.NORTH);
        
        assertEquals(15, t.getX(), "Truck x coordinate not initialized correctly!");
        assertEquals(20, t.getY(), "Truck y coordinate not initialized correctly!");
        assertEquals(Direction.NORTH, t.getDirection(), 
                     "Truck direction not initialized correctly!");
        assertEquals(0, t.getDeathTime(), "Human death time not initialized correctly!");
        assertTrue(t.isAlive(), "Truck isAlive() fails initially!");
    }
    
    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        
        // Truck can move through STREET, LIGHT, and CROSSWALK
        // so we need to test all of those conditions
        
        // Truck should not go on GRASS or TRAIL
        // so we need to test that truck never move to other terrain types
        
        // Truck should only reverse direction if no other option is available
        // so we need to be sure to test that requirement also
        
        final List<Terrain> validTerrain = new ArrayList<>();
        
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.LIGHT);
                
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {
                    
                    // Truck can pass STREET under any light condition
                    assertTrue(truck.canPass(destinationTerrain, currentLightCondition), 
                               "Truck should be able to pass STREET" 
                                + ", with light " + currentLightCondition);
                } else if (destinationTerrain == Terrain.LIGHT) {
                    
                    // Truck can pass any street LIGHT
                    assertTrue(truck.canPass(destinationTerrain, currentLightCondition), 
                               "Truck should be able to pass LIGHT"
                                + ", with light " + currentLightCondition);
                    
                    // Truck can pass CROSSWALK with certain LIGHT condition
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    
                    // Truck can't pass RED CROSSWALK light
                    if (currentLightCondition == Light.RED) {
                        assertFalse(truck.canPass(destinationTerrain, currentLightCondition), 
                                    "Truck should not pass RED CROSSWALK light");
                    } else { // Truck can pass YELLOW or GREEN CROSSWALK light
                        assertTrue(truck.canPass(destinationTerrain, currentLightCondition), 
                                   "Truck should pass for YELLOW or GREEN CROSSWALK light");
                    }
                } else if (!validTerrain.contains(destinationTerrain)) {
 
                    assertFalse(truck.canPass(destinationTerrain, currentLightCondition), 
                                "Truck should not be able to pass " + destinationTerrain
                                + ", with light " + currentLightCondition);
                }  
            }
        }
    }
    
    /** Test method for Truck setters. */
    @Test
    public void testHumanSetters() {
        final Truck t = new Truck(10, 11, Direction.NORTH);
        
        t.setX(30);
        assertEquals(30, t.getX(), "Truck setX failed!");
        t.setY(29);
        assertEquals(29, t.getY(), "Truck setY failed!");
        t.setDirection(Direction.SOUTH);
        assertEquals(Direction.SOUTH, t.getDirection(), "Truck setDirection failed!");
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByValidTerrain() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.CROSSWALK);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = truck.chooseDirection(neighbors);
            
            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }
        
        assertTrue(seenWest && seenNorth && seenEast,
                   "Truck chooseDirection() fails to select randomly "
                                   + "among all possible valid choices!");
                 
        assertFalse(seenSouth, 
                    "Truck chooseDirection() reversed direction when not necessary!");
        
        // Test again just to be safe
        neighbors.put(Direction.WEST, Terrain.LIGHT);
        neighbors.put(Direction.NORTH, Terrain.CROSSWALK);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        neighbors.put(Direction.SOUTH, Terrain.CROSSWALK);
        
        seenWest = false;
        seenNorth = false;
        seenEast = false;
        seenSouth = false;
        
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = truck.chooseDirection(neighbors);
            
            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }
        
        assertTrue(seenWest && seenNorth && seenEast,
                   "Truck chooseDirection() fails to select randomly "
                                   + "among all possible valid choices!");
                 
        assertFalse(seenSouth, 
                    "Truck chooseDirection() reversed direction when not necessary!"); 
    }
      
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnGrassMustReverse() {
        
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {
                
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                
                final Truck truck = new Truck(0, 0, Direction.NORTH);
                
                // the Human must reverse and go SOUTH
                assertEquals("Truck chooseDirection() failed " + t
                                + "when reverse was the only valid choice!",
                             Direction.SOUTH, truck.chooseDirection(neighbors));
            }
                
        }
    }
}
