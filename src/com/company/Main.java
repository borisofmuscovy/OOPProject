package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Monster Bob = new Monster("Bob", 15);
        Monster Alice = new Monster("Alice", 15);
        int swings = 0;
        while (Bob.isAlive() && Alice.isAlive()) {
            Bob.hit(Alice);
            Alice.hit(Bob);
            swings++;
            if (swings == 15) {
                System.out.println("Draw.");
                break;
            }
        }
        if (Bob.isAlive()) {
            System.out.println(Bob.getName() + " has Protection " + Bob.getProtection() + ", Strength " +
                    Bob.getStrength() + ", Damage " + Bob.getDamage() + " and their maximum HP is " + Bob.getMAX_HP() + ".");
        } else if (Alice.isAlive()) {
            System.out.println(Alice.getName() + " has Protection " + Alice.getProtection() + ", Strength " +
                    Alice.getStrength() + ", Damage " + Alice.getDamage() + " and their maximum HP is " + Alice.getMAX_HP() + ".");
        } else if (Alice.isAlive() && Bob.isAlive()) {
            System.out.println(Bob.getName() + " has Protection " + Bob.getProtection() + ", Strength " +
                    Bob.getStrength() + ", Damage " + Bob.getDamage() + " and their maximum HP is " + Bob.getMAX_HP() + ".");
            System.out.println(Alice.getName() + " has Protection " + Alice.getProtection() + ", Strength " +
                    Alice.getStrength() + ", Damage " + Alice.getDamage() + " and their maximum HP is " + Alice.getMAX_HP() + ".");
        } else {
            System.out.println("Everybody is dead!");
        }

    }
}
