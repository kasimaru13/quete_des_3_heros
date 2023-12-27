package main.java.com.quete_des_3_heros.factory;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.heros.Mage;
import main.java.com.quete_des_3_heros.element.heros.Warrior;

/**
 * Créateur concret pour le Héro (factory design pattern)
 */
public class ConcreteCreatorHero extends Creator {
    public Element createElement(){
        return new Mage();
    }
}
