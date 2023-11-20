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

package main.java.com.votrenomdeprojet.modele.heros;

import main.java.com.votrenomdeprojet.modele.combattant;
import main.java.com.votrenomdeprojet.modele.ennemis.Monster;
import java.util.Random;

public class Mage extends Hero {

    public Mage() {
        super(100, 100, 50, 50, 10, 30, 20, 15, 25, 10, 80, "mage.png", 1, 0, 100);
    }

    @Override
    public void attaquer(combattant cible) {
        if (cible instanceof Monster) {
            attaqueMagique((Monster) cible);
        }
    }

    public void attaqueMagique(Monster ennemi) {
    int chanceAttaqueMagique = (this.getAgilite() + this.getIntelligence()) / 2; // Chance d'un coup critique
    int degatsBase = this.getForce(); // Dégâts de base basés sur la force
    boolean estCritique = (new Random().nextInt(100) < chanceAttaqueMagique); // Génère une chance de coup critique

    int degats = estCritique ? degatsBase * 2 : degatsBase; // Dégâts doublés en cas de coup critique
    ennemi.subirDegats(degats);
    if (estCritique) {
        System.out.println("Coup critique! Le mage inflige " + degats + " points de dégâts!");
    } else {
        System.out.println("Le mage attaque avec ineligence et inflige " + degats + " points de dégâts!");
    }
    }
}

