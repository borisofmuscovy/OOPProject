package com.Monsters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BackpackTest {
    @Test
    void add() {
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Backpack Kipling = new Backpack(15, 5, 60);
        Kipling.add(Skullcrusher);
        assertTrue(Kipling.getContents().contains(Skullcrusher));
    }

    @Test
    void removeWeapon() {
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Backpack Kipling = new Backpack(15, 5, 60);
        Kipling.add(Skullcrusher);
        Kipling.remove(Skullcrusher);
        assertTrue(Kipling.getContents().contains(Skullcrusher));
    }

    @Test
    void remove() {
        Purse Gucci = new Purse(20, 5, 2, 30);
        Backpack Kipling = new Backpack(15, 5, 60);
        Kipling.add(Gucci);
        Kipling.remove(Gucci);
        assertFalse(Kipling.getContents().contains(Gucci));
    }

    @Test
    void transfer() {
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Backpack Kipling = new Backpack(15, 5, 60);
        Backpack Next = new Backpack(30, 6, 60);
        Kipling.add(Skullcrusher);
        Kipling.transfer(Next, Skullcrusher);
        assertTrue((!Kipling.getContents().contains(Skullcrusher))
                && (Next.getContents().contains(Skullcrusher)));
    }

    @Test
    void multiAdd() {
        Backpack Kipling = new Backpack(15, 5, 60);
        Weapon Skullcrusher1 = new Weapon(10, 10, 20);
        Weapon Skullcrusher2 = new Weapon(11, 10, 20);
        Weapon Skullcrusher3 = new Weapon(12, 10, 20);
        Purse Gucci = new Purse(20, 13, 2, 30);
        Kipling.add(Skullcrusher1, Skullcrusher2, Skullcrusher3, Gucci);
        boolean idiot = Kipling.getContents().contains(Skullcrusher2);
        assertTrue(Kipling.getContents().contains(Skullcrusher1)
                && Kipling.getContents().contains(Skullcrusher2)
                && Kipling.getContents().contains(Skullcrusher3)
                && Kipling.getContents().contains(Gucci));
    }

    @Test
    void getIndirectHolder() throws IllegalAccessException{
        Weapon Skullcrusher1 = new Weapon(10, 10, 20);
        Weapon Skullcrusher2 = new Weapon(10, 10, 20);
        Weapon Skullcrusher3 = new Weapon(10, 10, 20);
        Purse Dolce = new Purse(20, 6, 2, 30);
        Backpack Kipling1 = new Backpack(15, 5, 60);
        Backpack Kipling2 = new Backpack(15, 5, 60);
        Backpack Kipling3 = new Backpack(15, 5, 60);
        Kipling1.add(Skullcrusher1);
        Kipling2.add(Skullcrusher2);
        Kipling3.add(Skullcrusher3);
        Kipling2.add(Kipling3);
        Kipling1.add(Kipling2);
        Kipling1.add(Dolce);
        float TotalWeight = Skullcrusher1.getWeight() + Skullcrusher2.getWeight() + Skullcrusher3.getWeight()
                + Kipling1.getWeight() + Kipling2.getWeight() + Kipling3.getWeight() + Dolce.getWeight();
        Monster John;
        while (true) {
            John = new Monster("John", 40);
            if (John.getCarryingCapacity() > TotalWeight) {
                break;
            }
        }
        John.add(Kipling1);
        assertEquals(John, Skullcrusher3.getIndirectHolder());
    }

    @Test
    void willNotAddBackpackWithHeavyContents(){
        Backpack Kipling1 = new Backpack(15, 5, 15);
        Backpack Kipling2 = new Backpack(15, 5, 60);
        Weapon Skullcrusher = new Weapon(11, 10, 20);
        Kipling2.add(Skullcrusher);
        Kipling1.add(Kipling2);
        assertFalse(Kipling1.getContents().contains(Kipling2));
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
    void getTotalWeight(){
        Weapon Skullcrusher1 = new Weapon(10, 10, 20);
        Weapon Skullcrusher2 = new Weapon(10, 10, 20);
        Weapon Skullcrusher3 = new Weapon(10, 10, 20);
        Purse Dolce = new Purse(20, 6, 2, 30);
        Backpack Kipling1 = new Backpack(15, 5, 60);
        Backpack Kipling2 = new Backpack(15, 5, 60);
        Backpack Kipling3 = new Backpack(15, 5, 60);
        Kipling1.add(Skullcrusher1);
        Kipling2.add(Skullcrusher2);
        Kipling3.add(Skullcrusher3);
        Kipling2.add(Kipling3);
        Kipling1.add(Kipling2);
        Kipling1.add(Dolce);
        float trueTotalWeight = Skullcrusher1.getWeight() + Skullcrusher2.getWeight() + Skullcrusher3.getWeight()
                + Kipling1.getWeight() + Kipling2.getWeight() + Kipling3.getWeight() + Dolce.getWeight();
        assertEquals(Kipling1.getTotalWeight(), trueTotalWeight);
    }

    @Test
    void getTotalValue(){
        Weapon Skullcrusher1 = new Weapon(10, 10, 20);
        Weapon Skullcrusher2 = new Weapon(10, 10, 20);
        Weapon Skullcrusher3 = new Weapon(10, 10, 20);
        Purse Dolce = new Purse(20, 6, 2, 30);
        Backpack Kipling1 = new Backpack(15, 5, 60);
        Backpack Kipling2 = new Backpack(15, 5, 60);
        Backpack Kipling3 = new Backpack(15, 5, 60);
        Kipling1.add(Skullcrusher1);
        Kipling2.add(Skullcrusher2);
        Kipling3.add(Skullcrusher3);
        Kipling2.add(Kipling3);
        Kipling1.add(Kipling2);
        Kipling1.add(Dolce);
        float trueTotalValue = Skullcrusher1.getValue() + Skullcrusher2.getValue() + Skullcrusher3.getValue()
                + Kipling1.getValue() + Kipling2.getValue() + Kipling2.getValue() + Dolce.getValue();
        assertEquals(Kipling1.getTotalValue(), trueTotalValue);
    }

    @Test
    void getHeaviest() {
        Weapon Skullcrusher1 = new Weapon(10, 10, 20);
        Weapon Skullcrusher2 = new Weapon(11, 10, 20);
        Weapon Skullcrusher3 = new Weapon(12, 10, 20);
        Purse Dolce = new Purse(20, 6, 2, 30);
        Backpack Kipling1 = new Backpack(15, 5, 60);
        Kipling1.add(Skullcrusher1, Skullcrusher2, Skullcrusher3, Dolce);
        assertTrue(Kipling1.getLightestItem() == Dolce);
    }

    @Test
    void getLightest() {
        Weapon Skullcrusher1 = new Weapon(10, 10, 20);
        Weapon Skullcrusher2 = new Weapon(11, 10, 20);
        Weapon Skullcrusher3 = new Weapon(12, 10, 20);
        Purse Dolce = new Purse(20, 6, 2, 30);
        Backpack Kipling1 = new Backpack(15, 5, 60);
        Kipling1.add(Skullcrusher1, Skullcrusher2, Skullcrusher3, Dolce);
        assertTrue(Kipling1.getHeaviestItem() == Skullcrusher3);
    }

}