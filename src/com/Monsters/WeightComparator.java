package com.Monsters;

import java.util.Comparator;

/**
 * Class implementing comparison of InventoryItem weights
 */
class WeightComparator implements Comparator<InventoryItem> {
    /**
     * Compares weight of inventory items
     * @param   item1
     *          Item to be compared
     * @param   item2
     *          Item to be compared
     * @return  Returns which item is heavier or if they have same weight, which have higher ID value
     */
    @Override
    public int compare(InventoryItem item1, InventoryItem item2) {
        //if items being compared have the same weight, the one with the higher ID will be considered bigger
        if (item1.getWeight() == item2.getWeight()) {
            return (Long.compare(item1.getID(), item2.getID()));
        } else if (item1.getWeight() > item2.getWeight()) {
            return 1;
        } else {
            return -1;
        }
    }
}