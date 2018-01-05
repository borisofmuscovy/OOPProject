package com.Monsters;

import java.util.*;

class WeightComparator implements Comparator<InventoryItem> {
    public int compare(InventoryItem item1, InventoryItem item2) {
        if (item1.getWeight() == item2.getWeight()) {
            return 0;
        }
        return Float.compare(item1.getWeight(), item2.getWeight());
    }
}

public class Backpack extends InventoryItem{
    private final long ID;
    private final float capacity;
    private float backpackValue;
    private SortedSet<InventoryItem> backpackContent = new TreeSet<>(new WeightComparator());

    public Backpack(int value, Monster holder, float weight, float capacity){
        super(value, holder, weight);
        this.ID = generateBackpackID();
        this.capacity = capacity;
        this.holder = holder;
        holder.equip(this);
    }

    public Backpack(int value, float weight, float capacity){
        super (value, weight);
        this.ID = generateBackpackID();
        this.capacity = capacity;
        this.holder = null;
    }


    public SortedSet<InventoryItem> getBackpackContents(){
        return Collections.unmodifiableSortedSet(this.backpackContent);
    }

    /**
     * Method setting odd ID of a backpack of long type
     * @return  backpackID
     */
    public long generateBackpackID(){
        long backpackID = 0;
        int i = 1;
        while(i == 1){
            Random ID = new Random();
            backpackID = ID.nextLong();
            if(backpackID % 2 != 0 && backpackID >= 0)
                i = 0;
            else
                i =1;
        }
        return backpackID;
    }

    public long getID(){
        return this.ID;
    }

    public float getCapacity(){
        return this.capacity;
    }

    public boolean canContain(InventoryItem item){
        if ((this.getContentsWeight() + item.getWeight()) > this.getCapacity()){
            return false;
        } else {
            return true;
        }
    }

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

    public void remove(InventoryItem... items) throws IllegalArgumentException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if (!this.backpackContent.contains(item)){
                    throw new IllegalArgumentException("There's no such item in the backpack");
                } else {
                    this.backpackContent.remove(item);
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void transfer(Backpack other, InventoryItem... items) throws IllegalArgumentException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if(other.canContain(item)) {
                    throw new IllegalArgumentException("You cannot add this item. It's too heavy!");
                } else {
                    this.remove(item);
                    other.add(item);
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public float getContentsWeight(){
        float contentsWeight = 0;
        for(InventoryItem item : this.backpackContent ){
            contentsWeight += item.getWeight();
        }
        return contentsWeight;
    }

    public float getTotalWeight(){
        float totalBackpackWeight = 0;
        for(InventoryItem item : this.backpackContent ){
            totalBackpackWeight += item.getWeight();
        }
        totalBackpackWeight += this.getWeight();
        return totalBackpackWeight;

    }

    public float getTotalValue(){
        for(InventoryItem item : this.backpackContent){
            backpackValue += item.getValue();
        }
        backpackValue += this.getValue();
        return backpackValue;
    }

    public InventoryItem getHeaviest() throws IllegalStateException{
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

    public InventoryItem getLightest() throws IllegalStateException{
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
