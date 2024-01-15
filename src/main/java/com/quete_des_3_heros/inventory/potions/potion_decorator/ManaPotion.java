package main.java.com.quete_des_3_heros.inventory.potions.potion_decorator;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.inventory.potions.Potion;

public class ManaPotion extends BasePotionDecorator {
    private int mana;
    
    public ManaPotion(Potion potion, int mana_amount) {
        super(potion);
        mana = mana_amount;
    }

    @Override
    public void usePotion(Hero hero) {
        // Mana
        hero.setMana(hero.getMana() + mana);

        // Other effects of potion
        super.usePotion(hero);
    }
}
