

package main.java.com.quete_des_3_heros.element.heros;

import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.heros.skills.Skill;

public class Mage extends Hero {

    public Mage() {
        super(0,
                0,
                "/main/java/com/quete_des_3_heros/ressources/sprites/mage.png",
                50,
                50,
                150,
                150,
                5,
                30,
                10,
                5,
                13,
                30,
                0.2,
                3,
                1,
                0,
                200,
                "Mage",
                null,
                null);

        // Skills
        addSkill(new Skill("Boule de feu", 50, 3, 25));
        addSkill(new Skill("Soin", -100, 10, 25));
    }

    @Override
    public int computeAttack(Entity target) {
        int starter_attack = intelligence, target_defense = target.getResistance();
        if (weapon != null) {
            starter_attack = intelligence + weapon.getDamage();
        }
        if (target.getArmor() != null) {
            target_defense = target.getResistance() + target.getArmor().getResistance();
        }

        return Math.max(10, starter_attack - target_defense / 2);
    }
}

