package main.java.com.quete_des_3_heros.factory;

import main.java.com.quete_des_3_heros.elements.Element;
import main.java.com.quete_des_3_heros.elements.Obstacle;

/**
 * Créateur concret pour les Obstacles (factory design pattern)
 */
public class ConcreteCreatorObstacle extends Creator {
    public Element createElement(){
        return new Obstacle();
    }
}
