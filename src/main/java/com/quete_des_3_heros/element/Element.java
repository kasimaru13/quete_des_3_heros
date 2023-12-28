package main.java.com.quete_des_3_heros.element;

import main.java.com.quete_des_3_heros.view.combat_ui.Board;

import java.awt.*;

/**
 * Interface Element qui sera contenu dans le tableau de jeu.
 */
public interface Element {
    /**
     * Get the image of the element
     * @return
     */
    public Image getSprite();

    /**
     * Target get damage from the attack of the Entity
     * @param board the board with all the entities
     * @param targetX target position of x-axis on the grid
     * @param targetY target position of y-axis on the grid
     * @param damage amount of damage of the attack
     */
    public void damage(Board board, int targetX, int targetY, int damage);

    /**
     * Function hurt() that lower the health of the entity
     * @param damage amount of damage of the attack
     */
    public void hurt(int damage);
}
