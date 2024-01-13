package main.java.com.quete_des_3_heros.element;

import java.awt.Image;

public class Obstacle implements Element {
    private int x,y;
    private Image sprite;

    /**
     * Constructor of Obstacle inherits Element
     */
    public Obstacle(Image sprite) {
        this.sprite = sprite;
    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    @Override
    public void hurt(int damage) {
        System.out.println("C'est un obstacle, il ne prend pas de dégâts !");
    }
}
