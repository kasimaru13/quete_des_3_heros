package Factory;

import Elements.Hero;
import Elements.Element;

/**
 * Créateur concret pour le Héro (factory design pattern)
 */
public class ConcreteCreatorHero extends Creator {
    public Element createElement(){
        return new Hero();
    }
}
