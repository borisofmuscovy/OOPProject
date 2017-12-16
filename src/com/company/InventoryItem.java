package com.company;
import java.text.DecimalFormat;
import java.math.RoundingMode;

/**
 * Abstract class of Inventory Items
 * Superclass of weapons ans purses
 */
public abstract class InventoryItem {

    public int ID;
    public float value;
    public float weight;
    public Monster holder;

    {
        value = 0;
        weight = 0;
    }

    /**
     * Constructor of inventory item: purse or weapon
     * Initializes item ID depending on item type:
     * Random odd long for weapons
     * Random number from fibonacci series for purses
     *
     * @param value Parameter describing value of the purse no smaller than zero
     *              | !(value < 0)
     * @param holder
     *        Monster that carries the inventory
     * @throws IllegalArgumentException If weight of the weapon is smaller than zero
     *                                  | weight < 0
     */
    public InventoryItem(float weight, float value, Monster holder) throws IllegalArgumentException {
        assert (!(value < 0));
        this.value = value;
        this.holder = holder;
        this.weight = weight;
    }

    public InventoryItem(float weight, float value) throws IllegalArgumentException {
        assert (!(value < 0));
        this.value = value;
        this.holder = null;
        this.weight = weight;
    }

    /**
     * Sets new value of the item
     * @param newValue Sets new value of the item not lower than 0
     *                 this.value = newValue
     * @pre Value of the item cannot be lower than zero
     * | !(newValue < 0)
     */
    public void setValue(float newValue) {
        assert !(newValue < 0);
        this.value = newValue;
    }

    /**
     * Gets value of an item
     * @return this.value
     */
    public float getValue() {
        return this.value;
    }

    /**
     * Set the holder of the item.
     * @param holder
     *        Monster who holds the item.
     */
    public void setHolder(Monster holder){
        this.holder = holder;
    }

    /**
     * Change the holder of an item to another monster
     * @param other
     *        Another monster who will hold the item.
     */
    public void changeHolder(Monster other){
        this.holder = other;
    }

    /**
     * Gets holder of the item.
     * @return  this.holder
     */
    public Monster getHolder(){
        return this.holder;
    }


    /**
     * Method setting weight of an item
     * @param   weight
     *          | Non-negative float
     * @return  weight
     * @throws  IllegalArgumentException
     *          Thrown if the weight of the item is about to be smaller than 0
     *          | weight < 0
     */
    public float setWeight(float weight) throws IllegalArgumentException{
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        if(weight < 0)
            throw new IllegalArgumentException();
        else
            return weight;
    }

    /**
     * Gets weight of an item
     * @return  this.weight
     */
    public float getWeight(){
        return this.weight;
    }

}