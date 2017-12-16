package com.company;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Weapon extends InventoryItem {
    int damage;
    int MIN_DAMAGE = 1;
    int MAX_DAMAGE = 20;
    static List<Long> existingIDs = new ArrayList<Long>();
    final long ID;

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
    public Weapon(float weight, float value, int damage, Monster holder){
        super(value, weight, holder);
        this.setValue(value);
        this.damage = generateDamage();
        this.weight = setWeight(weight);
        this.ID = generateWeaponID();
    }

    public Weapon(float weight, float value, int damage){
        super(value, weight);
        this.setValue(value);
        this.damage = generateDamage();
        this.weight = setWeight(weight);
        this.ID = generateWeaponID();
    }




    /**
     * Method setting unique, odd ID of a weapon of long type
     * @return  weaponID
     */
    public long generateWeaponID(){
        while(true){
            long weaponID = ThreadLocalRandom.current().nextLong(10000);
            if( (weaponID % 2 != 0) && (!existingIDs.contains(weaponID)) ){
                existingIDs.add(weaponID);
                return weaponID;
            }
        }
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
     * Sets damage caused by weapon to new damage
     * @pre   newDamage is in range(1, MAX_DAMAGE)
     *        | newDamage >= 1 && newDamage <= MAX_DAMAGE
     */
    public int generateDamage(){
        Random randno = new Random();
        int valuedDamage;
        //pegs damage to the value of a weapon
        if (this.getValue() < 10){
            valuedDamage = randno.nextInt(5 - MIN_DAMAGE + 1) + MIN_DAMAGE;
        } else if (this.getValue() > 10 && this.getValue() < 100){
            valuedDamage = randno.nextInt(15 - MIN_DAMAGE + 1) + MIN_DAMAGE;
        } else {
            valuedDamage = randno.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
        }
        assert(valuedDamage >= 1 && valuedDamage <= MAX_DAMAGE);
        return valuedDamage;
    }

    /**
     * Returns damage of the weapon
     * @return  this.damage
     */
    public int getDamage(){
        return this.damage;
    }

    public void repair(int value) {
        assert(value > 0);
        setValue(this.value + value);
    }

    public void erode(int value) {
        assert(this.value - value > 0);
        setValue(this.value - value);
    }

}
