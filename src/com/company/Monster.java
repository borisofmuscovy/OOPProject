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
    }

    public void setMAX_DAMAGE(int newMAX_DAMAGE) {
        MAX_DAMAGE = newMAX_DAMAGE;
    }

    public void changeDamage(){
        Random randno = new Random();
        this.damage = randno.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
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

    public void setProtection(){
        Random protection = new Random();

    }
}
