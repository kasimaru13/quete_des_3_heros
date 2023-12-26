package main.java.com.quete_des_3_heros.element;

public class Obstacle extends Entity {

    /**
     * Constructor of Obstacle inherits Entity
     *
     * @param x            position of x-axis on the grid
     * @param y            position of y-axis on the grid
     * @param sprite       url of the sprite
     */
    public Obstacle(int x, int y, String sprite) {
        super(x, y, sprite, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
