/*package main.java.com.votrenomdeprojet.modele.heros;

import java.util.Random;

import main.java.com.votrenomdeprojet.modele.ennemis.Monster;

public class Warrior extends Hero {

    public Warrior(){

        super(100, 100, 30, 30, 20, 8, 
        10, 15, 8, 5, 12, "image.pnj", 1, 0, 100);
        }

    public void attaqueForte(Monster ennemi) {
    int chanceCritique = (this.getResistance() + this.getForce()); // Chance d'un coup critique
    int degatsBase = this.getForce() - 12; // Dégâts de base basés sur la force
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


import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.heros.skills.Skill;
import main.java.com.quete_des_3_heros.view.combat_ui.Board;

public class Warrior extends Hero {

    public Warrior() {
        super(0,
                0,
                "src/main/java/com/quete_des_3_heros/ressources/sprites/warrior.png",
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

    @Override
    public boolean attack(Board board, int targetX, int targetY) {
        return getDamage(board, targetX, targetY, this.strength);
    }
}
