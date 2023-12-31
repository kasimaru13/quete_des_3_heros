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

package main.java.com.quete_des_3_heros.modele.heros;

import java.util.Random;
import main.java.com.quete_des_3_heros.modele.combattant;
import main.java.com.quete_des_3_heros.modele.ennemis.Monster;

public class Warrior extends Hero {

    public Warrior() {
        super(100, 100, 30, 30, 20, 8, 10, 15, 8, 5, 12, "image.pnj", 1, 0, 100);
    }

    @Override
    public void attaquer(combattant cible) {
        if (cible instanceof Monster) {
            attaqueForte((Monster) cible);
        }
    }

    public void attaqueForte(Monster ennemi) {
    int chanceCritique = (this.getResistance() + this.getForce()); // Chance d'un coup critique
    int degatsBase = this.getForce() - 12; // Dégâts de base basés sur la force
    boolean estCritique = (new Random().nextInt(100) < chanceCritique); // Génère une chance de coup critique

    int degats = estCritique ? degatsBase * 2 : degatsBase; // Dégâts doublés en cas de coup critique
    ennemi.subirDegats(degats);
    if (estCritique) {
        System.out.println("Coup critique! Le warrior inflige " + degats + " points de dégâts!");
    } else {
        System.out.println("Le warrior attaque fortement et inflige " + degats + " points de dégâts!");
    }
    }
}
