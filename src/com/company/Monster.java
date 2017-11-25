package com.company;
import java.util.ArrayList;
import java.util.Random;


/**
 * A class of Monsters
 *
 * @author Boris Shulov & Alicja Ochman
 */
public class Monster {

    public final String name;
    public final int strength;
    public int damage;
    public int protection;
    public int hp;
    private int MAX_DAMAGE;
    private int MIN_DAMAGE = 1;
    private int MAX_HP;

    public Monster(String Name, int startDamage, int startStrength, int startProtection, int startHP) throws IllegalArgumentException {
        if (!Name.matches("[A-Z][a-zA-Z0-9 ']{2,}")) {
            throw new IllegalArgumentException("You cannot name your monster " +
                    Name + "! Monster culture demands names begin with " +
                    "a capital letter and consist only of upper or lowercase letters and digits, with \' also allowed.");
        } else {
            this.name = Name;
        }
        setMAX_DAMAGE(20);
        changeDamage();
        this.strength = startStrength;
        protection = startProtection;
        hp = startHP;
        MAX_HP = hp;
    }


    public void setMAX_DAMAGE(int newMAX_DAMAGE) {
        MAX_DAMAGE = newMAX_DAMAGE;
    }

    public void changeDamage(){
        Random randno = new Random();
        this.damage = randno.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
    }

    private int getDamage(){
        return this.damage;
    }

    public int hitDamage(){

    }


    public String getName(){
        return this.name;
    }

    int MAX_PROTECTION = 40;
    int MIN_PROTECTION = 1;

    public ArrayList<Integer> primeList(int MAX_PROTECTION) {
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
        return primeList;
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

    public int getProtection(){
        return this.protection;
    }

    public void setProtection(){
        Random protection = new Random();

    }

    public int getHP(){
        return this.hp;
    }

    private void setHP(int newHP){
        this.hp = newHP;
    }

    public void setMAX_HP(int newMAX_HP){
        this.MAX_HP = newMAX_HP;
    }

    public int hitBy(Monster attacker){
        // D&D-like dice mechanic - generate a random number between 1-30 to try and bypass attackers protection
        system.out.println(this.getName() + " is taking a swing at " + attacker.getName() + "!")
        Random randno = new Random();
        int diceVal = randno.nextInt(30 - 1 + 1) + 1;
        int genVal;
        if (diceVal < attacker.getHP()) {
            genVal = diceVal;
        } else if (diceVal => attacker.getHP()) {
            genval = attacker.getHP();
        }
        // compare generated value to opponent protection

        if (genVal => attacker.getProtection()) {
            System.out.println("The hit connects!");
            attacker.setHP(attacker.getHP() - attacker.get)
        }



    }



}
