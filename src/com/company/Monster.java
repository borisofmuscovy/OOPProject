package com.company;

public class Monster {

    public final String name;
    public final int damage;
    public final int strength;
    public int protection;
    public int hp;

    public Monster(String startName, int startDamage, int startStrength, int startProtection, int startHP) throws IllegalArgumentException {
        if (!startName.matches("[A-Z][a-zA-Z0-9 ']{2,}")) {
            throw new IllegalArgumentException("You cannot name your monster " +
                    startName + "! Monster culture demands names begin with " +
                    "a capital letter and consist only of upper or lowercase letters and digits, with \' also allowed.");
        } else {
            this.name = startName;
        }
        this.damage = startDamage;
        this.strength = startStrength;
        protection = startProtection;
        hp = startHP;
    }


    public String getName(){
        return this.name;
    }
}
