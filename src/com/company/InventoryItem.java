package com.company;
import

public abstract class InventoryItem {

    public int ID;
    public int value;
    public int weight;

    {
        value = 0;
        weight = 0;
    }

    public InventoryItem(int ID, float weight, int value) {

    }

    public setValue(int newValue) throws IllegalArgumentException{
        if (newValue < 0) {
            throw IllegalArgumentException();
        } else {
            this.value = newValue;
        }
    }
}
