package com.Monsters;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Random;

public class Purse extends InventoryItem {
    float content;
    final float capacity;
    float totalValue;
    private boolean Torn;


    /**
     * Initialized a purse with weight, value, content, capacity and holder
     * @pre     Content cannot be bigger than capacity
     *          | content >= capacityy
     * @param   value
     *          Sets value of the purse
     * @param   holder
     *          Monster that carries the purse
     * @param   content
     *          Sets content of the purse at given time
     * @param   capacity
     *          Describes maximum capacity of the purse
     */
    public Purse(int value, float weight, float content, float capacity, Monster holder){
        super(value, holder, weight);
        this.ID = generateID();
        assert(content <= capacity);
        this.content = content;
        this.capacity = capacity;
        this.Torn = false;
    }

    /**
     * Initialized a purse with weight, value, content and capacity
     * @pre     Content cannot be bigger than capacity
     *          | content >= capacity
     * @param   value
     *          Sets value of the purse
     * @param   content
     *          Sets content of the purse at given time
     * @param   capacity
     *          Describes maximum capacity of the purse
     */
    public Purse(int value, float weight, float content, float capacity) {
        super(value, weight);
        this.ID = generateID();
        assert(content <= capacity);
        this.content = content;
        this.capacity = capacity;
        this.Torn = false;
    }

    /**
     * Fibonacci series generator
     * @param   number
     *          Number indicating how many fibonacci numbers are going to be generated
     * @return  Array containing fibonacci numbers of type long
     *          |long[] fibonacciSeries
     */
    private static int[] fibonacci(int number) {
        int[] fibonacciSeries = new int[number];
        fibonacciSeries[0] = 0;
        fibonacciSeries[1] = 1;
        for(int i = 2; i < number; i++){
            fibonacciSeries[i] = fibonacciSeries[i - 1] + fibonacciSeries [i - 2];
        }
        return fibonacciSeries;
    }

    /**
     * Method returning random long number from fibonacci series created using fibonacci()
     * @return  Random long from the fibonacciSeries array
     *          | purseID
     */
    @Raw
    @Immutable
    protected long generateID(){
        long purseID = 0;
        int n = 1000;
        int[] fibonacciSeries = fibonacci(n);
        Random r = new Random();
        while (purseID <= 0) {
            purseID = (long) fibonacciSeries[r.nextInt(fibonacciSeries.length-1)];
        }
        existingIDs.add(purseID);
        return purseID;
    }

    /**
     * Returns ID of this purse
     * @return  ID of this purse
     *          | this.ID
     */
    @Raw
    @Override
    public long getID(){
        return this.ID;
    }

    /**
     * Sets new value of the content of a purse
     * @param newContent
     *        Describes new content of a purse
     */
    @Basic
    public void setContent(float newContent){
        if(this.Torn)
            setContent(0);
        else
            this.content = newContent;
    }

    /**
     * Returns content of this purse
     * @return  Content of this purse
     *          | this.content
     */
    @Basic
    public float getContent(){
        return this.content;
    }


    /**
     * Adds content to the purse
     * @pre     To add ducats to this purse, purse cannot be torn
     *          this.Torn != true
     * @param   ducats
     *          Number of ducats to be added to the purse
     * @post    If the content of the purse after addition of the ducats exceeds its capacity
     *              this purse is torn
     *          |If (this.content > this.capacity)
     *              then tearThePurse()
     */
    public void addContent(float ducats){
        if(this.Torn == true)
            setContent(0);
        else
            setContent(this.content + ducats);
        if(this.content > this.capacity)
            tearThePurse();
    }

    /**
     * Removes ducats from the purse
     * @param ducats
     *        Number of ducats to be removed from the purse
     */
    public void removeContent(float ducats){
        if(this.Torn == true)
            setContent(0);
        else
            setContent(this.content - ducats);
    }

    /**
     * Transfers money from one purse to the other
     * @param   other
     *          Other purse
     * @param   ducats
     *          Number of ducats to be transferred
     * @post     If the new content of one of the purses exceeds its capacity, the purse is torn
     *           |  if(this.content > this.capacity)
     *           |      then this.tearThePurse();
     *           |  if(other.content > other.capacity)
     *           |      then other.tearThePurse();
     */

    public void transferContent(Purse other, float ducats){
        setContent(this.content - ducats);
        setContent(other.content + ducats);
        if(this.content > this.capacity)
            this.tearThePurse();
        if(other.content > other.capacity)
            other.tearThePurse();
    }

    /**
     * Calculates total value of the purse
     */
    public void totalValue(){
        this.totalValue = this.getValue() + this.content;
    }

    /**
     * Method to destroy the purse
     * @post    The purse is torn
     *          | this.Torn = true
     */
    public void tearThePurse(){
        this.value = 0;
        this.content = 0;
        this.Torn = true;
    }

    /**
     *  Method indicating that purse is torn.
     *  @return true if purse is torn, false otherwise
     *          | this.Torn
     */
    public boolean isTorn(){
        return this.Torn;
    }


}
