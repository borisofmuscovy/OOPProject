package com.company;

import java.util.Random;

public class Purse extends InventoryItem {
    float content;
    final long ID;
    final float capacity;
    float totalValue;
    private boolean Torn;


    /**
     * Constructor of purse object
     * Value of intialized content cannot be bigger than purses capacity
     * @param value
     *        Sets value of the purse
     * @param holder
     *        Monster that carries the purse
     * @param content
     *        Sets content of the purse at given time
     * @param capacity
     *        Describes maximum capacity of the purse
     */
    public Purse(int value, float weight, float content, float capacity, Monster holder){
        super(value, holder, weight);
        this.ID = setPurseID();
        assert(content <= capacity);
        this.content = content;
        this.capacity = capacity;
        this.Torn = false;
    }

    public Purse(int value, float weight, float content, float capacity) {
        super(value, weight);
        this.ID = setPurseID();
        assert(content <= capacity);
        this.content = content;
        this.capacity = capacity;
        this.Torn = false;
    }

    /**
     * Fibonacci series generator
     * @param   number
     *          Number indicating how many fibonacci numbers are going to be generated
     * @return  long[] fibonacciSeries
     *          Array containing fibonacci numbers of type long
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
     *  Method returning random long number from fibonacci series
     * @return  purseID
     *          Random long from the fibonacciSeries array
     */
    public long setPurseID(){
        int n = 25;
        int[] fibonacciSeries = fibonacci(n);
        Random r = new Random();
        long purseID = (long) fibonacciSeries[r.nextInt(fibonacciSeries.length-1)];
        return purseID;
    }

    public long getID(){
        return this.ID;
    }
    /**
     * Sets new value of the content of a purse
     * @param newContent
     *        Describes new content of a purse
     */
    public void setContent(float newContent){
        if(this.Torn == true)
            setContent(0);
        else
            this.content = newContent;
    }

    /**
     * Adds content to the purse
     * If content of the purse exceeds its capacity, the purse is torn
     * @param ducats
     *        Number of ducats to be added to the purse
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
     * If the new content of one of the pursee exceeds its capacity, the purse is torn
     * @param other
     *        Other purse
     * @param ducats
     *        Number of ducats to be transferred
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
     */
    public void tearThePurse(){
        this.value = 0;
        this.content = 0;
        this.Torn = true;
    }

    /**
     *  Method indicating that purse is torn.
     */
    public boolean isTorn(){
        return this.Torn;
    }


}
