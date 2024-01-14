package main.java.com.quete_des_3_heros.inventory;

import java.util.ArrayList;

/**
 * Inventory class, which is a singleton that contains an array of Item. Common for every characters of the player.
 */
public class Inventory {
    private ArrayList<Item> inventory;
    private static Inventory instance;

    private Inventory(){
        inventory = new ArrayList<>();
    }

    /**
     * Returns the instance of the singleton Inventory, or create one if none exist.
     */
    public static Inventory getInstance(){
        if (instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Adds the item in the inventory to the first available position
     * /!\ If full inventory, throws a RuntimeException
     * @param item Item to add to the inventory
     */
    public void addItem(Item item){
        inventory.add(item);
    }

    /**
     * Deletes the Item passed as an attribute from the inventory
     * /!\ If the Item is not in the inventory, throws a RuntimeException
     * @param item Item to delete from the inventory
     */
    public void deleteItem(Item item){
        inventory.remove(item);
    }

    /**
     * Swapt an Item from the inventory with an Item from outside the inventory.
     * Faster than using add then delete, which go through multiple items of the inventory (while loop).
     * @param item_inside_inventory Item to take out of the inventory
     * @param item_outside_inventory Item to put in the inventory
     * @return Item taken out of the inventory
     */
    public Item swapItem(Item item_inside_inventory, Item item_outside_inventory){
        inventory.add(item_outside_inventory);
        inventory.remove(item_inside_inventory);
        return item_inside_inventory;
    }

    /**
     * Deletes the Item passed as an attribute an returns it
     * @param item
     * @return
     */
    public Item takeItem(Item item){
        deleteItem(item);
        return item;
    }

    /**
     * Deletes the Item at the index passed as an attribute and returns it
     * @param index Index of Item in inventory to take out
     * @return Item taken out of inventory
     */
    public Item takeItem(int index){
        Item item = inventory.get(index);
        deleteItem(item);
        return item;
    }

    /**
     * DEBUG FUNCTION
     * Prints all items in the inventory
     */
    public void showInventory(){
        for (Item item : inventory) {
            System.out.println(item.getName());
        }
    }
}
