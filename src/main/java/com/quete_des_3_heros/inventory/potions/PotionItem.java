package main.java.com.quete_des_3_heros.inventory.potions;

import java.awt.Image;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.inventory.Item;

public class PotionItem extends Item {
    private Potion potion;

    public PotionItem(String _name, String _description, Image _sprite, Potion _potion){
        super(_name, _description, _sprite);

        potion = _potion;
    }

    @Override
    public void useItem(Hero hero){
        potion.usePotion(hero);
    }
}
