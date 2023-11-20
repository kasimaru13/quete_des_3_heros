package main.java.com.quete_des_3_heros.inventory;

public class Item {
    private String name;
    private String description;

    public Item(String _name, String _description){
        name = _name;
        description = _description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
