package com.Monsters;

import be.kuleuven.cs.som.annotate.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract class of Inventory Items which can be held by Monsters
 * Superclass of weapons, purses and backpacks
 * @invar   Value is a valid value for an item
 *          | isValidValue(value)
 * @invar   Weight is a valid weight for an item
 *          | isValidWeight(weight)
 *
 *
 * @version 1.0
 * @author Boris Shilov & Alicja Ochman
 */
public abstract class InventoryItem {

    /**
     * Variable indicating ID of the item
     */
    protected long ID;

    /**
     * Variable indicating value of the item
     */
    protected int value;

    /**
     * Variable indicating the weight of the item
     */
    protected float weight;

    /**
     * Variable indicating the holder of the item
     */
    protected Object holder;

    /**
     * List of all IDs that were already assigned to the items
     */
    static List<Long> existingIDs = new ArrayList<Long>();


    /**
     * Initialized new inventory item: weapon, purse or backpack with value, holder and weight
     *
     * @pre     Value of the item is a valid value
     *          | isValidValue(value)
     * @param   value
     *          Parameter describing value of the purse no smaller than zero
     * @param   weight
     *          Weight of the inventory item
     * @param   holder
     *          Monster that carries the inventory
     * @throws  IllegalArgumentException
     *          Throws an exception if weight of the item is not a valid weight
     *          |  if(!(isValidWeight(weight))
     *          |       then throw new IllegalArgumentException
     * @throws  IllegalArgumentException
     *          Throws an exception if holder is different than Monster or Backpack
     *          | !(holder instanceof Monster) || !(holder instanceof Backpack)
     */
    @Model
    @Raw
    protected InventoryItem(int value, Object holder, float weight) throws IllegalArgumentException {
        assert (isValidValue(value));
        if (!isValidWeight(weight))
            throw new IllegalArgumentException();
        this.weight = weight;
        this.value = value;
        if(holder instanceof Monster || holder instanceof Backpack)
            this.holder = holder;
        else
            throw new IllegalArgumentException("Holder of the item must be Monster or Backpack");
    }


    /**
     * Initialized new inventory item: weapon, purse or backpack with value and weight
     *
     * @pre     Value of the item has to be a valid value
     *          | isValidValue(value)
     * @param   value
     *          Parameter describing value of the purse no smaller than zero
     * @param   weight
     *          Weight of the inventory item
     * @throws  IllegalArgumentException
     *          Throws an exception if weight of the item is not a valid weight
     *          |  if(!(isValidWeight(weight))
     *          |       then throw new IllegalArgumentException
     */
    @Model
    @Raw
    protected InventoryItem(int value, float weight) throws IllegalArgumentException {
        assert (isValidValue(value));
        if (!isValidWeight(weight))
            throw new IllegalArgumentException();
        this.weight = weight;
        this.value = value;
    }

    /**
     * Returns the ID of inventory item
     */
    @Basic
    public long getID(){
        return this.ID;
    };

    /**
     * Checks validity of the ID
     * @param   ID
     *          ID to be checked
     * @return  True if the ID is a unique ID, false otherwise
     *          | result == existingIDs.contains(ID)
     */
    private static boolean isValidID(long ID) {
        if (existingIDs.contains(ID)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method setting odd unique ID of an item
     * @pre     ID has to be an odd number
     *          | ID % 2 != 0
     * @pre     ID has to be a number bigger than 0
     *          | ID > 0
     * @return  ID
     *          ID of an item
     */
    protected long generateID(){
        while(true) {
            if ((ID % 2 != 0) && (ID > 0) && (isValidID(ID))){
                existingIDs.add(ID);
                return ID;
            } else {
                Random newID = new Random();
                ID = newID.nextLong();
            }
        }
    }

    /**
     * Checks validity of value
     * @param   value
     *          Value to be checked
     * @return  True if value is not negative
     *          | result == (value >= 0)
     */
    private boolean isValidValue(int value){
        return value >= 0;
    }

    /**
     * Sets new value of the item
     * @param   newValue
     *          Value to be set as new Value
     * @pre     Value of the item cannot be lower than zero
     *          | !(newValue < 0)
     * @post    Value of this item corresponds to newValue
     *          | new.getValue() == newValue
     */
    @Raw
    protected void setValue(int newValue) {
        assert (isValidValue(newValue));
        this.value = newValue;
    }

    /**
     * Returns value of this item
     * @return  value of this item
     *          | this.value
     */
    @Basic
    @Raw
    public int getValue() {
        return this.value;
    }

    /**
     * Set the holder of the item to new holder
     * @param   holder
     *          Holder to be set
     * @pre     New holder of the item has to be backpack, monster or can be null if the object is dropped
     *          | (holder instanceof Monster) || (holder instanceof Backpack) || (holder == null)
     * @post    Holder of the item is set to holder
     *          | new.getHolder() == holder
     */
    public void setHolder(Object holder)throws IllegalArgumentException{
        if(holder instanceof Monster || holder instanceof Backpack || holder == null)
            this.holder = holder;
        else
            throw new IllegalArgumentException("Holder of an item has to be Monster or Backpack");
    }

    /**
     * Returns holder of the item.
     * @return  holder of this item
     *          | this.holder
     */
    @Basic
    public Object getHolder(){
            return this.holder;
    }

    /**
     * Returns indirect holder of this item
     * E.g. if the item is contained in a backpack, returns holder of this backpack
     * @return  Holder of the item
     *          | this.holder
     * @throws  IllegalAccessException
     *          If the holder of the item is neither Monster nor Backpack
     *          | if (!(this.holder instanceof Monster) || !( this.holder instanceof Backpack)
     */
    @Basic
    public Object getIndirectHolder() throws IllegalAccessException{
            if (this.holder instanceof Monster) {
                return this.getHolder();
            } else if (this.holder instanceof Backpack) {
                return ((Backpack) this.holder).getIndirectHolder();
            } else {
                throw new IllegalAccessException("Found item with holder type not monster or backpack.");
            }
    }

    /**
     * Method setting weight of an item
     * @param   weight
     *          | Non-negative float
     * @throws  IllegalArgumentException
     *          Thrown if the weight of the item is about to be invalid weight
     *          | !isValidWeight(weight)
     * @post    Weight of the item is set to weight
     *          |new.getWeight() == weight
     */
    @Raw
    public float setWeight(float weight) throws IllegalArgumentException{
        if(!isValidWeight(weight))
            throw new IllegalArgumentException();
        else
            return weight;
    }

    /**
     * Returns weight of an item
     * @return  weight of an item
     *          | this.weight
     */
    @Basic
    @Raw
    public float getWeight(){
        return this.weight;
    }

    /**
     * Checks validity of weight
     * @param   weight
     *          Weight to be checked
     * @return  True if weight is not negative, false otherwise
     *          | result == (weight >= 0)
     */
    public boolean isValidWeight(float weight){
        return weight >= 0;
    }

}