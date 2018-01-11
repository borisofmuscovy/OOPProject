package com.Monsters;

import java.security.AllPermission;
import java.util.Map;

public class Main {


    public static void Battle(Monster Monster1, Monster Monster2) {
        int hitCount = 0;
        if (Math.random() < 0.5){
            while (Monster1.isAlive() && Monster2.isAlive() && (hitCount < 100)) {
                System.out.println(Monster1.hit(Monster2));
                System.out.println(Monster2.hit(Monster1));
                hitCount++;
            }
        } else {
            while (Monster1.isAlive() && Monster2.isAlive() && (hitCount < 100)) {
                System.out.println(Monster2.hit(Monster1));
                System.out.println(Monster1.hit(Monster2));
                hitCount++;
            }
        }
        if (Monster2.isAlive()) {
            System.out.println(Monster2.getName() + " won the fight.");
            printMonsterStats(Monster2);
        } else if (Monster1.isAlive()) {
            System.out.println(Monster1.getName() + " won the fight.");
            printMonsterStats(Monster1);
        } else {
            System.out.println("The opponents cannot beat each other. Draw.");
        }
    }

    public static void printMonsterStats(Monster Monster1) {
        System.out.println(Monster1.getName() + " has Protection " + Monster1.getProtection() + ", Strength " +
                Monster1.getStrength() + ", Damage " + Monster1.getDamage()
                + " and their maximum HP is " + Monster1.getMAX_HP() + ".");
    }

    public static void printInventory(Monster Monster1) {
        System.out.println(Monster1.getName() + " carries the following in their anchors: ");
        for (int i = 0; i < Monster1.getContents().size(); i++) {
            String anchor = (String) Monster1.getContents().keySet().toArray()[i];
            if (Monster1.getContents().get(anchor) instanceof Backpack) {
                System.out.print("A backpack in " + anchor + " anchor. ");
            } else if (Monster1.getContents().get(anchor) instanceof Weapon) {
                System.out.print("A weapon in " + anchor + " anchor. ");
            } else if (Monster1.getContents().get(anchor) instanceof Purse) {
                System.out.print("A purse in " + anchor + " anchor. ");
            } else {
                System.out.print("Nothing in " + anchor + " anchor. ");
            }
        }
        System.out.println("\n");
    }

    public static void Part1() throws IllegalAccessException{
        System.out.println("This is Part 1 of the project.");
        Monster Bob = new Monster("Bob", 30);
        printMonsterStats(Bob);
        Monster Alice = new Monster("Alice", 30);
        printMonsterStats(Alice);
        Battle(Alice, Bob);
        System.out.println("END PART 1\n");
    }

    public static void Part2() throws IllegalAccessException{
        System.out.println("This is Part 2 of the project.");
        Weapon Stick = new Weapon(2, 10, 12);
        Weapon Rock = new Weapon(2, 10, 12);
        Monster Bob = new Monster("Bob", 30, Rock);
        Monster Alice = new Monster("Alice", 30, Stick);

        printMonsterStats(Bob);
        printMonsterStats(Alice);

        Purse Gucci = new Purse(10, 1, 20, 50);
        Purse Yves = new Purse(20, 1, 30, 70);

        Alice.add(Gucci);
        Bob.add(Yves);

        Weapon Bonemasher = new Weapon(5, 5, 10);
        Weapon Skullcrusher = new Weapon(5, 5, 10);

        Alice.add(Bonemasher);
        Bob.add(Skullcrusher);

        Battle(Alice, Bob);
        try{
        if (Bob.isAlive()) {
            for (Map.Entry<String,InventoryItem> entry : Alice.getContents().entrySet()) {
                if (entry.getValue() instanceof Weapon) {
                    Alice.transfer(Bob, entry.getValue());
                } else if (entry.getValue() instanceof Purse) {
                    ((Purse) entry.getValue()).transfer(Yves, ((Purse) entry.getValue()).getContent());
                }
            }
        } else if (Alice.isAlive()) {
            for (Map.Entry<String,InventoryItem> entry : Bob.getContents().entrySet()) {
                if (entry.getValue() instanceof Weapon) {
                    Bob.transfer(Alice, entry.getValue());
                } else if (entry.getValue() instanceof Purse) {
                    ((Purse) entry.getValue()).transfer(Gucci, ((Purse) entry.getValue()).getContent());
                }
            }
        }
        } catch (IllegalArgumentException e1) {
            System.out.println(e1.getMessage());
        } catch (IllegalStateException e2) {
            System.out.print(e2.getMessage());
        } finally {
            if (Bob.isAlive()) {
                printInventory(Bob);
            } else if (Alice.isAlive()) {
                printInventory(Alice);
            }
        }
        System.out.println("END PART 2\n");
    }

    public static void Part3() throws IllegalAccessException{
        System.out.println("This is Part 3 of the project.");
        Weapon Stick = new Weapon(2, 10, 12);
        Weapon Rock = new Weapon(2, 10, 12);
        Backpack BobsBackpack = new Backpack(10, 15, 30);
        Backpack AlicesBackpack = new Backpack(10, 12, 32);


        Monster Bob = new Monster("Bob", 30, Stick, BobsBackpack);
        Monster Alice = new Monster("Alice", 30, Rock, AlicesBackpack);

        printMonsterStats(Bob);
        printInventory(Bob);
        printMonsterStats(Alice);
        printInventory(Alice);

        Backpack Versace = new Backpack(20, 25, 100);
        Weapon Skullcrusher = new Weapon(2, 10, 10);
        Versace.add(Skullcrusher);

        Purse Gucci = new Purse(10, 1, 20, 50);
        Purse Yves = new Purse(20, 1, 30, 70);
        Versace.add(Gucci, Yves);

        Bob.remove(BobsBackpack);
        Bob.add(Versace);

        Battle(Bob, Alice);

        try {
            if (Bob.isAlive()) {
                Alice.transfer(Bob, Alice.getContents().get("Left"), Alice.getContents().get("Back"));
            } else if (Alice.isAlive()) {
                Bob.transfer(Alice, Bob.getContents().get("Left"), Bob.getContents().get("Back"));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("The winner could not loot the losers inventory fully.");
        } finally {
            if (Bob.isAlive()) {
                printInventory(Bob);
            } else if (Alice.isAlive()) {
                printInventory(Alice);
            }
        }

        System.out.println("END PART 3\n");
    }

    public static void main(String[] args) throws IllegalAccessException, IllegalStateException, IllegalArgumentException{
        Part1();
        Part2();
        Part3();
    }
}
