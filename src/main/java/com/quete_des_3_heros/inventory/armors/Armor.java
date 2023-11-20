package main.java.com.quete_des_3_heros.inventory.armors;

import main.java.com.quete_des_3_heros.inventory.Item;

public class Armor extends Item {
    private String type;
    private int resistance;
    private int speed;
    private int dodge;
    private int precision;

    public Armor(String _name, String _description, String _type, int _resistance, int _speed, int _dodge, int _precision){
        super(_name, _description);
        type = _type;
        resistance = _resistance;
        speed = _speed;
        dodge = _dodge;
        precision = _precision;
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
