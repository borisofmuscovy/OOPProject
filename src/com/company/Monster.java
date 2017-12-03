package com.company;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;


/**
 * A class of Monsters
 *
 * @author Boris Shilov & Alicja Ochman
 */
public class Monster {

    public final String name;
    public final int strength;
    public int damage;
    public final int protection;
    public int hp;
    private int MAX_DAMAGE;
    private int MIN_DAMAGE = 1;
    private int MAX_HP;
    private boolean Alive = true;

    public Monster(String Name, int startHP) throws IllegalArgumentException {
        if (!Name.matches("[A-Z][a-zA-Z0-9 ']{2,}")) {
            throw new IllegalArgumentException("You cannot name your monster " +
                    Name + "! Monster culture demands names begin with " +
                    "a capital letter and consist only of upper or lowercase letters and digits, with \' also allowed.");
        } else {
            this.name = Name;
        }
        setMAX_DAMAGE(20);
        changeDamage();
        this.strength = (int) (new Random().nextGaussian() + 10);

        int startProtection = generateProtectionFactor();
        assert isPrime(startProtection);
        this.protection = startProtection ;
        hp = startHP;
        MAX_HP = hp;
    }

    public boolean isAlive() {
        return this.Alive;
    }

    public int getStrength(){
        return this.strength;
    }


    public void setMAX_DAMAGE(int newMAX_DAMAGE) {
        MAX_DAMAGE = newMAX_DAMAGE;
    }

    public void changeDamage(){
        Random randno = new Random();
        this.damage = randno.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
    }

    public int getDamage(){
        return this.damage;
    }

    public int hitDamage(){
        int momentaryStrength = ((this.getStrength() - 5) / 3);
        return (this.getDamage() + momentaryStrength);
    }


    public String getName(){
        return this.name;
    }

    int MAX_PROTECTION = 40;
    int MIN_PROTECTION = 1;

    public int generateProtectionFactor() {
        ArrayList<Integer> primeList = new ArrayList<Integer>();
        for(int i=1; i < MAX_PROTECTION; i++){
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
        int protectionFactor = primeList.get(n.nextInt(primeList.size()));
        return protectionFactor;
    }

    public boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    public int getProtection(){ return this.protection; }

    public void setProtection(){ Random protection = new Random(); }

    /**
     * Retrieves the current health points of the monster. If the monster is dead, throws exception.
     * @return The integer hp if and only if the monster isAlive.
     * @throws IllegalAccessException
     *         This monster is dead. Health points are no longer meaningful.
     *         | this.isAlive == false
     */

    public int getHP() {
        return this.hp;
    }

    /**
     * Allows to directly modify the health points of the monster.
     * @param newHP
     *        The new HP for this monster.
     * @post The HP of the monster will be equal to the HP provided, if it is not equal or less than 0.
     *       | if (newHP > 0) {hp == newHP}
     * @throws IllegalArgumentException
     *         The new HP is equal to or less than 0, which means the monster is dead.
     *         | if (newHP <= 0) { Death() }
     */

    private void setHP(int newHP) throws IllegalArgumentException {
        // look at exception catching again later, make sure it will actually throw if HP <= 0
            if (newHP <= 0) {
                this.Death();
                throw new IllegalArgumentException("Battle over, quitting...");
            } else {
                this.hp = newHP;
            }

    }

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

    public void hit(Monster opponent) throws IllegalArgumentException, IllegalStateException{
        try {
            if (this.isAlive() == false) {
                throw new IllegalStateException();
            } else {
                // D&D-like dice mechanic - generate a random number between 1-30 to try and bypass attackers protection
                System.out.println(this.getName() + " is taking a swing at " + opponent.getName() + "!");
                Random randno = new Random();
                int diceVal = randno.nextInt(30 - 1 + 1) + 1;
                int genVal;
                if (diceVal < opponent.getHP()) {
                    genVal = diceVal;
                } else {
                    genVal = opponent.getHP();
                }
                // compare generated value to opponent protection

                if (genVal >= opponent.getProtection()){
                    int damagedHP = opponent.getHP() - this.hitDamage();
                    System.out.println("The hit connects! " + this.getName() + " does " + this.hitDamage() + " damage to " + opponent.getName() + "!");
                    opponent.setHP(damagedHP);
                } else {
                    System.out.println(this.getName() + " misses " + opponent.getName() + " completely!");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(opponent.getName() + " has died.");
        } catch (IllegalStateException e) {
            System.out.println(this.getName() + " is dead, they cannot hurt anyone no more.");
        }
    }

    private void Death(){
        this.Alive = false;
    }


}
