package com.Monsters;

import java.util.Map;

public class Main {

    public static String Battle(Monster Monster1, Monster Monster2) {
        if (Math.random() < 0.5){
            while (Monster1.isAlive() && Monster2.isAlive()) {
                System.out.println(Monster1.hit(Monster2));
                System.out.println(Monster2.hit(Monster1));
            }
        } else {
            while (Monster1.isAlive() && Monster2.isAlive()) {
                System.out.println(Monster2.hit(Monster1));
                System.out.println(Monster1.hit(Monster2));
            }
        }

        if (Monster2.isAlive()) {
            System.out.println(Monster2.getName() + " has Protection " + Monster2.getProtection() + ", Strength " +
                    Monster2.getStrength() + ", Damage " + Monster2.getDamage()
                    + " and their maximum HP is " + Monster2.getMAX_HP() + ".");
            return Monster2.getName();
        } else if (Monster1.isAlive()) {
            System.out.println(Monster1.getName() + " has Protection " + Monster1.getProtection() + ", Strength " +
                    Monster1.getStrength() + ", Damage " + Monster1.getDamage()
                    + " and their maximum HP is " + Monster1.getMAX_HP() + ".");
            return Monster1.getName();
        } else {
            System.out.println("Everybody is dead!");
            return null;
        }
    }

    public static void Part1() throws IllegalAccessException{
        System.out.println("This is Part 1 of the project.");
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);

        Weapon Skullcrusher = new Weapon(2, 10, 10);
        Alice.equip(Skullcrusher);
        System.out.println(Alice.getInventoryContents());
        Alice.trade(Bob, Skullcrusher);
        System.out.println("Alice" + Alice.getInventoryContents());
        System.out.println("Bob" + Bob.getInventoryContents());
        System.out.println(Skullcrusher.getHolder());
        Battle(Alice, Bob);
        System.out.println("END PART 1\n");
    }

    public static void Part2() throws IllegalAccessException{
        System.out.println("This is Part 2 of the project.");
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);
        System.out.println(Bob);
        System.out.println(Alice);

        Purse Gucci = new Purse(10, 1, 20, 50);
        Purse Yves = new Purse(20, 1, 30, 70);

        Weapon Bonemasher = new Weapon(5, 5, 10);
        Weapon Skullcrusher = new Weapon(5, 5, 10);

        Alice.equip(Gucci);
        Alice.equip(Bonemasher);
        Bob.equip(Yves);
        Bob.equip(Skullcrusher);

        Battle(Alice, Bob);
        if (Bob.isAlive()) {
            for (Map.Entry<String,InventoryItem> entry : Alice.getInventoryContents().entrySet()) {
                if (entry.getValue() instanceof Weapon) {
                    Alice.trade(Bob, entry.getValue());
                } else if (entry.getValue() instanceof Purse) {
                    ((Purse) entry.getValue()).transferContent(Yves, ((Purse) entry.getValue()).getContent());
                }
            }
            System.out.println(Bob.getInventoryContents());
        } else if (Alice.isAlive()) {
            for (Map.Entry<String,InventoryItem> entry : Bob.getInventoryContents().entrySet()) {
                if (entry.getValue() instanceof Weapon) {
                    Bob.trade(Alice, entry.getValue());
                } else if (entry.getValue() instanceof Purse) {
                    ((Purse) entry.getValue()).transferContent(Gucci, ((Purse) entry.getValue()).getContent());
                }
            }
            System.out.println(Alice.getInventoryContents());
        }
        System.out.println("END PART 2\n");
    }

    public static void Part3() throws IllegalAccessException{
        System.out.println("This is Part 3 of the project.");
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);

        Backpack BobsBackpack = new Backpack(10, Bob, 15, 30);

        Weapon Stick = new Weapon(2, 10, 12);
        Alice.equip(Stick);
        Weapon Rock = new Weapon(2, 10, 12);
        Bob.equip(Rock);

        System.out.println(Bob.getInventoryContents());
        System.out.println(Alice.getInventoryContents());

        Backpack Versace = new Backpack(20, 25, 100);
        Weapon Skullcrusher = new Weapon(2, 10, 10);
        Versace.add(Skullcrusher);

        Purse Gucci = new Purse(10, 1, 20, 50);
        Purse Yves = new Purse(20, 1, 30, 70);
        Versace.add(Gucci, Yves);

        Bob.unequip(BobsBackpack);
        Bob.equip(Versace);

        Battle(Bob, Alice);

        if (Bob.isAlive()) {
            Alice.trade(Bob, Alice.getInventoryContents().get("Left"));
            Alice.trade(Bob, Alice.getInventoryContents().get("Right"));
            Alice.trade(Bob, Alice.getInventoryContents().get("Back"));
        } else if (Alice.isAlive()) {
            Bob.trade(Alice, Bob.getInventoryContents().get("Left"));
            Bob.trade(Alice, Bob.getInventoryContents().get("Right"));
            Bob.trade(Alice, Bob.getInventoryContents().get("Back"));
        }

        System.out.println("END PART 3\n");
    }

    public static void main(String[] args) throws IllegalAccessException{
        Part1();
        Part2();
        Part3();
    }
}
