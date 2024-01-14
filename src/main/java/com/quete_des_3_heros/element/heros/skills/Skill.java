package main.java.com.quete_des_3_heros.element.heros.skills;

public class Skill {
    private String name;
    private int attack;
    private int range;
    private int mana_consumption;

    public Skill(String name, int attack_amount, int range, int mana_consumption) {
        this.name = name;
        attack = attack_amount;
        this.range = range;
        this.mana_consumption = mana_consumption;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getRange() {
        return range;
    }

    public int getMana_consumption() {
        return mana_consumption;
    }
}
