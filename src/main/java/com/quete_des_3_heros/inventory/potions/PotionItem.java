package main.java.com.quete_des_3_heros.inventory.potions;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.inventory.Item;

public class PotionItem extends Item {
    private Potion potion;

    public PotionItem(String _name, String _description, Potion _potion){
        super(_name, _description);

        potion = _potion;
    }

    public void usePotion(Hero hero){
        potion.usePotion(hero);
    }
}
