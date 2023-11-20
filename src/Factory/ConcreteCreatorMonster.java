package Factory;

import Elements.Element;
import Elements.Monster;


/**
 * Créateur concret pour les Monstres (factory design pattern)
 */
public class ConcreteCreatorMonster extends Creator {
    public Element createElement() {
        return new Monster();
    }
}
