package main.java.com.quete_des_3_heros.inventory.weapons;

import java.awt.Image;

public class Bow extends Weapon {
    Bow(String name, String description, Image sprite, int damage, int range){
        super(name, description, damage, range, sprite);
    }
}
