package main.java.com.quete_des_3_heros.element;

import main.java.com.quete_des_3_heros.inventory.armors.Armor;
import main.java.com.quete_des_3_heros.inventory.weapons.Weapon;

public abstract class Monster extends Entity {
    private int rangeAttack; // distance range of the base attack of the entity
    /**
     * Constructor of Monster inherits Entity
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
     * @param criticalRate attribute critical rate of the entity
     * @param movementRange distance of the movement, one movement point is one cell
     * @param rangeAttack distance range of the base attack of the entity
     */
    public Monster(int x,
                   int y,
                   String sprite,
                   int health,
                   int maxHealth,
                   int mana,
                   int maxMana,
                   int strength,
                   int intelligence,
                   int agility,
                   int resistance,
                   int speed,
                   int precision,
                   double criticalRate,
                   int movementRange,
                   int rangeAttack,
                   String name,
                   Weapon weapon,
                   Armor armor) {
        super(x, y, sprite, health, maxHealth, mana, maxMana, strength, intelligence, agility, resistance, speed, precision, criticalRate, movementRange, name, weapon, armor);
        this.rangeAttack = rangeAttack;
    }

    public int getRangeAttack() {
        return rangeAttack;
    }

    public void setRangeAttack(int rangeAttack) {
        this.rangeAttack = rangeAttack;
    }
}
