package main.java.com.quete_des_3_heros.controleurs;

import main.java.com.quete_des_3_heros.modele.ennemis.*;
import main.java.com.quete_des_3_heros.modele.heros.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Créer des héros
        Hero mage = new Mage();
        Hero warrior = new Warrior();
        Hero thief = new Thief();

        // Créer des monstres
        Monster dragon = new Dragon();
        Monster goblin = new Goblin();
        Monster skeleton = new Skeleton();

        // Créer le contrôleur de combat
        combatcontrolleur controlleur = new combatcontrolleur(
                Arrays.asList(mage, warrior, thief), 
                Arrays.asList(dragon, goblin, skeleton));

        // Démarrer le combat
        controlleur.demarrerCombat();
    }
}