package com.Monsters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackpackTest {
    @Test
    void add() {
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Backpack Kipling = new Backpack(15, 5, 60);
        Kipling.add(Skullcrusher);
        assertTrue(Kipling.getBackpackContents().contains(Skullcrusher));
    }

    @Test
    void removeWeapon() {
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Backpack Kipling = new Backpack(15, 5, 60);
        Kipling.add(Skullcrusher);
        Kipling.remove(Skullcrusher);
        assertTrue(Kipling.getBackpackContents().contains(Skullcrusher));
    }

    @Test
    void remove() {
        Purse Gucci = new Purse(20, 5, 2, 30);
        Backpack Kipling = new Backpack(15, 5, 60);
        Kipling.add(Gucci);
        Kipling.remove(Gucci);
        assertFalse(Kipling.getBackpackContents().contains(Gucci));
    }

    @Test
    void transfer() {
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Backpack Kipling = new Backpack(15, 5, 60);
        Backpack Next = new Backpack(30, 6, 60);
        Kipling.add(Skullcrusher);
        Kipling.transfer(Next, Skullcrusher);
        assertTrue((!Kipling.getBackpackContents().contains(Skullcrusher))
                && (Next.getBackpackContents().contains(Skullcrusher)));
    }

    @Test
    void multiAdd() {
        Backpack Kipling = new Backpack(15, 5, 60);
        Weapon Skullcrusher1 = new Weapon(10, 10, 20);
        Weapon Skullcrusher2 = new Weapon(11, 10, 20);
        Weapon Skullcrusher3 = new Weapon(12, 10, 20);
        Purse Gucci = new Purse(20, 13, 2, 30);
        Kipling.add(Skullcrusher1, Skullcrusher2, Skullcrusher3, Gucci);
        boolean idiot = Kipling.getBackpackContents().contains(Skullcrusher2);
        assertTrue(Kipling.getBackpackContents().contains(Skullcrusher1)
                && Kipling.getBackpackContents().contains(Skullcrusher2)
                && Kipling.getBackpackContents().contains(Skullcrusher3)
                && Kipling.getBackpackContents().contains(Gucci));
    }


    @Test
    void getContentsWeightSameWeight() {
        //this mostly tests the comparator. will only work properly if we can order many same weight objects ok
        Backpack Kipling = new Backpack(15, 5, 60);
        Weapon Skullcrusher1 = new Weapon(10, 10, 20);
        Weapon Skullcrusher2 = new Weapon(10, 10, 20);
        Weapon Skullcrusher3 = new Weapon(10, 10, 20);
        Purse Gucci = new Purse(20, 11, 2, 30);
        Purse Dolce = new Purse(20, 11, 2, 30);
        Kipling.add(Skullcrusher1, Skullcrusher2, Skullcrusher3, Gucci, Dolce);
        float contentWeight = Skullcrusher1.getWeight() + Skullcrusher2.getWeight() + Skullcrusher3.getWeight()
                + Gucci.getWeight() + Dolce.getWeight();
        float trueContentWeight = Kipling.getContentsWeight();
        assertTrue(contentWeight == trueContentWeight);
    }

    @Test
    void getTotalWeight() {
    }

    @Test
    void getTotalValue() {
    }

    @Test
    void getHeaviest() {
    }

    @Test
    void getLightest() {
    }

}