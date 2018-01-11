package com.Monsters;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

import java.util.*;


/**
 * A class of Monsters involving their names, health points, protection, strength and damage values,
 * as well as the ability to engage each other in deadly combat.
 * Also available with full commit history at: https://github.com/borisofmuscovy/OOPProject
 *
 * @invar   The name of the monster has to be a valid name
 *          | isValidName(name)
 * @invar   The value of protection has to be a valid value
 *          | isValidProtection(protection)
 *
 * @version 0.3
 * @author Boris Shilov & Alicja Ochman
 */
public class Monster implements Inventorised{

    /**
     * Variable indicating Monster's name
     */
    private final String name;

    /**
     * Variable indicating Monster's strength
     */
    private int strength;

    /**
     * Variable indicating damage that can be cause by Monster
     */
    private int damage;

    /**
     * Variable indicating Monster's protection
     */
    private final int protection;

    /**
     * Variable indicating number of Monster's hitpoints
     */
    private int hp;
    /**
     * Constant indicating maximum damage
     */
    private static int MAX_DAMAGE = 20;

    /**
     * Constant indicating minimum damage
     */
    private static final int MIN_DAMAGE = 1;

    /**
     * Constant indicating maximum hitpoints
     */
    private static int MAX_HP;
    /**
     * Variable indicating if the monster is alive
     */
    private boolean Alive;

    /**
     * Constant indicating maximum protection
     */
    private static int MAX_PROTECTION  = 40;

    /**
     * Constant indicating minimum protection
     */
    private static final int MIN_PROTECTION = 1;

    /**
     * Variable indicating maximum weight of items that can be carried by a monster
     */
    private int carryingCapacity;

    /**
     * Collection of items that are in monster's possession
     */
    private Map<String,InventoryItem> inventory = new HashMap<String, InventoryItem>();

    /**
     * Anchors of a monster: left hand, right hand and back
     */
    private static ArrayList<String> anchorDefaults = new ArrayList<String>(Arrays.asList("Left", "Right", "Back"));

    /**
     * Variable indicating anchors
     */
    private final int anchors;


    /**
     * Initialise a named monster with a given number of hit points.
     *
     * @param Name
     *        The name of the monster. Must start with a capital letter, can contain lowercase, uppercase letters, numbers
     *        and the ' character.
     * @param startHP
     *        The health points value the monster starts with. This is the maximum HP of the monster unless changed explicitly.
     * @pre    The given name must be a valid name for a monster
     *         | isValidName(name)
     * @throws IllegalArgumentException
     *          The given name is not a valid name for a monster.
     *          | ! isValidName(name)
     *
     */
    public Monster(String Name, int startHP, InventoryItem... items) throws IllegalArgumentException{
        if (!isValidName(Name)){
            throw new IllegalArgumentException();
        }
        if(startHP < 0)
            throw new IllegalArgumentException("Initial value of hitpoints cannot be lower than 0");
        else
            this.hp = startHP;
        this.name = Name;
        this.damage = generateDamage();
        this.strength = (int) (new Random().nextGaussian() * 10 + 10);
        this.protection = generateProtection();
        this.MAX_HP = hp;
        this.carryingCapacity = this.getStrength() * 12;
        this.Alive = true;
        if (items.length >= 3) {
            this.anchors = items.length;
            items[0].setHolder(this);
            this.inventory.put(anchorDefaults.get(0), items[0]);
            items[2].setHolder(this);
            this.inventory.put(anchorDefaults.get(1), items[2]);
            items[1].setHolder(this);
            this.inventory.put(anchorDefaults.get(2), items[1]);
            int j = 1;
            for (int i = 3;i < this.anchors;i++){
                items[i].setHolder(this);
                this.inventory.put("Appendage" + j, items[i]);
                j++;
            }
        } else if (items.length > 0){
            this.anchors = 3;
            int emptyAnchorNo = anchors - items.length;
            items[0].setHolder(this);
            this.inventory.put(anchorDefaults.get(0), items[0]);
            int i = 1;
            for (int j = items.length - 1; j >= emptyAnchorNo;j--) {
                if (i < items.length) {
                    items[j].setHolder(this);
                    this.inventory.put(anchorDefaults.get(j), items[j]);
                    i++;
                } else {
                    this.inventory.put(anchorDefaults.get(j), null);
                }
            }
        } else {
            this.anchors = 3;
            this.inventory.put("Left", null);
            this.inventory.put("Right", null);
            this.inventory.put("Back", null);
        }
    }

