package Factory;

import Elements.Element;
import Elements.Obstacle;

public class ConcreteCreatorObstacle extends Creator {
    public Element createElement(){
        return new Obstacle();
    }
}
