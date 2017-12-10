package com.company;

public abstract class InventoryItem {

    public int ID;
    public int value;
    public float weight;

    {
        value = 0;
        weight = 0;
    }

    public InventoryItem(int ID, float weight, int value) throws IllegalArgumentException {
        this.value = value;
        this.weight = setWeaponWeight(weight);

    }

    public void setValue(int newValue) throws IllegalArgumentException{
        if (newValue < 0) {
            throw new IllegalArgumentException();
        } else {
            this.value = newValue;
        }
    }

    public float setWeaponWeight(float weight) throws IllegalArgumentException{
        if(weight <0)
            throw new IllegalArgumentException();
        else
            return weight;
    }
}
