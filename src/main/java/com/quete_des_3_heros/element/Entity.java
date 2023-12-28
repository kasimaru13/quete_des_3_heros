package main.java.com.quete_des_3_heros.element;

import main.java.com.quete_des_3_heros.view.combat_ui.Board;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public abstract class Entity implements Element{
    protected int x, y; // Position on the grid
    protected Image sprite; // image of the sprite
    protected int health; // health points of the entity
    protected int maxHealth; // maximum health points of the entity
    protected int mana; // mana points of the entity
    protected int maxMana; // maximum mana points of the entity
    protected int strength; // attribute strength of the entity
    protected int intelligence; // attribute intelligence of the entity
    protected int agility; // attribute agility of the entity
    protected int resistance; // attribute resistance of the entity
    protected int speed; // attribute speed of the entity
    protected int precision; // attribute precision of the entity
    protected boolean alive; // entity alive or not


    /**
     * Constructor of Entity
     * @param x position of x-axis on the grid
     * @param y position of y-axis on the grid
     * @param sprite url of the sprite
     * @param health health points of the entity
     * @param maxHealth maximum health points of the entity
     * @param mana mana points of the entity
     * @param maxMana maximum mana points of the entity
     * @param strength attribute strength of the entity
     * @param intelligence attribute intelligence of the entity
     * @param agility attribute agility of the entity
     * @param resistance attribute resistance of the entity
     * @param speed attribute speed of the entity
     * @param precision attribute precision of the entity
     */
    public Entity(int x,
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
                  int precision){
        this.x = x;
        this.y = y;
        try{
            this.sprite = ImageIO.read(new File(sprite));
        } catch (IOException e) {
            System.out.println("Erreur dans la lecture des images du jeu");
            System.exit(0);
        }
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.resistance = resistance;
        this.speed = speed;
        this.precision = precision;
        this.alive = true;
    }

    public void damage(Board board, int targetX, int targetY){
        // Vérifiez si les coordonnées de la cible sont valides
        if (targetX >= 0 && targetX < board.getWidth() && targetY >= 0 && targetY < board.getHeight()) {
            System.out.println(this.getClass().getSimpleName() + " attaque la case (" + targetX + ", " + targetY + ") !");
            Element target;
            if((target = board.getEntity(targetX, targetY)) != null){
                target.hurt(10);
            }
            else {
                System.out.println("Cible ratée !");
            }
        } else {
            System.out.println("Coordonnées de la cible invalides !");
        }
    }

    public void hurt(int damage) {
        this.health -= damage;
        if (health <= 0) {
            alive = false;
            System.out.println(this.getClass().getSimpleName() + " est mort.");
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

