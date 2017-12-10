package com.company;

import java.util.List;
import java.util.Random;

/**
 * Abstract class of Inventory Items
 * Superclass of weapons ans purses
 */
public abstract class InventoryItem {

    public int ID;
    public int value;
    public float weight;
    List allIDs;
    {
        value = 0;
        weight = 0;
    }

    /**
     * Constructor of inventory item: purse or weapon
     * Initializes item ID depending on item type:
     *          Random odd long for weapons
     *          Random number from fibonacci series for purses
     * @param   weight
     *          Non-negative float value of weapon's weight
     *          | !(this.weight < 0)
     * @param   value
     *          Parameter describing value of the purse no smaller than zero
     *          | !(value < 0)
     * @throws  IllegalArgumentException
     *          If weight of the weapon is smaller than zero
     *          | weight < 0
     */
    public InventoryItem(float weight, int value) throws IllegalArgumentException {
        assert(!(value < 0));
        this.value = value;
        this.weight = setWeight(weight);
        setWeaponID(allIDs);
        setPurseID(30);
    }

    /**
     * Method setting unique, odd ID of a weapon of long type
     * @param   allIDs
     *          List of ID's that were ascribed to other weapons
     * @return  weaponID
     */
    public long setWeaponID(List allIDs){
        long weaponID = 0;
        int i = 1;
        while(i == 1){
        Random ID = new Random();
        weaponID = ID.nextLong();
            if((weaponID % 2 != 0) &&(!allIDs.contains(weaponID))){
                allIDs.add(weaponID);
                i = 0;
            }else
                i =1;
        }
        return weaponID;
    }

    /**
     * Fibonacci series generator
     * @param   number
     *          Number indicating how many fibonacci numbers are going to be generated
     * @return  long[] fibonacciSeries
     *          Array containing fibonacci numbers of type long
     */
    public static int[] fibonacci(int number) {
        int[] fibonacciSeries = new int[number];
        fibonacciSeries[0] = 0;
        fibonacciSeries[1] = 1;
        for(int i = 2; i < number; i++){
            fibonacciSeries[i] = fibonacciSeries[i - 1] + fibonacciSeries [i - 2];
        }
        return fibonacciSeries;
    }

    /**
     *  Method returning random long number from fibonacci series
     * @param   n
     *          Number of fibonacci number to generate
     * @return  purseID
     *          Random long from the fibonacciSeries array
     */
    public long setPurseID(int n){
        int[] fibonacciSeries = fibonacci(n);
        Random r = new Random();
        long purseID = (long) fibonacciSeries[r.nextInt(fibonacciSeries.length)];
        return purseID;
    }

    /**
     * Sets new value of the item
     * @pre     Value of the item cannot be lower than zero
     *          | !(newValue < 0)
     * @param   newValue
     *          Sets new value of the item not lower than 0
     *          this.value = newValue
     */
    public void setValue(int newValue){
        assert(!(newValue < 0));
        this.value = newValue;
    }

    /**
     * Gets value of an item
     * @return  this.value
     */
    public int getValue(){
        return this.value;
    }

    /**
     * Method setting weight of an item
     * @param   weight
     *          | Non-negative float
     * @return  weight
     *
     * @throws  IllegalArgumentException
     *          Thrown if the weight of the weapon is about to be smaller than 0
     *          | weight < 0
     */
    public float setWeight(float weight) throws IllegalArgumentException{
        if(weight <0)
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
