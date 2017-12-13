package com.company;

import java.util.List;
import java.util.Random;

public class Weapon extends InventoryItem {
    int damage;
    int MAX_DAMAGE = 20;
    List allIDs;

    /**
     * Constructor of weapon object
     * @param weight
     *        Describes weight of the weapon
     * @param value
     *        Describes value of the weapon
     * @param holder
     *        Monster that holds the weapon
     */
    public Weapon(float weight, int value, Monster holder){
        super(value, holder);
        this.damage = setDamage(MAX_DAMAGE);
        this.weight = setWeight(weight);
        setWeaponID(allIDs);
    }
    /**
     * Method setting unique, odd ID of a weapon of long type
     * @param   allIDs
     *          List of ID's that were ascribed to other weapons
     * @return  weaponID
     */
    public long setWeaponID(List allIDs){
        long weaponID = 0;
        int i = 1;
        while(i == 1){
            Random ID = new Random();
            weaponID = ID.nextLong();
            if((weaponID % 2 != 0) &&(!allIDs.contains(weaponID))){
                allIDs.add(weaponID);
                i = 0;
            }else
                i =1;
        }
        return weaponID;
    }

    /**
     * Sets damage to a random number from 1 to MAX_DAMAGE
     * @param MAX_DAMAGE
     *        Maximum damage
     * @return damage
     */
    public int setDamage(int MAX_DAMAGE){
        Random r = new Random();
        damage = r.nextInt(MAX_DAMAGE + 1) + 1;
        return damage;
    }

    /**
     * Method setting weight of an item
     * @param   weight
     *          | Non-negative float
     * @return  weight
     * @throws  IllegalArgumentException
     *          Thrown if the weight of the weapon is about to be smaller than 0
     *          | weight < 0
     */
    public float setWeight(float weight) throws IllegalArgumentException{
        if(weight < 0)
            throw new IllegalArgumentException();
        else
            return weight;
    }

    /**
     * Gets weight of an item
     * @return  this.weight
     */
    public float getWeight(){
        return this.weight;
    }
}
