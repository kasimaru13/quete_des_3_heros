package main.java.com.quete_des_3_heros.element;

import java.awt.*;

/**
 * Interface Element qui sera contenu dans le tableau de jeu.
 */
public interface Element {
    /**
     * Get the image of the element
     * @return Image
     */
    Image getSprite();

    /**
     * Function attack() entity attack at coordinates indicated
     * @param board board of the game
     * @param targetX target position of x-axis on the grid
     * @param targetY target position of y-axis on the grid
     */
    // void attack(Board board, int targetX, int targetY);

    /**
     * Function getCriticalDamage() compare the critical rate of the entity with a double random between 0 and 1, if it's higher the entity get a critical attack
     * @param damage amount of damage
     * @return int
     */
    // int getCriticalDamage(int damage);

    /**
     * Function getDamage() target get damage from the attack of the Entity
     * @param board the board with all the entities
     * @param targetX target position of x-axis on the grid
     * @param targetY target position of y-axis on the grid
     * @param damage amount of damage of the attack
     */
    // void getDamage(Board board, int targetX, int targetY, int damage);

    public void setX(int x);
    public void setY(int y);
}
