package Inventory;

public class Inventory {
    private int inventorySize;
    private Item[] inventory;
    private Inventory instance;

    private Inventory(){
        inventorySize = 16;
        inventory = new Item[inventorySize];
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

    /**
     * Ajoute un Item dans l'inventaire à la première position libre.
     * /!\ Si l'inventaire est plein, créé une exception RuntimeException.
     * @param item - l'item à ajouter à l'inventaire
     */
    public void addItem(Item item){
        int i = 0;
        while (i < inventorySize && inventory[i] != null){
            i++;
        }
        if (i == inventorySize){
            throw new RuntimeException();
        }
        else{
            inventory[i] = item;
        }
    }

    /**
     * Supprime de l'inventaire l'Item passé en paramètre.
     * /!\ Si l'Item ne se trouve pas dans l'inventaire, lance une RuntimeException
     * @param item L'Item à supprimer de l'inventaire
     */
    public void deleteItem(Item item){
        int i = 0;
        while (i < inventorySize && inventory[i] != item){
            i++;
        }
        if (i == inventorySize){
            throw new RuntimeException();
        }
        else{
            inventory[i] = null;
            sortInventory();
        }
    }

    /**
     * Supprime l'Item passé en paramètre et le renvoie.
     * @param item
     * @return
     */
    public Item takeItem(Item item){
        deleteItem(item);
        return item;
    }

    /**
     * Supprime l'Item se trouvant à l'index passé en paramètre et le renvoie
     * @param index Index de l'Item à prendre de l'inventaire
     * @return L'Item qui a été supprimé de l'inventaire
     */
    public Item takeItem(int index){
        Item item = inventory[index];
        deleteItem(item);
        return item;
    }

    /**
     * Trie l'inventaire de façon à ce qu'il n'y ait aucun élément null entre deux Item
     */
    private void sortInventory(){
        int i = 0;
        int j = 0;

        while (inventory[i] != null){
            i++;
        }
        while(j < inventorySize){
            j = i + 1;
            while (inventory[j] == null){
                j++;
            }
            if (j != inventorySize){
                inventory[i] = inventory[j];
                inventory[j] = null;
                i++;
            }
        }
    }
}
