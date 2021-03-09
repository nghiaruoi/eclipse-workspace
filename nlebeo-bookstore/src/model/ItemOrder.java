package model;

import java.util.Objects;

/**
 * ItemOrder class take in an Item object and quantity of the Item.
 * 
 * @author Nghia (Tony) Le
 * @version Winter 2021
 *
 */

public final class ItemOrder {

    /**
     * name for the item.
     */
    
    private Item myItem;
    
    /**
     * Quantity of the item.
     */
    
    private int myItemQuantity;
    
    //*********************
    //     Constructor
    //*********************
    
    /**
     * Construct a ItemOrder object with the Item object and quantity of the item.
     * 
     * @param theItem is the Item object with name of the item, price, and sometimes bulk price
     * @param theQuantity quantity of the item
     * @throws IllegalArgumentException throw exception in case the quantity is negative
     * @throws NullPointerException throw exception if theItem is null
     */
    
    public ItemOrder(final Item theItem, final int theQuantity) 
                    throws IllegalArgumentException {
        
        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        
        myItem = Objects.requireNonNull(theItem, "Item must not be null");
        myItemQuantity = theQuantity;
    }
    
    //********************
    //   Public Methods
    //********************
    
    /**
     * get Item from ItemOrder.
     * 
     * @return Item
     */
    
    public Item getItem() {
        return this.myItem;
    }
    
    /**
     * Get quantity of the Item.
     * 
     * @return quantity
     */
    
    public int getQuantity() {
        return myItemQuantity;
    }
    

    @Override
    public String toString() {  
        if (myItemQuantity == 1) {
            return "1 " + myItem;
        } else if (myItemQuantity > 1) {
            return myItemQuantity + " " + myItem + "s";
        } else {
            return "There is no item";
        }
            
    }

}
