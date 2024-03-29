package main.java.com.quete_des_3_heros.inventory;

import java.awt.Image;

import main.java.com.quete_des_3_heros.element.Hero;

/**
 * Item class. Children classes are the one used in the game, so this is just a model for them, to put them in an array
 * (Inventory class)
 */
public abstract class Item {
    private String name;
    private String description;
    private Image sprite;

    public Item(String _name, String _description, Image _sprite){
        name = _name;
        description = _description;
        sprite = _sprite;
    }

    public abstract void useItem(Hero hero);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Image getSprite() {
        return sprite;
    }
}
