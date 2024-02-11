/*package main.java.com.votrenomdeprojet.modele.heros;


import main.java.com.votrenomdeprojet.modele.ennemis.Monster;

import java.util.Random;


public class Mage extends Hero {

    public Mage() {
        super(100, 100, 50, 50, 10, 30, 20, 15,
         25, 10, 80, "mage.png", 1, 0, 100);
    }

    public void attaqueMagique(Monster ennemi) {
    int chanceAttaqueMagique = (this.getAgilite() + this.getIntelligence()) / 2; // Chance d'un coup critique
    int degatsBase = this.getForce(); // Dégâts de base basés sur la force
    boolean estCritique = (new Random().nextInt(100) < chanceAttaqueMagique); // Génère une chance de coup critique

    int degats = estCritique ? degatsBase * 2 : degatsBase; // Dégâts doublés en cas de coup critique
    ennemi.subirDegats(degats);
    if (estCritique) {
        System.out.println("Coup critique! Le voleur inflige " + degats + " points de dégâts!");
    } else {
        System.out.println("Le voleur attaque rapidement et inflige " + degats + " points de dégâts!");
    }
    }

}*/

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

