package main.java.com.quete_des_3_heros.inventory.weapons;

import java.awt.Image;

import main.java.com.quete_des_3_heros.inventory.Item;

/**
 * Weapon class, children classes are the ones used in the game, so this is just a model for them, to group them in a 
 * category of items.
 */
public class Weapon extends Item {
    private int damage;
    private int range;

    public Weapon(String _name, String _description, int _damage, int _range, Image _sprite){
        super(_name, _description, _sprite);
        damage = _damage;
        range = _range;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }
}
