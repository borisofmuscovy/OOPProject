package com.Monsters;

public interface Inventorised {
    public void add(InventoryItem... items);
    public void remove(InventoryItem... items);
    public void transfer(Inventorised other, InventoryItem... items);
    public float getTotalValue();
    public float getTotalWeight();
}
