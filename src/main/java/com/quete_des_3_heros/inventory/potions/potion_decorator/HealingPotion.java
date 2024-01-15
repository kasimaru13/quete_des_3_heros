package main.java.com.quete_des_3_heros.inventory.potions.potion_decorator;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.inventory.potions.Potion;

public class HealingPotion extends BasePotionDecorator {
    private int health;
    
    public HealingPotion(Potion potion, int healing_amount) {
        super(potion);
        health = healing_amount;
    }

    @Override
    public void usePotion(Hero hero) {
        // Heal
        hero.setHealth(hero.getHealth() + health);

        // Do other effects of potions
        super.usePotion(hero);
    }
}
