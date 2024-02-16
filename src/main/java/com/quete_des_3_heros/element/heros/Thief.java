

package main.java.com.quete_des_3_heros.element.heros;

import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.heros.skills.Skill;

public class Thief extends Hero {
    // Constructeur de Thief
    public Thief() {
        super(0,
                0,
                "/main/java/com/quete_des_3_heros/ressources/sprites/rogue.png",
                100,
                100,
                100,
                100,
                10,
                5,
                30,
                10,
                15,
                30,
                0.3,
                4,
                1,
                0,
                200,
                "Voleur",
                null,
                null);

        // Skills
        addSkill(new Skill("Tir à l'arc", 20, 5, 10));
    }

    public int computeAttack(Entity target) {
        int starter_attack = agility, target_defense = target.getResistance();
        if (weapon != null) {
            starter_attack = agility + weapon.getDamage();
        }
        if (target.getArmor() != null) {
            target_defense = target.getResistance() + target.getArmor().getResistance();
        }

        return Math.max(10, starter_attack - target_defense / 2);
    }
}
