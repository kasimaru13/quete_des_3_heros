package Inventory;

public class Inventory {
    private Item[] inventory;
    private Inventory instance;

    private Inventory(){
        inventory = new Item[16];
    }

    public Inventory getInstance(){
        if (instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    public Item[] getInventory() {
        return inventory;
    }
}
