package com.company;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import java.util.ArrayList;
import java.util.Random;


/**
 * A class of Monsters involving their names, health points, protection, strength and damage values,
 * as well as the ability to engage each other in deadly combat.
 *
 * @version 0.1
 * @author Boris Shilov & Alicja Ochman
 */
public class Monster {

    private final String name;
    private int strength;
    private int damage;
    private final int protection;
    private int hp;
    private static int MAX_DAMAGE;
    private static final int MIN_DAMAGE;
    private int MAX_HP;
    private boolean Alive;
    private static int MAX_PROTECTION;
    private static final int MIN_PROTECTION;

    static {
        MIN_DAMAGE = 1;
        MAX_DAMAGE = 20;
        MIN_PROTECTION = 1;
        MAX_PROTECTION = 40;
    }

    {
        Alive = true;
    }

    /**
     * Initialise a named monster with a given number of hitpoints.
     *
     * @param Name
     *        The name of the monster. Must start with a capital letter, can contain lowercase, uppercase letters, numbers
     *        and the ' character.
     * @param startHP
     *        The health points value the monster starts with. This is the maximum HP of the monster unless changed explicitly.
     *
     */
    public Monster(String Name, int startHP) throws IllegalArgumentException{
        if (!isValidName(Name)){
            throw new IllegalArgumentException();
            }
        this.name = Name;
        changeDamage();
        this.strength = (int) (new Random().nextGaussian() + 10);
        this.protection = generateProtectionFactor();
        hp = startHP;
        MAX_HP = hp;
    }

    /**
     * Return the name of this monster.
     * @return  name
     *          | this.name
     */
    @Basic @Immutable
    public String getName(){
        return this.name;
    }

