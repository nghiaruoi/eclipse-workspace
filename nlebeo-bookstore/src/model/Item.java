

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Item class with either 2 fields, name and price or
 * 4 fields with 2 additional bulk quantity and bulk price.
 * 
 * @author Nghia (Tony) Le
 * @version Winter 2021
 *
 */

public final class Item implements Comparable<Item> {
    
    
    /**
     * Item name.
     */ 
    
    private String myItemName;
    
    /**
     * Price of the item.
     */
    
    private BigDecimal myItemPrice;
    
    /**
     * The bulk quantity.
     */
    private int myBulkQuantity;
    
    /**
     * Price for the bulk quantity.
     */
    
    private BigDecimal myBulkPrice = new BigDecimal("0.00"); 
    

    /**
     *  variable used to format currency output for myPrice and myBulkPrice. 
     */
    
    private NumberFormat myNF = NumberFormat.getCurrencyInstance(Locale.US);
    
    /**
     * Error message when item passed in is null.
     */
    
    private static final String ERROR_NULL_ITEM = "Item name must not be null";
    
    /**
     * Construct item object with only name and price.
     * 
     * <p>Empty constructor
     * 
     * @param theName name of the item
     * @param thePrice price of the item
     * @throws IllegalArgumentException throw exception in case thePrice is negative
     * @throws NullPointerException throw exception if theName is null
     */
    
    public Item(final String theName, final BigDecimal thePrice) 
                    throws IllegalArgumentException {
        
        if (thePrice.compareTo(BigDecimal.valueOf(0)) == -1) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        
        myItemName = Objects.requireNonNull(theName, ERROR_NULL_ITEM);
        
        myItemPrice = thePrice;
    }

    /**
     * Constructor to create item object with name, price, bulk price, bulk quantity
     * 
     * <p>This item can also be sold in bulk.
     * 
     * @param theName name of the item
     * @param thePrice price of the item
     * @param theBulkQuantity item quantity needed to sell in bulk
     * @param theBulkPrice price tag for the amount of item in bulk
     * @throws IllegalArgumentException throw exception when either 
     *                              thePrice or theBulkPrice is negative
     * @throws NullPointerException if theName is null
     */
    
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) throws IllegalArgumentException {
        
        if (thePrice.doubleValue() < 0.0) {
            throw new IllegalArgumentException("Price must not be negative");
        }
        
        if (theBulkPrice.doubleValue() < 0.0) {
            throw new IllegalArgumentException("Bulk price must not be negative");
        }
        
        if (theBulkQuantity < 0) {
            throw new IllegalArgumentException("Bulk quantity must not be negative");
        }
        
        myItemName = Objects.requireNonNull(theName, ERROR_NULL_ITEM);
        
        myItemPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;       
    }

    //***************************
    //    Public Methods
    //**************************
    

    /**
     *Get the price of one single Item.
     *  
     * @return item price
     */
    
    public BigDecimal getPrice() {
        return myItemPrice;
    }

    /**
     * Get bulk quantity of the item.
     * 
     * @return bulk quantity
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }
    
    /**
     * Get the bulk price of the item.
     * 
     * @return bulk price
     */

    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /**
     * Determine whether the item can be sold in bulk.
     * 
     * @return bulk or not
     */
    public boolean isBulk() {
        return myBulkQuantity > 0;
    }


    @Override
    public String toString() {
        
        final String itemName;
        if (isBulk()) {
            itemName =  myItemName + ", " + myNF.format(myItemPrice) 
                + " (" + myBulkQuantity + " for " + myNF.format(myBulkPrice) + ")";
        } else {
            itemName = myItemName + ", " + myNF.format(myItemPrice);
        }
        
        return itemName;
    }
    
    /**
     * Override default equals with my own method.
     * 
     * <p>I did google some example code from GeeksforGeeks
     */
    
    @Override
    public boolean equals(final Object theOther) {
        
        boolean result = false;
        
        // it checks if the argument is null or of the type Item
        if (theOther == null || theOther.getClass() != this.getClass()) { 
            return false;
        }
        
        // it checks whether both variable point to the same object
        if (this == theOther) {
            result = true; 
        }
        
        final Item item = (Item) theOther;
        
        // check every single element of the Item object with theOther
        if (item.myItemName.equals(this.myItemName) 
                        && item.myItemPrice.equals(this.myItemPrice) 
                        && item.myBulkQuantity == this.myBulkQuantity 
                        && item.myBulkPrice.equals(this.myBulkPrice)) {
            result = true;
        }
        return result;
    }

    @Override
    public int hashCode() {

        return Objects.hash(myItemName, myItemPrice, myBulkPrice, myBulkQuantity);
    }
    
    /**
     * Override for compareTo method. the first line is for sorting Item by name,
     * the second line is for sorting Item by price in case the Items have identical name.
     */
    @Override
    public int compareTo(final Item theOther) { 
        
        final int last = this.myItemName.compareTo(theOther.myItemName);
        
        return last == 0 ? (this.myItemPrice.compareTo(theOther.myItemPrice)) : last;
    }
    
    /**
     * Comparing price of this Item and theOther. 
     * 
     * 
     * @param theOther compare this Item to theOther
     * @return a negative double if this Item's price is smaller than theOther's price
     *          a 0.0 if both item has the same price
     *          a positive double if this Item's price is larger than theOther's price
     */
    
    public double orderByPrice(final Item theOther) {
        final int last = this.myItemPrice.compareTo(theOther.myItemPrice);
        
        return last == 0 ? this.myItemName.compareTo(theOther.myItemName) *-1 : last;
                       
    }
    
    

}
