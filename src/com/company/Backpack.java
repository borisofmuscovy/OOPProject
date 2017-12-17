package com.company;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class Backpack extends InventoryItem{
    final long ID;
    HashMap<Long, String> backpackContent = new HashMap<Long, String>();

    public Backpack(int value, Monster holder, float weight){
        super(value, holder, weight);
        ID = setBackpackID();

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

    public void addBackpackContent(InventoryItem item) throws IllegalArgumentException{
        if(backpackContent.containsValue(item.getID()))
            throw new IllegalArgumentException("This item is already in the backpack!");
        if(item instanceof Weapon)
            this.backpackContent.put(item.getID(), "Weapon");
        if(item instanceof Purse)
            this.backpackContent.put(item.getID(), "Purse");
        if(item instanceof Backpack)
            this.backpackContent.put(item.getID(), "Backpack");
    }

    public void removeBackpackContent(InventoryItem item) throws IllegalArgumentException{
        if(!(backpackContent.containsKey(item.getID())))
            throw new IllegalArgumentException("There no such item in the backpack");
        else
            backpackContent.remove(item.getID());

    }

    public Set getBackpackContent(){
            return backpackContent.entrySet();
    }


}
