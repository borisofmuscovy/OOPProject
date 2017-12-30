package com.company;

public class Main {

    public static void main(String[] args) {
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);
        System.out.println(Bob);
        System.out.println(Alice);
        Weapon SKULLCRUSHER = new Weapon(2, 10, 10, null);
        Purse Gucci = new Purse(10, 1, 20, 50);
        Purse Yves = new Purse(20, 1, 20, 70);
        Alice.equip(Gucci);
        Alice.equip(SKULLCRUSHER);
        System.out.println(Alice.getInventoryContents());
        Alice.trade(Bob, SKULLCRUSHER);
        System.out.println("Alice" + Alice.getInventoryContents());
        System.out.println("Bob" + Bob.getInventoryContents());
        System.out.println(SKULLCRUSHER.getHolder());
//
//        if (Math.random() < 0.5){
//            while (Bob.isAlive() && Alice.isAlive()) {
//                System.out.println(Bob.hit(Alice));
//                System.out.println(Alice.hit(Bob));
//            }
//        } else {
//                while (Bob.isAlive() && Alice.isAlive()) {
//                    System.out.println(Alice.hit(Bob));
//                    System.out.println(Bob.hit(Alice));
//                }
//            }
//
//        if (Alice.isAlive()) {
//            System.out.println(Alice.getName() + " has Protection " + Alice.getProtection() + ", Strength " +
//                    Alice.getStrength() + ", Damage " + Alice.getDamage() + " and their maximum HP is " + Alice.getMAX_HP() + ".");
//        } else if (Bob.isAlive()) {
//            System.out.println(Bob.getName() + " has Protection " + Bob.getProtection() + ", Strength " +
//                    Bob.getStrength() + ", Damage " + Bob.getDamage() + " and their maximum HP is " + Bob.getMAX_HP() + ".");
//        } else {
//            System.out.println("Everybody is dead!");
//        }

//        Alice.unequip(Gucci);
//        System.out.println(Alice.getInventoryContents());
//        Alice.unequip(SKULLCRUSHER);
//        System.out.println(Alice.getInventoryContents());

    //Code below is only for testing purposes
        Backpack Pocket = new Backpack(20, Alice, 25, 100);
        Backpack Pouch = new Backpack(20, Bob, 25, 100);
        Pocket.add(Gucci, SKULLCRUSHER);
        //Pouch.add(Gucci);
        System.out.println(Pouch.getBackpackContents());
        System.out.println(Pocket.getBackpackContents());
        Pocket.add(Yves);
        System.out.println(Pocket.getBackpackContents());
        System.out.println(Pocket.getLightest());

    }
}
