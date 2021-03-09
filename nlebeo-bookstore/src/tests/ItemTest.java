package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import model.Item;
import model.ItemOrder;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests of the Item class.
 * 
 * @author Nghia (Tony) Le
 * @version Winter 2021
 *
 */
public class ItemTest {
    
    
    
    /** An Item will be used in the test. */
    private Item myItem;
    
    /** An Item with Bulk sale will be used in the test. */
    private Item myBulkItem;
    
    /**
     * A method to initialize the test fixture before each test.
     */
    @Before
    public void setUp() {
        myItem = new Item("simple test", new BigDecimal("1.99"));
        myBulkItem = new Item("test", new BigDecimal("2.99") , 5, new BigDecimal("9.99"));
    }
    
    /**
     * Test method to throw exception when item name is null.
     */
    
    @Test 
    public void testItemItemNPE() {  
        assertThrows("Item can't be null!", 
            NullPointerException.class, () -> new Item(null, new BigDecimal("1.99")));
    }
    
    /**
     * Test method to check for any negative price.
     */
    
    @Test 
    public void testItemItemIAE() {
        
        assertThrows("Price can not be negative!", IllegalArgumentException.class, 
            () -> new Item("test", new BigDecimal("-1.99")));
        
        assertThrows("Price can not be negative!", IllegalArgumentException.class, 
            () -> new Item("test", new BigDecimal("-1.99"), 5, new BigDecimal("2.99")));
        
        assertThrows("Bulk quantity can not be negative!", IllegalArgumentException.class, 
            () -> new Item("test", new BigDecimal("1.99"), -1, new BigDecimal("2.99")));
        
        assertThrows("Bulk price can not be negative!", IllegalArgumentException.class, 
            () -> new Item("test", new BigDecimal("1.99"), 5, new BigDecimal("-2.99")));
    }
    
    /**
     * Test method to check whether item can be sold in bulk.
     */
    
    @Test
    public void testIsBulk() {
        assertFalse("This is not a bulk item", myItem.isBulk());
        assertTrue("This is not a regular item", myBulkItem.isBulk());
    }
    
    /**
     * Simple test for getPrice method.
     */
    
    @Test
    public void testGetPrice() {
        assertEquals("Price getter is malfunction", myItem.getPrice(), new BigDecimal("1.99"));
        assertEquals("Price getter is malfunction", 
            myBulkItem.getPrice(), new BigDecimal("2.99"));
    }
    
    /**
     * Simple test to test getBulkQuantity method.
     */
    
    @Test
    public void testGetBulkQuantity() {
        assertEquals("Bulk item should not be negative!", myBulkItem.getBulkQuantity(), 5);
    }
    
    /**
     * Test for getBulkPrice method.
     */
    @Test
    public void testGetBulkPrice() {
        assertEquals("Bulk price getter malfunctions", 
            myBulkItem.getBulkPrice(), new BigDecimal("9.99"));
    }
    
    /**
     * Test for toString method. Testing the out put with a sample string.
     */
    @Test
    public void testToString() {
        assertEquals("toString method is not working!", 
            myItem.toString(), "simple test, $1.99");
        assertEquals("toString method is not working!", 
            myBulkItem.toString(), "test, $2.99 (5 for $9.99)");
    }
    
    /**
     * Test all possible cases for equals method.
     */
    
    @Test
    public void testEquals() {
        final Item testItem = new Item("simple test", new BigDecimal("1.99"));
        final Item testBulkItem = 
                        new Item("test", new BigDecimal("2.99") , 5, new BigDecimal("9.99"));
        final ItemOrder testItemOrder = new ItemOrder(myItem, 5);
        
        final String ERROR_MSG = "Two objects are not equal!";
        
        assertAll(() -> assertEquals(ERROR_MSG, myItem, myItem),
                  
            () -> assertFalse(ERROR_MSG, myItem.equals(testItemOrder)),
                  
            () -> assertNotEquals(ERROR_MSG, myItem, null),
        
            () -> assertNotEquals(ERROR_MSG, myItem, testBulkItem),
            
            () -> assertEquals(ERROR_MSG, myItem, testItem),
            
            () -> assertEquals(ERROR_MSG, testItem, myItem),
            
            () -> assertEquals(ERROR_MSG, myBulkItem, testBulkItem),
            
            () -> assertEquals(ERROR_MSG, testBulkItem, myBulkItem),
            
            () -> assertFalse(ERROR_MSG, 
                myItem.equals(new Item("simple", new BigDecimal("1.99")))),
            
            () -> assertFalse(ERROR_MSG, 
                myItem.equals(new Item("simple item", new BigDecimal("0.99")))),
        
            () -> assertFalse(ERROR_MSG, myBulkItem.equals(
                new Item("test", new BigDecimal("2.99"), 4, new BigDecimal("9.99")))),
                              
            () -> assertFalse(ERROR_MSG, myBulkItem.equals(
                new Item("test", new BigDecimal("2.99"), 5, new BigDecimal("8.99")))),
            
            () -> assertFalse(ERROR_MSG, myBulkItem.equals(
                new Item("simple test", new BigDecimal("2.99"), 5, new BigDecimal("9.99")))),
            
            () -> assertFalse(ERROR_MSG, myBulkItem.equals(
                new Item("test", new BigDecimal("1.99"), 5, new BigDecimal("9.99")))),
        
            () -> assertEquals(ERROR_MSG, myItem.hashCode(), testItem.hashCode()));
    }
    
//    /**
//     * Test Unit for orderByPrice method. I'm not sure how to implement orderByPrice
//     * into .sort().
//     */
//    
//    @Test
//    public void testOrderByPrice() {
//        
//        final ArrayList<Item> list = new ArrayList<Item>();
//        list.add(new Item("simple test", new BigDecimal("1.99")));
//        list.add(new Item("bulk test", new BigDecimal("2.99") , 5, new BigDecimal("9.99")));
//        list.add(new Item("test item", new BigDecimal("6.99") , 10, new BigDecimal("19.99")));
//        list.add(new Item("item test", new BigDecimal("6.99") , 10, new BigDecimal("19.99")));
//        
//        Collections.sort(list);
//        final String testString = "[bulk test, $2.99 (5 for $9.99), "
//                        + "simple test, $1.99, test item, $6.99 (10 for $19.99), "
//                        + "item test, $6.99 (10 for $19.99)]";
//        
//        assertEquals(list.toString(), testString);
//    }
//    
    /**
     * Test for sorting function, it should sort the Item list by name first, then by price.
     */
    
    @Test
    public void testCompareTo() {
        
        final ArrayList<Item> list = new ArrayList<Item>();
        list.add(new Item("simple test", new BigDecimal("1.99")));
        list.add(new Item("bulk test", new BigDecimal("2.99") , 5, new BigDecimal("9.99")));
        list.add(new Item("test item", new BigDecimal("6.99") , 10, new BigDecimal("19.99")));
        list.add(new Item("test item", new BigDecimal("7.99") , 10, new BigDecimal("19.99")));
        
        Collections.sort(list);
        final String testString = "[bulk test, $2.99 (5 for $9.99), "
                        + "simple test, $1.99, test item, $6.99 (10 for $19.99), "
                        + "test item, $7.99 (10 for $19.99)]";
        
        assertEquals(list.toString(), testString);
        
//      simply print the list out
//        assertFalse(list.toString(), false);
    }
   
                  


}

