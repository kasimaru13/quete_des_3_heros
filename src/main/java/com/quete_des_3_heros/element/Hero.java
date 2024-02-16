package main.java.com.quete_des_3_heros.element;

import java.util.ArrayList;

import main.java.com.quete_des_3_heros.element.heros.skills.Skill;
import main.java.com.quete_des_3_heros.inventory.armors.Armor;
import main.java.com.quete_des_3_heros.inventory.weapons.Weapon;

public abstract class Hero extends Entity {

    private int level; // level of the hero
    private int xp; // experience points of the hero
    private int xpMaxLevel; // maximum experience points of the level of the hero
    private ArrayList<Skill> skills;

    /**
     * Constructor of Hero inherits Entity
     *
     * @param x             position of x-axis on the grid
     * @param y             position of y-axis on the grid
     * @param sprite        url of the sprite
     * @param health        health points of the entity
     * @param maxHealth     maximum health points of the entity
     * @param mana          mana points of the entity
     * @param maxMana       maximum mana points of the entity
     * @param strength      attribute strength of the entity
     * @param intelligence  attribute intelligence of the entity
     * @param agility       attribute agility of the entity
     * @param resistance    attribute resistance of the entity
     * @param speed         attribute speed of the entity
     * @param precision     attribute precision of the entity
     * @param criticalRate  attribute critical rate of the entity
     * @param movementRange distance of the movement, one movement point is one cell
     * @param level         level of the hero
     * @param xp            experience points of the hero
     * @param xpMaxLevel    maximum experience points of the level of the hero
     */
    public Hero(int x,
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
                int level,
                int xp,
                int xpMaxLevel,
                String name,
                Weapon weapon,
                Armor armor) {
        super(x, y, sprite, health, maxHealth, mana, maxMana, strength, intelligence, agility, resistance, speed, precision, criticalRate, movementRange, name, weapon, armor);
        this.level = level;
        this.xp = xp;
        this.xpMaxLevel = xpMaxLevel;
        skills = new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getXpMaxLevel() {
        return xpMaxLevel;
    }

    public void setXpMaxLevel(int xpMaxLevel) {
        this.xpMaxLevel = xpMaxLevel;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }
}

