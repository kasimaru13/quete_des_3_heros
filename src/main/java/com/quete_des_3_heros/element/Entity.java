package main.java.com.quete_des_3_heros.element;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public abstract class Entity implements Element{
    private int x, y; // Position on the grid
    private Image sprite; // image of the sprite
    private int health; // health points of the entity
    private int maxHealth; // maximum health points of the entity
    private int mana; // mana points of the entity
    private int maxMana; // maximum mana points of the entity
    private int strength; // attribute strength of the entity
    private int intelligence; // attribute intelligence of the entity
    private int agility; // attribute agility of the entity
    private int resistance; // attribute resistance of the entity
    private int speed; // attribute speed of the entity
    private int precision; // attribute precision of the entity

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
}

