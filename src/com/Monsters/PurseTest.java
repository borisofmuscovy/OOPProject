package com.Monsters;

import org.junit.Test;

import static org.junit.Assert.*;

public class PurseTest {

    @Test
    public void addContent() {
        Purse Gucci = new Purse(10, 10, 3, 40);
        Gucci.add(30);
        assertTrue((Gucci.getContent() == 33)
                && (Gucci.getTotalValue() == 43));
    }

    @Test
    public void removeContent() {
        Purse Gucci = new Purse(10, 10, 3, 40);
        Gucci.remove(3);
        assertTrue(Gucci.getContent() == 0);
    }

    @Test
    public void transferContent() {
        Purse Gucci = new Purse(10, 10, 3, 40);
        Purse Yves = new Purse(10, 10, 33, 40);
        Yves.transfer(Gucci, 33);
        assertTrue((Gucci.getContent() == 36)
                && (Yves.getContent() == 0));
    }

    @Test
    public void totalValue() {
        Purse Gucci = new Purse(10, 10, 3, 40);
        int Money = 30;
        Gucci.add(Money);
        assertTrue(Gucci.getTotalValue() == (Gucci.getValue() + 33));
    }

    @Test
    public void Tear(){
        Purse Gucci = new Purse(10, 10, 3, 40);
        Gucci.add(60);
        assertTrue(Gucci.isTorn());
    }

    @Test
    public void tearAdd(){
        Purse Gucci = new Purse(10, 10, 3, 40);
        Gucci.add(60);
        Gucci.add(60);
        assertEquals(Gucci.getTotalValue(), 0);
    }
}