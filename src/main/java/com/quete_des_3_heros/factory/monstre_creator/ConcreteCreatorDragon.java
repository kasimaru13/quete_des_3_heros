package main.java.com.quete_des_3_heros.factory.monstre_creator;

import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.monsters.Dragon;
import main.java.com.quete_des_3_heros.factory.Creator;


/**
 * Créateur concret pour les Monstres (factory design pattern)
 */
public class ConcreteCreatorDragon extends Creator {
    public Element createElement() {
        return new Dragon();
    }
}
