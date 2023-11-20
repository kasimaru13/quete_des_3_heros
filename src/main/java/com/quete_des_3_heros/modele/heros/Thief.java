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

package main.java.com.votrenomdeprojet.modele.heros;

import main.java.com.votrenomdeprojet.modele.combattant;
import main.java.com.votrenomdeprojet.modele.ennemis.Monster;
import java.util.Random;

public class Thief extends Hero {
    private int furtivite; // Niveau de furtivité spécifique au voleur

    // Constructeur de Thief
    public Thief() {
        super(100, 100, 30, 30, 15, 20, 40, 10, 40, 50, 90, "image.pnj", 1, 0, 100);
        this.furtivite = 50;
    }

    @Override
    public void attaquer(combattant cible) {
        if (cible instanceof Monster) {
            attaqueRapide((Monster) cible);
        }
        // Autres logiques d'attaque si nécessaire
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
}
