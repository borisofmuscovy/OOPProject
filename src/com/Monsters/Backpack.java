package com.Monsters;

import be.kuleuven.cs.som.annotate.*;
import java.util.*;

/**
 * Class of Backpack a special kind of InventoryItem.
 * In addition to value, holder and weight, each backpack has its capacity.
 * Other inventory items can be stored in backpacks (including backpacks)
 *
 * @version 1.0
 * @author Boris Shilov & Alicja Ochman
 */
public class Backpack extends InventoryItem implements Inventorised{

    /**
     * Variable indicating maximum weight of items stored in the backpack
     */
    private final float capacity;

    /**
     * Set of items contained in the backpack
     */
    private SortedSet<InventoryItem> backpackContent = new TreeSet<>(new WeightComparator());

    /**
     * Initialize new backpack with value, holder, weight and capacity
     * @param   value
     *          The value of this new backpack
     * @param   holder
     *          The holder of this new backpack
     * @param   weight
     *          The weight of this new backpack
     * @param   capacity
     *          The capacity of this new backpack
     */
    public Backpack(int value, Object holder, float weight, float capacity){
        super(value, holder, weight);
        this.ID = generateID();
        this.capacity = capacity;
        this.holder = holder;
        ((Inventorised) holder).add(this);

    }

    /**
     * Initialize new backpack with value, weight and capacity
     * @param   value
     *          The value of this new backpack
     * @param   weight
     *          The weight of this new backpack
     * @param   capacity
     *          The capacity of this new backpack
     */
    public Backpack(int value, float weight, float capacity){
        super (value, weight);
        this.ID = generateID();
        this.capacity = capacity;
    }

    /**
     * Returns the sorted set of content od the backpack
     * @return  backpackContent
     *          | Collections.unmodifiableSortedSet(this.backpackContent)
     */
    @Basic
    public SortedSet<InventoryItem> getContents(){
        return Collections.unmodifiableSortedSet(this.backpackContent);
    }

    /**
     * Returns capacity of this backpack
     * @return  capacity of this backpack
     *          | this.capacity
     */
    @Basic
    @Immutable
    public float getCapacity(){
        return this.capacity;
    }

