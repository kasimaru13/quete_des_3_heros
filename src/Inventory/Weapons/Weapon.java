package Inventory.Weapons;

import Inventory.Item;

public class Weapon extends Item {
    private int damage;
    private int range;

    public Weapon(String _name, String _description, int _damage, int _range){
        super(_name, _description);
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
