package main.java.com.quete_des_3_heros.inventory.armors;

import java.awt.Image;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.inventory.Inventory;
import main.java.com.quete_des_3_heros.inventory.Item;

public class Armor extends Item {
    private String type;
    private int resistance;
    private int speed;
    private int dodge;
    private int precision;

    public Armor(String _name, String _description, String _type, int _resistance, int _speed, int _dodge, int _precision, Image _sprite){
        super(_name, _description, _sprite);
        type = _type;
        resistance = _resistance;
        speed = _speed;
        dodge = _dodge;
        precision = _precision;
    }

    @Override
    public void useItem(Hero hero) {
        if (hero.getArmor() != null) {
            Inventory.getInstance().addItem(hero.getArmor());
        }
        hero.setArmor(this);
        Inventory.getInstance().deleteItem(this);
    }

    public String getType() {
        return type;
    }

    public int getResistance() {
        return resistance;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDodge() {
        return dodge;
    }

    public int getPrecision() {
        return precision;
    }

    public int[] getStats(){
        int[] stats = {resistance, speed, dodge, precision};
        return stats;
    }
}