    /**
     * Return the name of this monster.
     * @return  name
     *          | this.name
     */
    @Basic @Immutable
    public String getName(){
        return this.name;
    }

    /**
     * Checks validity of the monsters name
     * @param   Name
     *          Name of the monster as a string to be checked.
     * @return  True if and only if the monsters name will begin with an uppercase letter, and will contain only
     *          lower, uppercase characters, numbers and the symbol '.
     *          | result == (Name.matches("[A-Z][a-zA-Z0-9 ']{2,}"))
     */
    private static boolean isValidName(String Name) throws IllegalArgumentException{
        if (!Name.matches("[A-Z][a-zA-Z0-9 ']{2,}")) {
            throw new IllegalArgumentException("It is not a valid name for a Monster.");
        } else {
            return true;
        }
    }

    /**
     * Returns whether the monster is alive or not.
     * @return  True if alive, false if dead.
     *          | this.Alive
     */
    @Basic
    public boolean isAlive() {
        return this.Alive;
    }

    /**
     * Kills the monster by setting Alive to false.
     * @post    Monster is dead and its hitpoints equal 0
     *          | this.Alive = false
     *          | this.hp = 0
     */
    private void Death(){
        this.Alive = false;
        this.hp = 0;
    }

    /**
     * Returns the strength of the monster.
     * @return  Stength of this monster
     *          | this.strength
     */
    @Basic
    public int getStrength(){
        return this.strength;
    }

    /**
     * Sets strength of a monster ti a given new strength
     * @param   newStrength
     *          The new strength for this monster
     */
    protected void setStrength(int newStrength){
        this.strength = newStrength;
    }

    /**
     * Returns maximum damage that can be caused by a monster
     * @return Maximum value of damage
     *         | MAX_DAMAGE
     */
    protected static int getMAX_DAMAGE() {
        return MAX_DAMAGE;
    }

    /**
     * Allows setting a new maximum damage value for this monster.
     * @param   newMAX_DAMAGE
     *          The new maximum damage value to be adopted.
     * @post    If the new maximum damage is more than or equal 1, the maximum damage value will equal to the new value.
     *          | if (newMAX_DAMAGE => 1) { MAX_DAMAGE = newMAX_DAMAGE }
     */

    public static void setMAX_DAMAGE(int newMAX_DAMAGE) {
        if (newMAX_DAMAGE >= 1) {
            MAX_DAMAGE = newMAX_DAMAGE;
        }
    }

    /**
     * Returns the damage of the monster.
     * @return  damage
     *          | this.damage
     */
    @Basic
    public int getDamage(){
        return this.damage;
    }

    /**
     * Generates damage cause be monster
     * @return random number between MIN_DAMAGE and MAX_DAMAGE
     *         | nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE)
     */
    @Basic
    private int generateDamage() {
        Random randno = new Random();
        return (randno.nextInt(MAX_DAMAGE - MIN_DAMAGE + 1) + MIN_DAMAGE);
    }

    /**
     * Re-roll the damage of the monster.
     * @post The new damage value will be a random number between the MIN_DAMAGE value and the MAX_DAMAGE value.
     *       | newDamage == rand(MIN_DAMAGE:MAX_DAMAGE)
     */
    public void changeDamage() {
        this.damage = generateDamage();
    }

    /**
     * Calculates the damage dealt by a single hit using the strength and damage values, according to a set formula.
     * @return  Hit damage of a monster according to formula
     *          | damage + ((strength - 5) / 3)
     */
    private int hitDamage() {
        int momentaryStrength = ((this.getStrength() - 5) / 3);
        if (this.inventory.get("Right") instanceof Weapon) {
            Weapon right = (Weapon) this.inventory.get("Right");
            return (this.getDamage() + momentaryStrength + right.getDamage());
        } else {
            return (this.getDamage() + momentaryStrength);
        }
    }

    /**
     * Returns the protection value for this monster.
     * @return  protection
     *          | this.protection
     */
    @Basic
    public int getProtection() { return this.protection; }


    /**
     * Generates protection of a monster
     * @return  protection value to be assigned to monster
     *          | protectionFactor
     * @pre     The protection of a monster must be a prime number within range(MIN_PROTECTION, MAX_PROTECTION)
     *          | assert isValidProtection(protectionFactor)
     */
    public int generateProtection(){
        boolean flag = true;
        Random n = new Random();
        int protectionFactor = 0;
        while(flag){
            protectionFactor = (n.nextInt(MAX_PROTECTION) + 1);
            if(isPrime(protectionFactor))
                break;
        }
        assert isValidProtection(protectionFactor);
        return protectionFactor;
    }

