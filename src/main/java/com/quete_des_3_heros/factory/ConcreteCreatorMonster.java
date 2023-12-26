package main.java.com.quete_des_3_heros.factory;

import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.Monster;
import main.java.com.quete_des_3_heros.element.monsters.Dragon;


/**
 * Créateur concret pour les Monstres (factory design pattern)
 */
public class ConcreteCreatorMonster extends Creator {
    public Element createElement() {
        return new Dragon();
    }
}
