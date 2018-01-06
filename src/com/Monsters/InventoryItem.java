package com.Monsters;
import be.kuleuven.cs.som.annotate.Basic;

import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract class of Inventory Items
 * Superclass of weapons ans purses
 */
public abstract class InventoryItem {

    protected long ID;
    protected int value;
    protected float weight;
    protected Object holder;
    static List<Long> existingIDs = new ArrayList<Long>();


    /**
     * Initialized new inventory item: weapon, purse or backpack with value, holder and weight
     *
     * @pre     Value of the item cannot be smaller than 0
     *          | !(value < 0)
     * @param   value
     *          Parameter describing value of the purse no smaller than zero
     * @param   weight
     *          Weight of the inventory item
     * @param   holder
     *          Monster that carries the inventory
     * @throws  IllegalArgumentException
     *          Throws an exception if weight of the item is smaller than zero
     *          |  if weight < 0
     *          |       then throw new IllegalArgumentException
     */
    public InventoryItem(int value, Object holder, float weight) throws IllegalArgumentException {
        assert (!(value < 0));
        if (weight < 0)
            throw new IllegalArgumentException();
        this.weight = weight;
        this.value = value;
        this.holder = holder;
    }


    /**
     * Initialized new inventory item: weapon, purse or backpack with value and weight
     *
     * @pre     Value of the item cannot be smaller than 0
     *          | !(value < 0)
     * @param   value
     *          Parameter describing value of the purse no smaller than zero
     * @param   weight
     *          Weight of the inventory item
     * @throws  IllegalArgumentException
     *          Throws an exception if weight of the item is smaller than zero
     *          |  if weight < 0
     *          |       then throw new IllegalArgumentException
     */
    public InventoryItem(int value, float weight) throws IllegalArgumentException {
        assert (!(value < 0));
        if (weight < 0)
            throw new IllegalArgumentException();
        this.weight = weight;
        this.value = value;
        this.holder = null;
    }

    /**
     * Returns the ID of inventory item
     */
    @Basic
    public abstract long getID();

    protected boolean checkValidID(long ID) {
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
     */
    protected long generateID(){
        while(true) {
            if ((ID % 2 != 0) && (ID > 0) && (checkValidID(ID))){
                existingIDs.add(ID);
                return ID;
            } else {
                Random newID = new Random();
                ID = newID.nextLong();
            }
        }
    }

    /**
     * Sets new value of the item
     * @param   newValue
     *          Value to be set as new Value
     * @pre     Value of the item cannot be lower than zero
     *          | !(newValue < 0)
     * @post    Value of this item corresponds to newValue
     *          | this.value = newValue
     */
    @Basic
    public void setValue(int newValue) {
        assert !(newValue < 0);
        this.value = newValue;
    }

    /**
     * Gets value of this item
     * @return  value of this item
     *          | this.value
     */
    @Basic
    public int getValue() {
        return this.value;
    }

    /**
     * Set the holder of the item.
     * @param holder
     *        Monster who holds the item.
     */
    @Basic
    public void setHolder(Object holder){
        this.holder = holder;
    }

    /**
     * Gets holder of the item.
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
     */
    @Basic
    public Object getIndirectHolder(){
        try{
            if (this.holder instanceof Monster) {
                return this.getHolder();
            } else if (this.holder instanceof Backpack) {
                return ((Backpack) this.holder).getIndirectHolder();
            } else {
                throw new IllegalAccessException("Found item with holder type not monster or backpack.");
            }
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method setting weight of an item
     * @param   weight
     *          | Non-negative float
     * @throws  IllegalArgumentException
     *          Thrown if the weight of the item is about to be smaller than 0
     *          | weight < 0
     */
    @Basic
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
     * @return  weight of an item
     *          | this.weight
     */
    @Basic
    public float getWeight(){
        return this.weight;
    }

}