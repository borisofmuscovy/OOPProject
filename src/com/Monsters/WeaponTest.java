package com.Monsters;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponTest {
    private Weapon Skullcrusher = new Weapon(5, 10, 20);

    @Test
    public void getMAX_DAMAGE() {
        assertEquals(20, Skullcrusher.getMAX_DAMAGE());
    }

    @Test
    public void getDamage() {
        assertEquals(20, Skullcrusher.getDamage());
    }

    @Test
    public void repair() {
        Weapon Bonemasher = new Weapon(5, 10, 10);
        Bonemasher.erode(3);
        Bonemasher.repair(2);
        assertEquals(9, Bonemasher.getValue());
    }

    @Test
    public void erode() {
        Weapon Bonemasher = new Weapon(5, 10, 10);
        Bonemasher.erode(3);
        assertEquals(7, Bonemasher.getValue());
    }
}