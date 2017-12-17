package com.company;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Backpack extends InventoryItem{
    private final long ID;
    private final float capacity;

    HashMap<InventoryItem, String> backpackContent = new HashMap<InventoryItem, String>();

    public Backpack(int value, Monster holder, float weight, float capacity){
        super(value, holder, weight);
        this.ID = setBackpackID();
        this.capacity = capacity;
    }

    /**
     * Method setting odd ID of a backpack of long type
     * @return  backpackID
     */
    public long setBackpackID(){
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

    public void addBackpackContent(InventoryItem item) throws IllegalArgumentException{
        if((this.getTotalWeight() + item.getWeight()) > this.getCapacity())
            throw new IllegalArgumentException("You cannot add this item. It's too heavy!");
        if(backpackContent.containsValue(item.getID()))
            throw new IllegalArgumentException("This item is already in the backpack!");
        if(item instanceof Weapon)
            this.backpackContent.put(item, "Weapon");
        if(item instanceof Purse)
            this.backpackContent.put(item, "Purse");
        if(item instanceof Backpack)
            this.backpackContent.put(item, "Backpack");
    }

    public void removeBackpackContent(InventoryItem item) throws IllegalArgumentException{
        if(!(backpackContent.containsKey(item)))
            throw new IllegalArgumentException("There's no such item in the backpack");
        else
            backpackContent.remove(item);

    }

    public Set getBackpackContent(){
            return backpackContent.entrySet();
    }

    float totalBackpackWeight;
    public float getTotalWeight(){
        for(InventoryItem key: backpackContent.keySet()){
            totalBackpackWeight += key.getWeight();
        }
        totalBackpackWeight += this.getWeight();
        return totalBackpackWeight;

    }
    int backpackValue;
    public int getTotalValue(){
        for(InventoryItem key: backpackContent.keySet()){
            backpackValue += key.getValue();
        }
        backpackValue += this.getValue();
        return backpackValue;
    }


}
