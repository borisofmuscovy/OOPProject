package com.Monsters;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The class of Weapon, special case of an inventory item.
 * Apart from weight, value and holder, weapons also have damage.
 * Weapons can be held by a monsters and used during the battles.
 *
 * @invar   Damage has to have a valid value
 *          | damage >= 1 & damage <= MAX_DAMAGE
 */
public class Weapon extends InventoryItem {

    /**
     * Variable indicating damage that can be cause by a Weapon
     */
    int damage;

    /**
     * Minimum damage that can be cause by a weapon
     */
    final int MIN_DAMAGE = 1;

    /**
     * Maximum damage that can be cause by a weapon
     */
    int MAX_DAMAGE = 20;

    /**
     * Initializes weapon with its weight, value and damage
     * @pre     Damage cause by the weapon has to be in the range from 1 to MAXIMUM_DAMAGE
     *          | damage >= 1 && damage <= MAX_DAMAGE
     * @param   weight
     *          Describes weight of the weapon
     * @param   value
     *          Describes value of the weapon
     * @param   damage
     *          Describes damage that can be caused by the weapon
     * @effect  Weight fo this monster is set to given weight
     *          | new.getWeight() == weight
     */
    public Weapon(float weight, int value, int damage){
        super(value, weight);
        this.setValue(value);
        assert(damage >= MIN_DAMAGE & damage <= MAX_DAMAGE);
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
    @Immutable
    public long getID(){
        return this.ID;
    }

    /**
     * Sets new MAX_DAMAGE for weapons
     * @post    new maximum value of a damage is set to a random value between 1 and 20
     *          | new.getMAX_DAMAGE() == (r.nextInt(20 + 1) + 1)
     */
    public void setMaxDamage(){
        Random r = new Random();
        MAX_DAMAGE = r.nextInt(20 + 1) + 1;
    }

    /**
     * Returns maximum value of damage
     * @return  MAX_DAMAGE
     */
    public int getMAX_DAMAGE(){
        return MAX_DAMAGE;
    }

    /**
     * Sets damage caused by weapon to new damage
     * @pre   newDamage is in range(1, MAX_DAMAGE)
     *        | newDamage >= MIN_DAMAGE && newDamage <= MAX_DAMAGE
     * @post  Value of damage eguals the value of newDamage
     *        | new.getDamage() == newDamage
     */
    public void setDamage(int newDamage){
        assert(newDamage >= MIN_DAMAGE & newDamage <= MAX_DAMAGE);
        this.damage = newDamage;
    }

    /**
     * Returns damage of the weapon
     * @return  damage of this weapon
     *          |this.damage
     */
    @Basic
    @Immutable
    public int getDamage(){
        return this.damage;
    }

    /**
     * Repairs the weapon and increases its value
     * @pre     Value to be added to the value of this weapon has to be bigger than 0
     *          | value > 0
     * @param   value
     *          Value to be added to the value of this weapon
     * @effect  Value of the weapon is set to new value
     *          | setValue(this.getValue() + value)
     */
    public void repair(int value) {
        assert(value > 0);
        setValue(this.getValue() + value);
    }

    /**
     * Weapon is eroding and thus its value is decreasing by given value
     * @pre     New value of a weapon has to be bigger than 0
     *          | this.value - value > 0
     * @param   value
     *          Value to be subtracted from current value of this weapon
     * @effect  Value of the weapon is set to value lower than current value
     *          | setValue(this.getValue() - value)
     */
    public void erode(int value) {
        assert(this.getValue() - value > 0);
        setValue(this.getValue() - value);
    }

}
