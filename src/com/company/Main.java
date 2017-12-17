package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);
        Weapon SKULLCRUSHER = new Weapon(2, 10, 10, null);
        Alice.equip(SKULLCRUSHER);
        Purse Gucci = new Purse(10, 1, 20, 50);
        Alice.equip(Gucci);

        if (Math.random() < 0.5){
            while (Bob.isAlive() && Alice.isAlive()) {
                System.out.println(Bob.hit(Alice));
                System.out.println(Alice.hit(Bob));
            }
        } else {
                while (Bob.isAlive() && Alice.isAlive()) {
                    System.out.println(Alice.hit(Bob));
                    System.out.println(Bob.hit(Alice));
                }
            }

        if (Alice.isAlive()) {
            System.out.println(Alice.getName() + " has Protection " + Alice.getProtection() + ", Strength " +
                    Alice.getStrength() + ", Damage " + Alice.getDamage() + " and their maximum HP is " + Alice.getMAX_HP() + ".");
        } else if (Bob.isAlive()) {
            System.out.println(Bob.getName() + " has Protection " + Bob.getProtection() + ", Strength " +
                    Bob.getStrength() + ", Damage " + Bob.getDamage() + " and their maximum HP is " + Bob.getMAX_HP() + ".");
        } else {
            System.out.println("Everybody is dead!");
        }


        Backpack Pocket = new Backpack(20, null, 25, 40);
        Pocket.addBackpackContent(Gucci);
        Pocket.addBackpackContent(SKULLCRUSHER);

        System.out.println(Pocket.getBackpackContent());
        Pocket.removeBackpackContent(Gucci);
        System.out.println(Pocket.getBackpackContent());

    }
}
