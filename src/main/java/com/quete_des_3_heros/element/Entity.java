package main.java.com.quete_des_3_heros.element;

import main.java.com.quete_des_3_heros.element.heros.skills.Skill;
import main.java.com.quete_des_3_heros.inventory.armors.Armor;
import main.java.com.quete_des_3_heros.inventory.weapons.Weapon;
import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.combat_ui.Board;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

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
    protected double criticalRate; // attribute critical rate of the entity
    protected int movementRange; // distance of the movement, one movement point is one cell
    protected boolean alive; // entity alive or not
    protected Weapon weapon;
    protected Armor armor;

    protected String name;

    private int oldResistance = resistance;

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
     * @param criticalRate attribute critical rate of the entity
     * @param movementRange distance of the movement, one movement point is one cell
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
                  int precision,
                  double criticalRate,
                  int movementRange,
                  String name,
                  Weapon weapon,
                  Armor armor){
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
        this.criticalRate = criticalRate;
        this.movementRange = movementRange;
        this.alive = true;
        this.name = name;
        this.weapon = weapon;
        this.armor = armor;
    }

    /**
     * Returns true if attack is succesful
     * @param board
     * @param targetX
     * @param targetY
     * @return 
     */
    public int attack(Board board, int targetX, int targetY) {
        int damage = getCriticalDamage(computeAttack());
        if (getDamage(board, targetX, targetY, getCriticalDamage(damage))) {
            return damage;
        }
        else return -1;
    }

    public int getCriticalDamage(int damage){
        Random random = new Random();
        double criticalValue = random.nextDouble(); // Value between 0 and 1
        if (criticalValue < criticalRate) {
            // The damage are critical
            double multiplier = 1.5;
            return (int) (damage * multiplier);
        } else {
            // The damage are not critical
            return damage;
        }
    }

    public boolean getDamage(Board board, int targetX, int targetY, int damage){
        // Verify if coordinates of the target are valid
        if (targetX >= 0 && targetX < Constants.NUMBER_OF_SQUARES && targetY >= 0 && targetY < Constants.NUMBER_OF_SQUARES) {
            System.out.println(this.getClass().getSimpleName() + " attaque la case (" + targetX + ", " + targetY + ") !");
            Element target;
            // Verify if there is a target at the coordinates
            if((target = board.getEntity(targetX, targetY)) != null){
                ((Entity)target).setHealth(((Entity)target).getHealth() - damage);
                System.out.println("La cible " + target.getClass().getSimpleName() + " a perdu " + damage + " points de vie !");
                return true;
            }
            else {
                System.out.println("Cible ratée !");
                return false;
            }
        } else {
            System.out.println("Coordonnées de la cible invalides !");
            return false;
        }
    }

    /**
     * Computes the damage the entity can do
     */
    public int computeAttack() {
        if (weapon == null) {
            return strength;
        }
        else {
            return strength + weapon.getDamage();
        }
    }

    /**
     * Use a skill on an entity
     * @param skill
     * @return
     */
    public int useSkill(Skill skill, Board board, int x, int y){
        int damage = getCriticalDamage(skill.getAttack());
        if (getDamage(board, x, y, damage)){
            setMana(getMana() - skill.getMana_consumption());
            return damage;
        }
        return -1;
    }

    public void defend(){
        resistance += 10;
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
        if (health <= 0) {
            this.health = 0;
            this.alive = false;
        }
        else if (health <= maxHealth){
            this.health = health;
        }
        else {
            this.health = maxHealth;
        }
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
        if (mana <= 0) {
            this.mana = 0;
        }
        else if (mana <= maxMana){
            this.mana = mana;
        }
        else {
            this.mana = maxMana;
        }
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

    public double getCriticalRate() {
        return criticalRate;
    }

    public void setCriticalRate(int criticalRate) {
        this.criticalRate = criticalRate;
    }

    public int getMovementRange(){
        return movementRange;
    }

    public void setMovementRange(int movementRange){
        this.movementRange = movementRange;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void resetResistance(){
        this.resistance = oldResistance;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}

