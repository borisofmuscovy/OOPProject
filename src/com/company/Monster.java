package com.company;
import java.util.Random;

public class Monster {

    public final String name;
    public final int damage;
    public final int strength;
    public int protection;
    public int hp;
    private int MAX_DAMAGE;
    private int MIN_DAMAGE = 1;

    public Monster(String startName, int startDamage, int startStrength, int startProtection, int startHP) throws IllegalArgumentException {
        if (!startName.matches("[A-Z][a-zA-Z0-9 ']{2,}")) {
            throw new IllegalArgumentException("You cannot name your monster " +
                    startName + "! Monster culture demands names begin with " +
                    "a capital letter and consist only of upper or lowercase letters and digits, with \' also allowed.");
        } else {
            this.name = startName;
        }
        setMAX_DAMAGE(20);
        Random randno = new Random();
        this.damage = randno.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE;
        this.strength = startStrength;
        protection = startProtection;
        hp = startHP;
    }

    public void setMAX_DAMAGE(int newMAX_DAMAGE) {
        MAX_DAMAGE = newMAX_DAMAGE;
    }


    public String getName(){
        return this.name;
    }
}
