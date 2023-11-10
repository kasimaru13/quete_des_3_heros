package Factory;

import Elements.Element;
import Elements.Monster;

public class ConcreteCreatorMonster extends Creator {
    public Element createElement() {
        return new Monster();
    }
}
