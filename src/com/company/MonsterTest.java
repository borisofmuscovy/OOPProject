package com.company;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;


class MonsterTest {

    Monster Bob = new Monster("Bob", 40);
    Monster Alice = new Monster("Alice", 40);

    @org.junit.jupiter.api.Test
    void nameExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new Monster("&", 40);});
    }

    @org.junit.jupiter.api.Test
    void isAlive() {
        assertTrue(Bob.isAlive());
    }


    @org.junit.jupiter.api.Test
    void healerTest() {
        Bob.setStrength(-30);
        int oldHealth = Alice.getHP();
        Bob.hit(Alice);
        assertTrue(Alice.getHP() > oldHealth);
    }

    @org.junit.jupiter.api.Test
    void setMAX_DAMAGE() {
        int oldDamage = Monster.getMAX_DAMAGE();
        Monster.setMAX_DAMAGE(31);
        assertTrue(Monster.getMAX_DAMAGE() != oldDamage);
    }


    @org.junit.jupiter.api.Test
    void setMAX_HP() {
        int oldHealth = Bob.getMAX_HP();
        Bob.setMAX_HP(50);
        assertTrue(Bob.getMAX_HP() != oldHealth);
    }

    @org.junit.jupiter.api.Test
    void hit() {
        int oldHealth = Alice.getHP();
        Bob.hit(Alice);
        assertTrue(Alice.getHP() <= oldHealth);
    }

}