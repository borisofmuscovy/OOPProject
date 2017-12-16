package com.company;

import java.util.Random;

public class Purse extends InventoryItem {
    float content;
    long ID;
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
    public Purse(float value, float weight, float content, float capacity, Monster holder){
        super(value, weight, holder);
        this.ID = setPurseID();
        assert(content <= capacity);
        this.content = content;
        this.capacity = capacity;
        this.Torn = false;
    }

    public Purse(float value, float weight, float content, float capacity){
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

    /**
     * Sets new value of the content of a purse
     * @param newContent
     *        Describes new content of a purse
     */
    public void setContent(float newContent){
        this.content = newContent;
    }

    /**
     * Adds content to the purse
     * If content of the purse exceeds its capacity, the purse is torn
     * @param dukats
     *        Number of dukats to be added to the purse
     */
    public void addContent(float dukats){
        setContent(this.content + dukats);
        if(this.content > this.capacity)
            tearThePurse();
    }

    /**
     * Removes dukats from the purse
     * @param dukats
     *        Number of dukats to be removed from the purse
     */
    public void removeContent(float dukats){
        setContent(this.content - dukats);
    }

    /**
     * Transfers money from one purse to the other
     * If the new content of one of the pursee exceeds its capacity, the purse is torn
     * @param other
     *        Other purse
     * @param dukats
     *        Number of dukats to be transferred
     */
    public void transferContent(Purse other, float dukats){
        setContent(this.content - dukats);
        setContent(other.content + dukats);
        if(this.content > this.capacity)
            this.tearThePurse();
        if(other.content > other.capacity)
            other.tearThePurse();
    }

    /**
     * Calculates total value of the purse
     */
    public void totalValue(){
        this.totalValue = this.value + this.content;
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
