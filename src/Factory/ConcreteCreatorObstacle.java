package Factory;

import Elements.Element;
import Elements.Obstacle;

/**
 * Créateur concret pour les Obstacles (factory design pattern)
 */
public class ConcreteCreatorObstacle extends Creator {
    public Element createElement(){
        return new Obstacle();
    }
}
