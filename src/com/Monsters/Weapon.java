package com.Monsters;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Weapon extends InventoryItem {
    int damage;
    final int MIN_DAMAGE = 1;
    int MAX_DAMAGE = 20;

    /**
     * Initializes weapon with its weight, value and damage
     * @pre   Damage cause by the weapon has to be in the range from 1 to MAXIMUM_DAMAGE
     *        | damage >= 1 && damage <= MAX_DAMAGE
     * @param weight
     *        Describes weight of the weapon
     * @param value
     *        Describes value of the weapon
     * @param damage
     *        Describes damage that can be caused by the weapon
     */
    public Weapon(float weight, int value, int damage){
        super(value, weight);
        this.setValue(value);
        assert(damage >= 1 & damage <= MAX_DAMAGE);
        this.damage = damage;
        this.weight = setWeight(weight);
        this.ID = generateID();
        this.holder = null;
    }

    /**
     * Returns ID of this weapon
     * @return  ID of this weapon
     *          | this.ID
     */
    @Override
    public long getID(){
        return this.ID;
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
    @Basic
    public void setDamage(int newDamage){
        assert(newDamage >= 1 & newDamage <= MAX_DAMAGE);
        this.damage = newDamage;
    }

    /**
     * Returns damage of the weapon
     * @return  damage of this weapon
     *          |this.damage
     */
    @Basic
    public int getDamage(){
        return this.damage;
    }

    /**
     * Repairs the weapon and increases its value
     * @pre     Value to be added to the value of this weapon has to be bigger than 0
     *          | value > 0
     * @param   value
     *          Value to be added to the value of this weapon
     */
    public void repair(int value) {
        assert(value > 0);
        setValue(this.value + value);
    }

    /**
     * Weapon is eroding and thus its value is decreasing by given value
     * @pre     New value of a weapon has to be bigger than 0
     *          | this.value - value > 0
     * @param   value
     *          Value to be subtracted from current value of this weapon
     */
    public void erode(int value) {
        assert(this.value - value > 0);
        setValue(this.value - value);
    }

}
