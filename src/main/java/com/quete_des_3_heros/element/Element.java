package main.java.com.quete_des_3_heros.element;

import main.java.com.quete_des_3_heros.view.combat_ui.Board;

import java.awt.*;

/**
 * Interface Element qui sera contenu dans le tableau de jeu.
 */
public interface Element {
    public Image getSprite();
    public void damage(Board board, int targetX, int targetY);
    public void hurt(int damage);
}
