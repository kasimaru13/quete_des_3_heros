package main.java.com.quete_des_3_heros.element;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obstacle implements Element {
    private Image sprite;

    /**
     * Constructor of Obstacle inherits Entity
     */
    public Obstacle(int x, int y, String sprite) {
        try{
            this.sprite = ImageIO.read(new File(sprite));
        } catch (IOException e) {
            System.out.println("Erreur dans la lecture des images du jeu");
            System.exit(0);
        }
    }

    @Override
    public Image getSprite() {
        return sprite;
    }
}
