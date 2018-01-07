package com.Monsters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MonsterTest {
    @Test
    void multiEquip(){
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Purse Gucci = new Purse(20, 5, 2, 30);
        Monster John = new Monster("John", 40, Skullcrusher, Gucci);
        assertTrue((John.getInventoryContents().get("Left") == Skullcrusher)
                && (John.getInventoryContents().get("Right") == Gucci));
    }

    @Test
    void oneExtraAnchor(){
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Purse Gucci = new Purse(20, 5, 2, 30);
        Backpack Kipling = new Backpack(15, 5, 60);
        Weapon Thrasher = new Weapon(3, 10, 20);
        Monster John = new Monster("John", 40, Skullcrusher, Gucci, Kipling, Thrasher);
        assertTrue((John.getInventoryContents().get("Left") == Skullcrusher)
                && (John.getInventoryContents().get("Right") == Gucci)
                && (John.getInventoryContents().get("Back") == Kipling)
                && (John.getInventoryContents().get("Appendage1") == Thrasher));
    }

    @Test
    void emptyMultiEquip(){
        Monster John = new Monster("John", 40);
    }

    @Test
    void getTotalCarriedWeightSimple() {
        Weapon Skullcrusher = new Weapon(10, 10, 20);
        Purse Gucci = new Purse(20, 5, 2, 30);
        Weapon Thrasher = new Weapon(3, 10, 20);
        Monster John = new Monster("John", 40, Skullcrusher, Gucci, Thrasher);
        float trueWeight = Skullcrusher.getWeight() + Gucci.getWeight() + Thrasher.getWeight();
        assertTrue(trueWeight == John.getTotalCarriedWeight());
    }

    @Test
    void nameExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> {new Monster("&", 40);});
    }

    @Test
    void isAlive() {
        Monster Bob = new Monster("Bob", 40);
        assertTrue(Bob.isAlive());
    }


    //@org.junit.jupiter.api.Test
    void healerTest() {
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);
        //this test will sometimes fail or run forever, depending on instantiated monster attributes
        Monster Healer;
        int oldHealth;
        while (true) {
            Healer = new Monster("Healer", 30);
            if (Healer.getStrength() < 0) {
                Bob.hit(Alice);
                if (Alice.getHP() < Alice.getMAX_HP()) {
                    oldHealth = Alice.getHP();
                    break;
                }
            }
        }
        while (Alice.getHP() <= oldHealth) {
            Healer.hit(Alice);
        }
        assertTrue(Alice.getHP() > oldHealth);
    }

    @Test
    void setMAX_DAMAGE() {
        int oldDamage = Monster.getMAX_DAMAGE();
        Monster.setMAX_DAMAGE(31);
        assertTrue(Monster.getMAX_DAMAGE() != oldDamage);
    }


    @Test
    void setMAX_HP() {
        Monster Bob = new Monster("Bob", 40);
        int oldHealth = Bob.getMAX_HP();
        Bob.setMAX_HP(50);
        assertTrue(Bob.getMAX_HP() != oldHealth);
    }

    @Test
    void hit() {
        Monster Bob = new Monster("Bob", 40);
        Monster Alice = new Monster("Alice", 40);
        int oldHealth = Alice.getHP();
        Bob.hit(Alice);
        assertTrue(Alice.getHP() <= oldHealth);
    }

    @Test
    void getTotalInventoryValue(){
        Weapon Skullcrusher1 = new Weapon(5, 10, 20);
        Weapon Skullcrusher2 = new Weapon(5, 10, 20);
        Weapon Skullcrusher3 = new Weapon(5, 10, 20);
        Purse Dolce = new Purse(20, 6, 2, 30);
        Backpack Kipling1 = new Backpack(15, 5, 60);
        Backpack Kipling2 = new Backpack(5, 5, 60);
        Monster John = new Monster("John", 40, Kipling1, Skullcrusher1, Dolce);
        Kipling2.add(Skullcrusher2, Skullcrusher3);
        John.equip(Kipling2);
        int trueTotalValue = Skullcrusher1.getValue() + Skullcrusher2.getValue() + Skullcrusher3.getValue()
                + Dolce.getValue() + Kipling1.getValue() + Kipling2.getValue();
         assertEquals(trueTotalValue, John.getTotalInventoryValue());
    }

    @Test
    void getTotalCarriedWeight(){
        Weapon Skullcrusher1 = new Weapon(5, 10, 20);
        Weapon Skullcrusher2 = new Weapon(5, 10, 20);
        Weapon Skullcrusher3 = new Weapon(5, 10, 20);
        Purse Dolce = new Purse(20, 6, 2, 30);
        Backpack Kipling1 = new Backpack(15, 5, 60);
        Backpack Kipling2 = new Backpack(5, 5, 60);
        Monster John = new Monster("John", 40, Kipling1, Skullcrusher1, Dolce);
        Kipling2.add(Skullcrusher2, Skullcrusher3);
        John.equip(Kipling2);
        float trueTotalWeight = Skullcrusher1.getWeight() + Skullcrusher2.getWeight() + Skullcrusher3.getWeight()
                + Dolce.getWeight() + Kipling1.getWeight() + Kipling2.getWeight();
        assertEquals(trueTotalWeight, John.getTotalCarriedWeight());
    }

}