    /**
     * Checks validity of the monsters name
     * @param   Name
     *          Name of the monster as a string to be checked.
     * @return  True if and only if the monsters name will begin with an uppercase letter, and will contain only
     *          lower, uppercase characters, numbers and the symbol '.
     *          | if (Name.matches("[A-Z][a-zA-Z0-9 ']{2,}"))
     *                  return True
     *            else
     *                  return False
     */
    private boolean isValidName(String Name) {
        if (!Name.matches("[A-Z][a-zA-Z0-9 ']{2,}")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns whether the monster is alive or not.
     * @return  True if alive, false if dead.
     *          | this.Alive
     */
    public boolean isAlive() {
        return this.Alive;
    }

    /**
     * Kills the monster by setting Alive to false.
     * @post    Sets this.Alive to false
     */
    private void Death(){
        this.Alive = false;
    }

    /**
     * Returns the strength of the monster.
     * @return  strength
     *          | this.strength
     */
    public int getStrength(){
        return this.strength;
    }

    protected void setStrength(int newStrength){
        this.strength = newStrength;
    }

    protected static int getMAX_DAMAGE() {
        return MAX_DAMAGE;
    }

    /**
     * Allows setting a new maximum damage value for this monster.
     * @param   newMAX_DAMAGE
     *          The new maximum damage value to be adopted.
     * @post    If the new maximum damage is more than or equal 1, the maximum damage value will equal to the new value.
     *          | if (newMAX_DAMAGE => 1) { MAX_DAMAGE = newMAX_DAMAGE }
     */
    public static void setMAX_DAMAGE(int newMAX_DAMAGE) {
        if (newMAX_DAMAGE >= 1) {
            MAX_DAMAGE = newMAX_DAMAGE;
        }
    }

    /**
     * Returns the damage of the monster.
     * @return  damage
     *          | this.damage
     */
    public int getDamage(){
        return this.damage;
    }

    /**
     * Re-roll the damage of the monster.
     * @post The new damage value will be a random number between the MIN_DAMAGE value and the MAX_DAMAGE value.
     *       | newDamage == rand(MIN_DAMAGE:MAX_DAMAGE)
     */

    private void changeDamage() {
        Random randno = new Random();
        this.damage = randno.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
    }

    /**
     * Calculates the damage dealt by a single hit using the strength and damage values, according to a set formula.
     * @return  | damage + ((strength - 5) / 3)
     */
    private int hitDamage() {
        int momentaryStrength = ((this.getStrength() - 5) / 3);
        return (this.getDamage() + momentaryStrength);
    }


    /**
     * Returns the protection value.
     * @return  protection
     */
    public int getProtection() { return this.protection; }

    /**
     * Generates a protection factor which is a prime number between the allowed minimum and maximum protection values
     * @return  protection
     * @pre     The protection is represented as a prime number between MIN_PROTECTION and MAX_PROTECTION
     *          | isPrime(initialProtectionFactor)
     *          | MIN_PROTECTION < initialProtectionFactor <MAX_PROTECTION
     */
    private int generateProtectionFactor() {
        ArrayList<Integer> primeList = new ArrayList<Integer>();
        int i;
        for(i = MIN_PROTECTION; i < MAX_PROTECTION; i++){
            boolean isPrime = true;
            for(int j=2; j < i; j++ ){
                if(i % j ==0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime)
                primeList.add(i);
        }
        Random n = new Random();
        int initialProtectionFactor = primeList.get(n.nextInt(primeList.size()));
        assert (isPrime(initialProtectionFactor)&&(MIN_PROTECTION <= initialProtectionFactor)&&(initialProtectionFactor <= MAX_PROTECTION));
        return initialProtectionFactor;
    }

    /**
     * @param   n
     *          The number to be checked
     * @return  True if the number is a prime number and false otherwise
     *
     */
    private boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) {
            return false;
        }
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    /**
     * Retrieves the current health points of the monster. If the monster is dead, throws exception.
     * @return      The integer hp if and only if the monster isAlive.
     *              | this.hp
     */

    public int getHP() {
        return this.hp;
    }

    /**
     * Allows to directly modify the health points of the monster.
     * @param   newHP
     *          The new HP for this monster.
     * @post    The HP of the monster will be equal to the HP provided, if it is not equal or less than 0.
     *          | if (newHP > 0) {hp == newHP}
     * @throws  IllegalArgumentException
     *          The new HP is equal to or less than 0, which means the monster is dead.
     *          | if (newHP <= 0) { Death() }
     */

    private void setHP(int newHP) throws IllegalArgumentException {
            if (newHP <= 0) {
                this.Death();
                throw new IllegalArgumentException();
            } else {
                this.hp = newHP;
            }

    }

    /**
     * @return  MAX_HP
     *          | this.MAX_HP
     */
    public int getMAX_HP() { return this.MAX_HP; }

    /**
     * Sets the ceiling for allowed HP values.
     * @param newMAX_HP
     *        The new HP ceiling.
     * @post The new HP ceiling will always equal the provided value.
     *       | MAX_HP == newMAX_HP
     */

    public void setMAX_HP(int newMAX_HP){
        this.MAX_HP = newMAX_HP;
    }

    /**
     * Method representing battle of the monsters.
     * @param   opponent
     *          | Oponent monster
     * @throws  IllegalStateException
     *          If the isAlive is false (monster has no HP) it cannot take part in the battle.
     *          |  if !this.isAlive() || !opponent.isAlive()
     */
    public String hit(Monster opponent) throws IllegalArgumentException, IllegalStateException{
        try {
            if (!this.isAlive()) {
                throw new IllegalStateException();
            } else {
                // D&D-like dice mechanic - generate a random number between 1-30 to try and bypass attackers protection
                Random randno = new Random();
                int diceVal = randno.nextInt(30 - 1 + 1) + 1;
                int genVal;
                if (diceVal < this.getHP()) {
                    genVal = diceVal;
                } else {
                    genVal = this.getHP();
                }
                // compare generated value to opponent protection

                if (genVal >= opponent.getProtection()){
                    int damagedHP = opponent.getHP() - this.hitDamage();
                    opponent.setHP(damagedHP);
                    return (this.getName() + " hits " + opponent.getName() +" and does " + this.hitDamage()
                            + " damage! " + opponent.getName() + " has " + opponent.getHP() + " HP left!");
                } else {
                    return (this.getName() + " tried to hit " + opponent.getName() + ", but had no effect!");
                }
            }
        } catch (IllegalArgumentException e) {
            return (opponent.getName() + " has been slain! The mortal blow has been dealt by " + this.getName()
                    + " dealing " + this.hitDamage() + " damage!");
        } catch (IllegalStateException e) {
            return null;
        }
    }

}
