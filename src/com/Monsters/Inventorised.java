package com.Monsters;

/**
 * An interface implementing methods to be used by Objects possessing the inventory, i.e. Backpacks and Monsters
 *
 * @version 1.0
 * @author Boris Shilov & Alicja Ochman
 */
interface Inventorised {
    /**
     * Add multiple inventory items to possessions
     * @param   items
     *          Items to be added to inventory
     */
    void add(InventoryItem... items);

    /**
     * Remove multiple items from possessions
     * @param   items
     *          Items to be removed
     */
    void remove(InventoryItem... items);

    /**
     * Transfer multiple items from inventory of one Object to the inventory of other Object
     * @param   other
     *          Object to which inventory items transferred
     * @param   items
     *          Items to be transferred
     * @throws IllegalArgumentException
     *          Throws an exception if after the transfer of the item the total
     *          capacity of other Object would be exceeded
     *          | ! canContain(item)
     * @throws IllegalStateException
     *          Throws an exception if the item to be transferred is not in possession of this Object
     *
     */
    void transfer(Inventorised other, InventoryItem... items) throws IllegalArgumentException, IllegalStateException;

    /**
     * Checks whether the item can be added to the Object's inventory
     * @param   item
     *          Item to be checked
     * @return  true if weight of the Inventory with new item does not exceed Object's capacity, false otherwise
     *          |   result == (this.getContentsWeight() + item.getWeight()) > this.getCapacity())
     */
    boolean canContain(InventoryItem item);

    /**
     *  Returns total value of items contained in inventory
     * @return  totalValue
     */
    float getTotalValue();

    /**
     *  Returns total weight of items contained in inventory
     * @return  totalWeight
     */
    float getTotalWeight();
}
