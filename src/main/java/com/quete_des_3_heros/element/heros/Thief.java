/*package main.java.com.votrenomdeprojet.modele.heros;

import java.util.Random;
import main.java.com.votrenomdeprojet.modele.ennemis.Monster;

// Thief hérite de Hero
public class Thief extends Hero {

    private int furtivite = 50; // Niveau de furtivité spécifique au voleur


    // Constructeur de Thief
    public Thief() {
        super(100, 100, 30, 30, 15, 20, 40, 10,
         40, 50, 90, "image.pnj", 1, 0, 100);
    }

    public void attaqueRapide(Monster ennemi) {
    int chanceCritique = (this.getAgilite() + this.furtivite) / 3; // Chance d'un coup critique
    int degatsBase = this.getForce(); // Dégâts de base basés sur la force
    boolean estCritique = (new Random().nextInt(100) < chanceCritique); // Génère une chance de coup critique

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
