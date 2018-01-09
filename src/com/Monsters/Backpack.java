package com.Monsters;

import be.kuleuven.cs.som.annotate.Basic;

import java.util.*;

/**
 * Class implementing comparison of InventoryItem weights
 */
class WeightComparator implements Comparator<InventoryItem> {
    /**
     * Compares weight of inventory items
     * @param   item1
     *          Item to be compared
     * @param   item2
     *          Item to be compared
     * @return  Returns which item is heavier or if they have same weight, which have higher ID value
     */
    @Override
    public int compare(InventoryItem item1, InventoryItem item2) {
        //if items being compared have the same weight, the one with the higher ID will be considered bigger
        if (item1.getWeight() == item2.getWeight()) {
            return (Long.compare(item1.getID(), item2.getID()));
        } else if (item1.getWeight() > item2.getWeight()) {
            return 1;
        } else {
            return -1;
        }
    }
}

/**
 * Class of Backpack a special kind of InventoryItem.
 * In addition to value, holder and weight, each backpack has its capacity.
 * Other inventory items can be stored in backpacks.
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
    public Backpack(int value, Monster holder, float weight, float capacity){
        super(value, holder, weight);
        this.ID = generateID();
        this.capacity = capacity;
        holder.add(this);
        this.holder = holder;
    }

    /**
     * Initialize new backpack with value, wieght and capacity
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
        this.holder = null;
    }

    /**
     * Returns the sorted set of content od the backpack
     * @return  backpackContent
     *          | Collections.unmodifiableSortedSet(this.backpackContent)
     */
    @Basic
    public SortedSet<InventoryItem> getBackpackContents(){
        return Collections.unmodifiableSortedSet(this.backpackContent);
    }

    /**
     * Returns ID of this backpack
     * @return  ID
     *          | this.ID
     */
    @Override
    public long getID(){
        return this.ID;
    }

    /**
     * Returns capacity of this backpack
     * @return  capacity of this backpack
     *          | this.capacity
     */
    @Basic
    public float getCapacity(){
        return this.capacity;
    }

    /**
     * Checks if an item with given weight can be added to the backpack
     * @param   item
     *          Item to be added to tbe backpack
     * @return  true if weight of backpack with new item does not exceed backpack's capacity, false otherwise
     *          |   this.getContentsWeight() + item.getWeight()) > this.getCapacity()
     */
    public boolean canContain(InventoryItem item){
        if (((this.getContentsWeight() + item.getWeight()) > this.getCapacity())
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
     *              item.getHolder == this.getHolder
     */
    public void setHolderRecursively(){
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
     *          | this.backpackContent.add(item)
     *          | this.setHolderRecursively()
     * @throws  IllegalArgumentException
     *          Throws an exception of the item to be added is to heavy or the item is already in the backpack
     *          or the item already has different holder
     *          | (!canContain(item)) || this.backpackContent.contains(item)) || item.getHolder()!=null
     */
    public void add(InventoryItem... items) throws IllegalArgumentException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if (!canContain(item)){
                    throw new IllegalArgumentException("You cannot add this item. It's too heavy!");
                } else if (this.backpackContent.contains(item)) {
                    throw new IllegalArgumentException("This item is already in the backpack!");
                } else if ((item.getHolder()!=null) && !(item instanceof Weapon)) {
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
     *          | !this.backpackContent.contains(item)
     * @post    The item is removed from the backpack
     *          | this.backpackContent.remove(item)
     */
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
     *          Throws illegal argument exception if item is too heavy to be transferres;
     *          Capacity of other backpack would be exceeded
     *          | ! other.canContain(item)
     * @post    Items are removed from this backpack and put in other backpack
     *          | this.remove(item)
     *          |  other.add(item)
     */
    public void transfer(Backpack other, InventoryItem... items) throws IllegalArgumentException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if(!other.canContain(item)) {
                    throw new IllegalArgumentException("You cannot add this item. It's too heavy!");
                } else {
                    this.backpackContent.remove(item);
                    item.setHolder(other);
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
     *          | contentsWeight
     */
    public float getContentsWeight(){
        return totalContainedWeight(0);
    }

    /**
     * Returns total weight of the backpack
     * @return  weight of the backpack and of the items contained in it
     *          | totalBackpackWeight
     */
    public float getTotalWeight(){
        return totalContainedWeight(this.getWeight());
    }

    private float totalContainedWeight(float totalWeight){
        for(InventoryItem item : this.backpackContent) {
            if (item instanceof Backpack) {
                totalWeight += item.getWeight();
                totalWeight = ((Backpack) item).totalContainedWeight(totalWeight);
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
    public float getTotalValue(){
        return totalContainedValue(this.getValue());
    }

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
