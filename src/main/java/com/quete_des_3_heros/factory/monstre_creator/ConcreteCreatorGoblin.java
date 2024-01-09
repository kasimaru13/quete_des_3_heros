package main.java.com.quete_des_3_heros.factory.monstre_creator;

import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.monsters.Goblin;
import main.java.com.quete_des_3_heros.factory.Creator;


/**
 * Créateur concret pour les Monstres (factory design pattern)
 */
public class ConcreteCreatorGoblin extends Creator {
    public Element createElement() {
        return new Goblin();
    }
}
