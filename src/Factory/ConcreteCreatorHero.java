package Factory;

import Elements.Hero;
import Elements.Element;

public class ConcreteCreatorHero extends Creator {
    public Element createElement(){
        return new Hero();
    };
}
