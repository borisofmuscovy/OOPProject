package com.Monsters;

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

    public static void Part1() {
        System.out.println("This is Part 1 of the project.");
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);
        System.out.println(Bob);
        System.out.println(Alice);
        Weapon SKULLCRUSHER = new Weapon(2, 10, 10);
        Alice.equip(SKULLCRUSHER);
        System.out.println(Alice.getInventoryContents());
        Alice.trade(Bob, SKULLCRUSHER);
        System.out.println("Alice" + Alice.getInventoryContents());
        System.out.println("Bob" + Bob.getInventoryContents());
        System.out.println(SKULLCRUSHER.getHolder());
        Battle(Alice, Bob);
        System.out.println("END PART 1\n");
    }

    public static void Part2(){
        System.out.println("This is Part 2 of the project.");
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);
        System.out.println(Bob);
        System.out.println(Alice);
        Purse Gucci = new Purse(10, 1, 20, 50);
        Purse Yves = new Purse(20, 1, 30, 70);
        Alice.equip(Gucci);
        Bob.equip(Yves);
        Battle(Alice, Bob);
        System.out.println("END PART 2\n");
    }

    public static void Part3(){
        System.out.println("This is Part 3 of the project.");
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);

        Backpack BobsBackpack = new Backpack(10, Bob, 15, 30);

        Weapon Stick = new Weapon(2, 10, 12);
        Alice.equip(Stick);

        System.out.println(Bob);
        System.out.println(Bob.getInventoryContents());
        System.out.println(Alice);
        System.out.println(Alice.getInventoryContents());

        Backpack Versace = new Backpack(20, 25, 100);
        Weapon SKULLCRUSHER = new Weapon(2, 10, 10);
        Versace.add(SKULLCRUSHER);
        Purse Gucci = new Purse(10, 1, 20, 50);
        Purse Yves = new Purse(20, 1, 30, 70);
        Versace.add(Gucci, Yves);

        Bob.unequip(BobsBackpack);
        Bob.equip(Versace);

        String Winner = Battle(Bob, Alice);

//        if (Winner == "Bob") {
//            Alice.trade
//        }
        System.out.println("END PART 3\n");
    }

    public static void main(String[] args) {
        Part1();
        Part2();
        Part3();

    //Code below is only for testing purposes
//        Backpack Pocket = new Backpack(20, Alice, 25, 100);
//        Backpack Pouch = new Backpack(20, Bob, 25, 100);
//        Weapon bonemasher = new Weapon(5, 10, 12, null);
//        Pocket.add(Gucci, SKULLCRUSHER);
//        Pouch.add(bonemasher);
//        print(bonemasher.getHolder());
//        //Pouch.add(Gucci);
//        System.out.println(Pouch.getBackpackContents());
//        System.out.println(Pocket.getBackpackContents());
//        Pocket.add(Yves);
//        System.out.println(Pocket.getBackpackContents());
//        System.out.println(Pocket.getLightest());
//        Pocket.add(Pouch);
//        print(Pocket.getBackpackContents());
//        Pocket.setHolderRecursively();
//        print(Pouch.getHolder());
//        print(Pouch.getIndirectHolder());
//        print(bonemasher.getHolder());
//        Bob.unequip(Pouch);
//        print(Pouch.getHolder());
//        print(Bob.getInventoryContents());
    }
}
