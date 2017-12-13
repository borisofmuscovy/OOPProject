package com.company;

import java.util.Random;

public class Backpack extends InventoryItem{
    final long ID;

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
            if(backpackID % 2 != 0)
                i = 0;
            else
                i =1;
        }
        return backpackID;
    }
}
