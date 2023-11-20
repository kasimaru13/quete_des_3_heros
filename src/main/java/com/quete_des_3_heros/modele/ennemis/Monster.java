/*package main.java.com.votrenomdeprojet.modele.ennemis;

//import java.util.Random;

import main.java.com.votrenomdeprojet.modele.heros.Hero;


public abstract class Monster {
    private int pv;
    private int totalPV;
    private int pm;
    private int totalPM;
    private int force;
    private int intelligence;
    private int agilite;
    private int resistance;
    private int vitesse;
    private int esquive;
    private int precision;
    private String image; // Le chemin de l'image ou un identifiant

    // Constructeur
    public Monster(int pv, int totalPV, int pm, int totalPM, int force, int intelligence,
                   int agilite, int resistance, int vitesse, int esquive, int precision, String image) {
                    
        this.pv = pv;
        this.totalPV = totalPV;
        this.pm = pm;
        this.totalPM = totalPM;
        this.force = force;
        this.intelligence = intelligence;
        this.agilite = agilite;
        this.resistance = resistance;
        this.vitesse = vitesse;
        this.esquive = esquive;
        this.precision = precision;
        this.image = image;
    }

    // Getters et setters pour les attributs privés, Ces méthodes fournissent un accès contrôlé aux attributs de la classe
    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = Math.max(0, Math.min(pv, this.totalPV));
    }

    public int getTotalPV() {
        return totalPV;
    }

    public void setTotalPV(int totalPV) {
        this.totalPV = Math.max(0, totalPV);
    }

    public int getPm() {
        return pm;
    }

    public void setPm(int pm) {
        this.pm = Math.max(0, Math.min(pm, this.totalPM));
    }

    public int getTotalPM() {
        return totalPM;
    }

    public void setTotalPM(int totalPM) {
        this.totalPM = Math.max(0, totalPM);
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = Math.max(0, force);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = Math.max(0, intelligence);
    }

    public int getAgilite() {
        return agilite;
    }

    public void setAgilite(int agilite) {
        this.agilite = Math.max(0, agilite);
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = Math.max(0, resistance);
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = Math.max(0, vitesse);
    }

    public int getEsquive() {
        return esquive;
    }

    public void setEsquive(int esquive) {
        this.esquive = Math.max(0, esquive);
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = Math.max(0, precision);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//---------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------

    public void subirDegats(int degats) {
        this.pv -= degats;
        if (this.pv <= 0) {
            System.out.println("Le monstre est mort.");
        }
    }

    public void attaquer(Hero hero) {
        // Implémentez la logique pour que le monstre attaque un héros.
        // Par exemple, infliger des dégâts basés sur la force du monstre moins la défense du héros.
        int degats = this.force - hero.getResistance();
        hero.subirDegats(degats);
        System.out.println("Le monstre attaque et inflige " + degats + " de dégâts.");
    }
}*/

package main.java.com.quete_des_3_heros.modele.ennemis;

import main.java.com.quete_des_3_heros.modele.combattant;
import main.java.com.quete_des_3_heros.modele.heros.Hero;

public abstract class Monster extends combattant {

    // Constructeur de Monster
    public Monster(int pv, int totalPV, int pm, int totalPM, int force, int intelligence,
                   int agilite, int resistance, int vitesse, int esquive, int precision, String image) {
        super(pv, totalPV, pm, totalPM, force, intelligence, agilite, resistance, vitesse, esquive, precision, image);
    }

    // Méthode subirDegats (si différente de celle dans Combattant, sinon inutile de la surcharger)
    // ...

    @Override
    public void attaquer(combattant cible) {
        if (cible instanceof Hero) {
            // Votre logique d'attaque contre un Hero
            int degats = this.force - ((Hero)cible).getResistance();
            ((Hero)cible).subirDegats(degats);
            System.out.println("Le monstre attaque et inflige " + degats + " de dégâts.");
        }
        // Autres logiques d'attaque si nécessaire
    }
}
