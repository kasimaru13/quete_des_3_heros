package main.java.com.quete_des_3_heros.element;

public class Obstacle extends Entity {

    /**
     * Constructor of Obstacle inherits Entity
     *
     * @param x            position of x-axis on the grid
     * @param y            position of y-axis on the grid
     * @param sprite       url of the sprite
     * @param health       health points of the entity
     * @param maxHealth    maximum health points of the entity
     * @param mana         mana points of the entity
     * @param maxMana      maximum mana points of the entity
     * @param strength     attribute strength of the entity
     * @param intelligence attribute intelligence of the entity
     * @param agility      attribute agility of the entity
     * @param resistance   attribute resistance of the entity
     * @param speed        attribute speed of the entity
     * @param precision    attribute precision of the entity
     */
    public Obstacle(int x, int y, String sprite, int health, int maxHealth, int mana, int maxMana, int strength, int intelligence, int agility, int resistance, int speed, int precision) {
        super(x, y, sprite, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
