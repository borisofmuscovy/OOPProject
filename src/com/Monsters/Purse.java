package com.Monsters;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import java.util.Random;

/**
 * The class of Purse, special case of Inventory Item.
 * Apart from value, weight and holder, Purse also has content and capacity.
 */
public class Purse extends InventoryItem {

    /**
     * Variable indicating the content of the purse
     */
    private int content;

    /**
     * Variable indicating the maximum capacity of the purse
     */
    private final int capacity;

    /**
     * Variable indicating if the purse is destroyed
     */
    private boolean Torn;


    /**
     * Initialized a purse with weight, value, content, capacity and holder
     * @pre     Content cannot be bigger than capacity
     *          | content >= capacity
     * @param   value
     *          Sets value of the purse
     * @param   holder
     *          Monster that carries the purse
     * @param   content
     *          Sets content of the purse at given time
     * @param   capacity
     *          Describes maximum capacity of the purse
     */
    public Purse(int value, float weight, int content, int capacity, Monster holder){
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
    public Purse(int value, float weight, int content, int capacity) {
        super(value, weight);
        this.ID = generateID();
        assert(content <= capacity && !(content < 0));
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
    @Basic
    @Override
    @Immutable
    public long getID(){
        return this.ID;
    }


    @Basic @Override
    public float getWeight(){
        return this.weight + 50*this.getContent();
    }
    /**
     * Sets new value of the content of a purse
     * @param newContent
     *        Describes new content of a purse
     */
    private void setContent(int newContent) {
            if(this.Torn) {
                this.content = 0;
            } else {
                assert(newContent >= 0 );
                this.content = newContent;
            }
    }

    /**
     * Returns content of this purse
     * @return  Content of this purse
     *          | this.content
     */
    @Basic
    @Immutable
    public int getContent(){
        return this.content;
    }

    @Basic
    @Immutable
    public int getCapacity() {
        return this.capacity;
    }
    /**
     * Adds content to the purse
     * @pre     To add ducats to this purse, purse cannot be torn
     *          this.Torn != true
     * @param   ducats
     *          Number of ducats to be added to the purse
     * @effect  Ducats are added to the purse
     *          | setContent(this.getContent() + ducats)
     * @post    If the content of the purse after addition of the ducats exceeds its capacity
     *              this purse is torn
     *          |If (this.content > this.capacity)
     *          |   then this.isTorn == true
     *
     */
    public void add(int ducats) throws  IllegalStateException{
        if(this.Torn)
            throw new IllegalStateException("You cannot add ducats to torn purse");
        else
            setContent(this.getContent() + ducats);
        if(this.getContent() > this.getCapacity())
            tearThePurse();
    }

    /**
     * Removes ducats from the purse
     * @param   ducats
     *          Number of ducats to be removed from the purse
     * @pre     Purse cannot be torn to remove content from it
     *          | this.Torn == false
     * @effect  Content of the purse is reduced by ducats
     *          | setContent(this.getContent() - ducats)
     */
    public void remove(int ducats) throws IllegalStateException{
        if(this.Torn)
            throw new IllegalStateException("You cannot remove ducats from torn purse");
        else
            setContent(this.getContent() - ducats);
    }

    /**
     * Transfers money from one purse to the other
     * @param   other
     *          Other purse
     * @param   ducats
     *          Number of ducats to be transferred
     * @post     If the new content of one of the purses exceeds its capacity, the purse is torn
     *           |  if(this.content > this.capacity)
     *           |      then this.isTorn == true;
     *           |  if(other.content > other.capacity)
     *           |      then other.isTorn == true;
     * @effect  Content of this purse is reduced by ducats
     *          | setContent(this.getContent - ducats)
     * @effect  Content of this purse is increased by ducats
     *          | setContent(other.getContent +  ducats)
     */

    public void transfer(Purse other, int ducats){
        this.setContent(this.getContent() - ducats);
        other.setContent(other.getContent() + ducats);
        if(this.getContent() > this.getCapacity())
            this.tearThePurse();
        if(other.getContent() > other.getCapacity())
            other.tearThePurse();
    }

    /**
     * Returns total value of the purse
     */
    public int getTotalValue(){
        return (this.getValue() + this.getContent());
    }

    /**
     * Method to destroy the purse
     * @post    The purse is torn
     *          | this.Torn = true
     * @effect  Content and value of torn purse is set to 0
     *          | this.setValue(0)
     *          | this.setContent(0)
     */
    private void tearThePurse(){
        this.setValue(0);
        this.setContent(0);
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

    public float getTotalWeight(){
        return this.getWeight() + (float)0.5 * this.getContent();
    }

}
