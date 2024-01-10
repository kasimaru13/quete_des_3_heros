package main.java.com.quete_des_3_heros.inventory.weapons;

import java.awt.Image;

public class Sword extends Weapon {
    public Sword(String name, String description, Image sprite, int damage, int range){
        super(name, description, damage, range, sprite);
    }
}