    /**
     * Checks whether the given protection is a valid protection for a monster
     * @param   protection
     *          Protection to be checked
     * @return  True if protection is higher than MIN_PROTECTION, lower than MAX_PROTECTION and if it is a prime number
     *          |   result == (protection >= MIN_PROTECTION && protection <= MAX_PROTECTION && isPrime(protection))
     */
    public static boolean isValidProtection(int protection){
        if(protection >= MIN_PROTECTION && protection <= MAX_PROTECTION && isPrime(protection))
            return true;
        else
            return false;
    }
    /**
     * Checks if a number is a prime number.
     * @param   n
     *          The number to be checked
     * @return  True if the number is a prime number and false otherwise
     *          | result == (n%2 != 0 && n%i != 0)
     *
     */
    private static boolean isPrime(int n) {
        //check if n is a multiple of 2
        if ((n%2==0) && n!=2) {
            return false;
        }
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

    /**
     * Retrieves the current health points of the monster. If the monster is dead, throws exception.
     * @return      The integer hp if and only if the monster isAlive.
     *              | this.hp
     */
    @Basic
    public int getHP() {
        return this.hp;
    }

    /**
     * Allows to directly modify the health points of the monster.
     * @param   newHP
     *          The new HP for this monster.
     * @post    The HP of the monster will be equal to the HP provided, if it is not equal or less than 0.
     *          | if (newHP > 0) {hp == newHP}
     * @throws  IllegalArgumentException
     *          The new HP is equal to or less than 0, which means the monster is dead.
     *          | if (newHP <= 0) { Death() }
     */
    private void setHP(int newHP) throws IllegalArgumentException {
            if (newHP <= 0) {
                this.Death();
                throw new IllegalArgumentException();
            } else {
                this.hp = newHP;
            }
    }

    /**
     * Returns the maximum value of hitpoints a monster can have
     * @return  MAX_HP
     *          | this.MAX_HP
     */

    public final int getMAX_HP() { return this.MAX_HP; }

    /**
     * Sets the new maximum hitpoint value
     * @param newMAX_HP
     *        The new HP ceiling.
     * @post The new HP ceiling will always equal the provided value.
     *       | MAX_HP == newMAX_HP
     */
    public static void setMAX_HP(int newMAX_HP){
        MAX_HP = newMAX_HP;
    }


    //all of the following inventory code deals with anchors only for now, no support for looking into backpacks

    /**
     * Returns total weight of items carried by a monster
     * @return  total weight of the items currently carried by a monster
     */
    public float getTotalWeight() {
        float totalCarriedWeight = 0;
        for (Map.Entry<String,InventoryItem> entry : this.inventory.entrySet()) {
            if (entry.getValue() instanceof Backpack) {
                totalCarriedWeight += (((Backpack) entry.getValue()).getTotalWeight());
            } else if (entry.getValue() instanceof Purse) {
                totalCarriedWeight += ((Purse) entry.getValue()).getTotalWeight();
            } else if (entry.getValue() != null) {
                totalCarriedWeight += entry.getValue().getWeight();
            }
        }
        return totalCarriedWeight;
    }

    /**
     * Returns maximum weight of items that monster can carry
     * @return  carrying capacity of a monster
     *          | this.carryingCapacity
     */
    @Basic
    public int getCarryingCapacity(){
        return this.carryingCapacity;
    }

    /**
     * Returns items that are present in monster's inventory
     *
     * @return  unmodifiable contents of monster's inventory
     *          | this.inventory
     */
    @Basic
    public Map<String, InventoryItem> getContents(){
        return Collections.unmodifiableMap(this.inventory);
    }

    /**
     * Checks if the monster is physically capable of carrying the extra weight of an item.
     * @param item
     *        An item to be checked if can be held by a monster
     * @return  True is monster can carry the item, false otherwise
     */
    public boolean canContain(InventoryItem item){
        if (item == null) {
            return false;
        } else if ((this.getTotalWeight() + item.getWeight()) > this.carryingCapacity){
            return false;
        } else if ((item instanceof Inventorised) &&
                ((this.getTotalWeight() + ((Backpack) item).getTotalWeight()) > this.getCarryingCapacity())) {
            return false;
        } else if (this.inventory.containsValue(item)){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adds an item to this monster's inventory
     * @param items
     *        Items to be added
     * @pre   Monster is capable of carrying the item
     *        |this.canContain(item)
     * @pre   The item does not have a holder
     *        | item.getHolder() == null
     * @pre   Monster has to have a free anchor to add the item
     *        | this.inventory.get("Left") == null ||this.inventory.get("Right") == null
     *              || this.inventory.get("Back") == null
     * @throws IllegalStateException
     *         Throws an exception if item is too heavy, it already has a holder or none of the anchors is empty
     *         | ! this.canContain(item) || item.getHolder() != null ||
     *         (this.inventory.get("Left") != null && this.inventory.get("Right") != null
     *              && this.inventory.get("Back") != null)
     * @post    Item is added to the inventory to the empty anchor
     *          | this.inventory.put(anchor, item)
     */
    public void add(InventoryItem... items) throws IllegalStateException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if (this.canContain(item)) {
                    for (Map.Entry<String,InventoryItem> entry : this.inventory.entrySet()){
                        if (entry.getValue() == null) {
                            item.setHolder(this);
                            entry.setValue(item);
                            return;
                        }
                    }
                    for (Map.Entry<String,InventoryItem> entry : this.inventory.entrySet()) {
                        if (entry.getValue() instanceof Backpack) {
                            ((Backpack) entry.getValue()).add(item);
                            return;
                        }
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
        } catch (IllegalStateException e) {
            System.out.println(this.getName() + " cannot pick up item.");
        }
    }

    /**
     * Deletes an item from monster's inventory
     * @param item
     *        Item to be deleted from inventory
     * @pre   Monster contains the item in its inventory
     *        this.inventory.containsValue(item)
     * @post  Item is deleted from monster's inventory
     *        | entry.setValue(null)??
     * @post  Holder of an item is set to null
     *        | item.getHolder == null
     */
    private void removeItem(InventoryItem item) {
        if (this.inventory.containsValue(item)) {
            for (Map.Entry<String, InventoryItem> entry : this.inventory.entrySet()) {
                if (entry.getValue() == item) {
                    item.setHolder(null);
                    entry.setValue(null);
                }
            }
        }
    }

    /**
     * Delete multiple items from monster's inventory
     * @param   items
     *          Items to be deleted from monster's inventory
     *
     * @throws  IllegalArgumentException
     *          throws an exception if monster tris to drop a weapon
     *          | if (item instance of Weapon)
     *          |       then throw new IllegalArgumentException
     * @throws  IllegalArgumentException
     *          throws an exception if monster does not hold a weapon it tries to drop
     *          | ! this.inventory.contains(item)
     *          |       then throw new IllegalArgumentException
     */
    public void remove(InventoryItem... items) throws IllegalArgumentException{
        // drop od remove from inventory. cannot drop weapons, sets holder to null
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                if (!(item instanceof Weapon)) { //add check for ownership of item here
                    this.removeItem(item);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IllegalArgumentException e){
            System.out.println(this.getName() + " cannot drop weapons.");
        }
    }

    /**
     * Swap items at anchors
     * @param thisItem
     * @param otherItem
     * @throws IllegalAccessException
     *
     *
     *
     *
     */
    public void swap(InventoryItem thisItem, InventoryItem otherItem) throws IllegalArgumentException, IllegalAccessException {
        if ((thisItem==null) || (otherItem==null)) {
            throw new IllegalArgumentException();
        } else if ((thisItem.getIndirectHolder() != this)
                || (otherItem.getIndirectHolder() != this)) {
            throw new IllegalAccessException();
        } else if ((thisItem.getHolder() == this) && (otherItem.getHolder() == this)) {
            //fetch keys for the swapped items
            String thisItemLocation = null;
            String otherItemLocation = null;
            for (Map.Entry item : this.inventory.entrySet()) {
                if (item.getValue() == thisItem) {
                    thisItemLocation = (String) item.getKey();
                } else if (item.getValue() == otherItem) {
                    otherItemLocation = (String) item.getKey();
                }
            }
            InventoryItem tempHolder = this.inventory.get(otherItemLocation);
            this.inventory.put(otherItemLocation, thisItem);
            this.inventory.put(thisItemLocation, tempHolder);
        }
    }

    /**
     * Exchange/transfer of several items between monsters
     * @param   other
     *          Monster with which this monster trades
     * @param   items
     *          Items to be traded
     * @pre     Monster can transfer only items contained in its inventory
     *          | this.inventory.containsValue(item)
     * @pre     Monster can transfer items only if the other monster has capacity to take them
     *          | other.canContain(item)
     * @post    Items traded are deleted from this monster inventory and added to other monster inventory
     *          | this.remove(item)
     *          | entry.setValue(item);
     * @post    Holder of an item changes
     *          | item.setHolder(other)
     * @throws  IllegalAccessException
     *          Throws an exception if other monster is not able to carry the item because of its weight or other monster's inventory is full
     *          | !other.canContain(item)
     * @throws  IllegalStateException
     *          Throws an exception if monster is trying to transfer an item it does not possess
     *          | !this.inventory.containsValue(item)
     */
    public void transfer(Inventorised other, InventoryItem... items) throws IllegalArgumentException, IllegalStateException{
        try {
            for (int i=0; i < items.length;i++){
                InventoryItem item = items[i];
                //first determine if this monster trying to transfer has the item in question
                if (!this.inventory.containsValue(item)){
                    throw new IllegalStateException();
                } else if (!other.canContain(item)) {
                    throw new IllegalArgumentException();
                } else {
                    this.inventory.entrySet().remove(item);
                    item.setHolder(null);
                    other.add(item);
                }

                }
        } catch (IllegalStateException e1) {
            System.out.println(this.getName() + " tried to transfer an item they do not possess.");
        } catch (IllegalArgumentException e2) {
            System.out.print(this.getName() + " tried to transfer a weapon to " + other + ", but " +
                    "their inventory was full!");
        }
    }

    /**
     * Returns total value of this monster's inventory items
     * @return  totalValue
     */
    public float getTotalValue() {
        float totalValue = 0;
        for (Map.Entry<String,InventoryItem> entry : this.inventory.entrySet()) {
            if (entry.getValue() instanceof Backpack) {
                totalValue += (((Backpack) entry.getValue()).getTotalValue());
            } else if (entry.getValue() != null) {
                totalValue += entry.getValue().getValue();
            }
        }
        return totalValue;
    }

    /**
     * Returns total value of weapons contained in Monster's inventory
     * @return getTotalValueOfWeapons
     */
    public int getTotalValueOfWeapons(){ //for now this implementation returns value of weapons in anchors only
        int totalAnchorWeaponValue = 0;
        for (Map.Entry<String,InventoryItem> entry : this.inventory.entrySet()) {
            if (entry.getValue() instanceof Weapon) {
                totalAnchorWeaponValue += entry.getValue().getValue();
            }
        }
        return totalAnchorWeaponValue;
    }

//    public Map<String, Weapon> getAllCarriedWeapons

    /**
     * Method representing battle of the monsters.
     * @param   opponent
     *          | Opponent monster
     * @throws  IllegalStateException
     *          If the isAlive is false (monster has no HP) it cannot take part in the battle.
     *          |  if !this.isAlive()
     * @throws  IllegalArgumentException
     *          If opponent monster dies during the battle
     *          | !opponent.isAlive()
     */
    public String hit(Monster opponent) throws IllegalArgumentException, IllegalStateException{
        try {
            if (!this.isAlive()) {
                throw new IllegalStateException();
            } else {
                // D&D-like dice mechanic - generate a random number between 1-30 to try and bypass attackers protection
                Random randno = new Random();
                int diceVal = randno.nextInt(30 - 1 + 1) + 1;
                int genVal;
                if (diceVal < this.getHP()) {
                    genVal = diceVal;
                } else {
                    genVal = this.getHP();
                }
                // compare generated value to opponent protection and hit if satisfactory

                if (genVal >= opponent.getProtection()){
                    int damagedHP = opponent.getHP() - this.hitDamage();
                    opponent.setHP(damagedHP);
                    return (this.getName() + " hits " + opponent.getName() +" and does " + this.hitDamage()
                            + " damage! " + opponent.getName() + " has " + opponent.getHP() + " HP left!");
                } else {
                    return (this.getName() + " tried to hit " + opponent.getName() + ", but had no effect!");
                }
            }
        } catch (IllegalArgumentException e) {
            return (opponent.getName() + " has been slain! The mortal blow has been dealt by " + this.getName()
                    + " dealing " + this.hitDamage() + " damage!");
        } catch (IllegalStateException e) {
            return (this.getName() + " kicked the dead body of " + opponent.getName());
        }
    }
        }
