package com.Monsters;

public interface Inventorised {
    void add(InventoryItem... items);
    void remove(InventoryItem... items);
    void transfer(Inventorised other, InventoryItem... items) throws IllegalArgumentException, IllegalStateException;
    boolean canContain(InventoryItem item);
    float getTotalValue();
    float getTotalWeight();
}
