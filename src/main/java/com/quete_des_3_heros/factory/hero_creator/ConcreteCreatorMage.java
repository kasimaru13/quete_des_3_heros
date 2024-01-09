package main.java.com.quete_des_3_heros.factory.hero_creator;

import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.heros.Mage;
import main.java.com.quete_des_3_heros.factory.Creator;

/**
 * Créateur concret pour le Héro (factory design pattern)
 */
public class ConcreteCreatorMage extends Creator {
    public Element createElement(){
        return new Mage();
    }
}