    /**
     * Checks if an item with given weight can be added to the backpack
     * @param   item
     *          Item to be added to tbe backpack
     * @return  true if weight of backpack with new item does not exceed backpack's capacity, false otherwise
     *          |   result == (this.getContentsWeight() + item.getWeight()) > this.getCapacity())
     */
    @Override
    public boolean canContain(InventoryItem item){
        if (item == null){
            return false;
        } else if (((this.getContentsWeight() + item.getWeight()) > this.getCapacity())
                && (!(item instanceof Backpack))){
            return false;
        } else if ((item instanceof Backpack) &&
                ((this.getContentsWeight() + ((Backpack) item).getTotalWeight()) > this.getCapacity())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * For items contained in the backpack, sets backpack holder as items holder
     * @post    If item is contained in a backpack, it has the same holder as the backpack
     *          | for(InventoryItem item: this.backpackContent)
     *              (new item).getHolder() == this.getHolder()
     */
    private void setHolderRecursively(){
        for(InventoryItem item : this.backpackContent) {
            if (item instanceof Backpack) {
                item.setHolder(this);
                ((Backpack) item).setHolderRecursively();
            } else {
                item.setHolder(this);
            }
        }
    }

    /**
     * Adds multiple items to a backpack
     * @param   items
     *          Items to be added to a backpack
     * @post    The item is placed in the backpack and new holder of the item is set
     *          | this.backpackContent.contains(item) == true
     *          | item.getHolder() ==  this
     * @throws  IllegalArgumentException
     *          Throws an exception of the item to be added is to heavy or the item is already in the backpack
     *          or the item already has different holder
     *          | (!canContain(item)) || this.backpackContent.contains(item)) || item.getHolder()!=null
     */
    @Override
    public void add(InventoryItem... items) throws IllegalArgumentException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if (!canContain(item)){
                    throw new IllegalArgumentException("You cannot add this item. It's too heavy!");
                } else if (this.backpackContent.contains(item)) {
                    throw new IllegalArgumentException("This item is already in the backpack!");
                } else if (item.getHolder()!=null) {
                    throw new IllegalArgumentException("Item already being held by someone else.");
                } else {
                    this.backpackContent.add(item);
                    this.setHolderRecursively();
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes multiple items from this backpack
     * @param   items
     *          Items to be removed
     * @throws  IllegalArgumentException
     *          Throws an exception if item to be removed is not contained in the backpack
     *          | !this.backpackContent.contains(items)
     * @post    The item is removed from the backpack
     *          | this.backpackContent.contains(items) == false
     */
    @Override
    public void remove(InventoryItem... items) throws IllegalArgumentException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if (!this.backpackContent.contains(item)){
                    throw new IllegalArgumentException("There's no such item in the backpack");
                } else if (item instanceof Weapon) {
                    throw new IllegalArgumentException("Cannot drop weapons.");
                } else {
                    item.setHolder(null);
                    this.backpackContent.remove(item);
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Transfers multiple items from this backpack to another
     * @param   other
     *          Backpack to which items are transferred
     * @param   items
     *          Items to be transferred to other backpack
     * @throws  IllegalArgumentException
     *          Throws illegal argument exception if item is too heavy to be transferred;
     *          Capacity of other backpack would be exceeded
     *          | ! other.canContain(item)
     * @throws  IllegalStateException
     *          Throws an exception if the item to be transferred is not in the inventory of this backpack
     * @post    Items are removed from this backpack and put in other backpack
     *          | this.backpackContent.contains(item) == false
     *          |  other.backpackContent.contains(items) == true
     */
    @Override
    public void transfer(Inventorised other, InventoryItem... items) throws IllegalArgumentException, IllegalStateException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if (!this.backpackContent.contains(item)) {
                    throw new IllegalStateException("Cannot transfer items not in backpack.");
                } else if(!other.canContain(item)) {
                    throw new IllegalArgumentException("You cannot add this item. It's too heavy!");
                } else {
                    this.backpackContent.remove(item);
                    item.setHolder(null);
                    other.add(item);
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns weight of items contained in the backpack
     * @return  total weight of items from the backpack
     *          | totalContainedWeight(0)
     */
    public float getContentsWeight(){
        return totalContainedWeight(0);
    }

    /**
     * Returns total weight of the backpack
     * @return  weight of the backpack and of the items contained in it
     *          | totalContainedWeight(this.getWeight())
     */
    @Override
    public float getTotalWeight(){
        return totalContainedWeight(this.getWeight());
    }

    /**
     *  Returns total weight of items contained in the backpack
     * @param   totalWeight
     *          total weight of backpack
     * @return  totalWeight of backpack and items contained in it
     *          | totalWeight
     */
    private float totalContainedWeight(float totalWeight){
        for(InventoryItem item : this.backpackContent) {
            if (item instanceof Backpack) {
                totalWeight += item.getWeight();
                totalWeight = ((Backpack) item).totalContainedWeight(totalWeight);
            } else if (item instanceof Purse) {
                totalWeight += ((Purse) item).getTotalWeight();
            } else {
                totalWeight += item.getWeight();
            }
        }
        return totalWeight;
    }

    /**
     * Returns total value of the backpack and of the items contained in it
     * @return  value of the backpack and of the items contained in it
     *          | backpackValue
     */
    @Override
    public float getTotalValue(){
        return totalContainedValue(this.getValue());
    }

    /**
     * Returns value of items contained in the backpack
     * @param   totalValue
     *          Value of a backpack
     * @return  value of backpack and items contained in it
     *          | totalValue
     */
    private float totalContainedValue(float totalValue){
        for(InventoryItem item : this.backpackContent) {
            if (item instanceof Backpack) {
                totalValue += item.getValue();
                totalValue = ((Backpack) item).totalContainedValue(totalValue);
            } else {
                totalValue += item.getValue();
            }
        }
        return totalValue;
    }

    /**
     * Returns the heaviest item from the backpack
     * @return  The heaviest item from the backpack
     *          | this.backpackContent.last()
     * @throws  IllegalStateException
     *          Throws an exception if backpack is empty
     *          | this.backpackContent.isEmpty()
     */
    public InventoryItem getHeaviestItem() throws IllegalStateException{

        try {
            if (this.backpackContent.isEmpty()) {
                throw new IllegalStateException("Backpack is empty so there is no heaviest item.");
            } else {
                return this.backpackContent.last();
            }
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Returns the lightest item from the backpack
     * @return  The lightest item from the backpack
     *          | this.backpackContent.first()
     * @throws  IllegalStateException
     *          Throws an exception if backpack is empty
     *          | this.backpackContent.isEmpty()
     */
    public InventoryItem getLightestItem() throws IllegalStateException{
        try {
            if(this.backpackContent.isEmpty()) {
                throw new IllegalStateException("Backpack is empty so there is no lightest item.");
            } else {
                return this.backpackContent.first();
            }
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
