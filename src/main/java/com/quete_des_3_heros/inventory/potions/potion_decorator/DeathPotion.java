package main.java.com.quete_des_3_heros.inventory.potions.potion_decorator;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.inventory.potions.Potion;

public class DeathPotion extends BasePotionDecorator {
    
    public DeathPotion(Potion potion) {
        super(potion);
    }

    @Override
    public void usePotion(Hero hero) {
        hero.setHealth(0);

        super.usePotion(hero);
    }
}
