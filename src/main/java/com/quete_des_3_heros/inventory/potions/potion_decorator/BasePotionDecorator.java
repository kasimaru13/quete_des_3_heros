package main.java.com.quete_des_3_heros.inventory.potions.potion_decorator;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.inventory.potions.Potion;

public class BasePotionDecorator implements Potion {
    private Potion decoratedPotion;

    public BasePotionDecorator(Potion potion) {
        decoratedPotion = potion;
    }

    @Override
    public void usePotion(Hero hero) {
        decoratedPotion.usePotion(hero);
    }
}
