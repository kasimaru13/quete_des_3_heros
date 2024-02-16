

package main.java.com.quete_des_3_heros.element.heros;


import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.heros.skills.Skill;

public class Warrior extends Hero {

    public Warrior() {
        super(0,
                0,
                "/main/java/com/quete_des_3_heros/ressources/sprites/warrior.png",
                200,
                200,
                50,
                50,
                30,
                5,
                10,
                15,
                10,
                15,
                0.15,
                2,
                1,
                0,
                200,
                "Guerrier",
                null,
                null);

        // Skills
        addSkill(new Skill("Ecrasement", 100, 1, 25));
    }
}
