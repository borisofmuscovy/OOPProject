package com.company;

import java.util.List;
import java.util.Random;

public class Weapon extends InventoryItem {
    int damage;
    int MAX_DAMAGE = 20;
    List allIDs;

    /**
     * Constructor of weapon object
     * @pre   Damage cause by the weapon has to be in the range from 1 to MAXIMUM_DAMAGE
     *        damage >= 1 && damage <= MAX_DAMAGE
     * @param weight
     *        Describes weight of the weapon
     * @param value
     *        Describes value of the weapon
     * @param damage
     *        Describes damage cause by the weapon
     * @param holder
     *        Monster that holds the weapon
     */
    public Weapon(float weight, int value, int damage, Monster holder){
        super(value, holder, weight);
        assert(damage >= 1 && damage <= MAX_DAMAGE);
        this.damage = damage;
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
     * Sets new MAX_DAMAGE for weapons
     * @return MAX_DAMAGE
     */
    public int setMaxDamage(){
        Random r = new Random();
        MAX_DAMAGE = r.nextInt(20 + 1) + 1;
        return MAX_DAMAGE;
    }

    /**
     * Sets damage cause by weapon to new damage
     * @param newDamage
     * @pre   newDamage is in range(1, MAX_DAMAGE)
     *        | newDamage >= 1 && newDamage <= MAX_DAMAGE
     */
    public void setDamage(int newDamage){
        assert( newDamage >= 1 && newDamage <= MAX_DAMAGE);
        this.damage = newDamage;
    }

    /**
     * Returns damage od the weapon
     * @return  this.damage
     */
    public int getDamage(){
        return this.damage;
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
