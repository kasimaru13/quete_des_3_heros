package main.java.com.quete_des_3_heros.controleurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import main.java.com.quete_des_3_heros.modele.ennemis.Monster;
import main.java.com.quete_des_3_heros.modele.heros.Hero;
import main.java.com.quete_des_3_heros.modele.heros.Mage;
import main.java.com.quete_des_3_heros.modele.heros.Thief;
import main.java.com.quete_des_3_heros.modele.heros.Warrior;




import main.java.com.quete_des_3_heros.modele.combattant;


public class combatcontrolleur {
    private List<combattant> combattants;

    // Constructeur
    public combatcontrolleur(List<Hero> heros, List<Monster> monstres) {
        // Fusionner les héros et les monstres en une seule liste
        combattants = new ArrayList<>();
        combattants.addAll(heros);
        combattants.addAll(monstres);

        // Trier les combattants par leur vitesse
        Collections.sort(combattants, (c1, c2) -> c2.getVitesse() - c1.getVitesse());
        Collections.sort(combattants, (c1, c2) -> c2.getVitesse() - c1.getVitesse());

        System.out.println("Ordre des combattants après le tri par vitesse :");
        for (combattant c : combattants) {
        String typeCombattant = c instanceof Hero ? "Héros" : "Monstre";
        System.out.println(typeCombattant + " - " + c.getClass().getSimpleName() + " (Vitesse: " + c.getVitesse() + ")");
        }

    }

    // Méthode pour démarrer le combat
    public void demarrerCombat() {
        // Boucle du combat
        while (!combatTermine()) {
            for (combattant combattant : combattants) {
                executerTourCombattant(combattant);
            }
            System.out.println("\nÉtat actuel des combattants:");
            for (combattant c : combattants) {
            String type = c instanceof Hero ? "Héros" : "Monstre";
            System.out.println(type + " - " + c.getClass().getSimpleName() + " (PV: " + c.getPv() + ")");
            }
        }
    }

    // Vérifie si le combat est terminé
    private boolean combatTermine() {
        boolean tousLesHerosVaincus = combattants.stream().filter(c -> c instanceof Hero).allMatch(h -> h.getPv() <= 0);
        boolean tousLesMonstresVaincus = combattants.stream().filter(c -> c instanceof Monster).allMatch(m -> m.getPv() <= 0);
    
        return tousLesHerosVaincus || tousLesMonstresVaincus;
    }
    

    // Exécuter le tour d'un combattant
    private void executerTourCombattant(combattant combattant) {
        if (combattant instanceof Hero) {
            Hero hero = (Hero) combattant;
            
            // Demander au joueur de choisir entre attaquer ou se défendre
            System.out.println("Tour de " + hero.getClass().getSimpleName() + ":");
            System.out.println("1. Attaquer");
            System.out.println("2. Défendre");
            System.out.print("Entrez votre choix (1-2): ");
    
            int choix = lireChoixJoueur(2);
    
            if (choix == 1) {
                // Choix d'attaque
                if (hero instanceof Mage) {
                    ((Mage) hero).attaqueMagique(trouverCibleMonstre());
                } else if (hero instanceof Warrior) {
                    ((Warrior) hero).attaqueForte(trouverCibleMonstre());
                } else if (hero instanceof Thief) {
                    ((Thief) hero).attaqueRapide(trouverCibleMonstre());
                }
                // Ajouter ici d'autres conditions pour d'autres types de héros
            } else {
                // Choix de défense
                hero.defendre();
            }
    
        } else if (combattant instanceof Monster) {
            // Logique pour un monstre
            Monster monstre = (Monster) combattant;
            monstre.attaquer(trouverCibleHero());
        }
    }
    

    private Hero trouverCibleHero() {
    List<Hero> herosVivants = new ArrayList<>();
    for (combattant c : combattants) {
        if (c instanceof Hero && c.getPv() > 0) {
            herosVivants.add((Hero) c);
        }
    }

    if (!herosVivants.isEmpty()) {
        // Choisir un héros au hasard parmi les héros vivants
        Random rand = new Random();
        return herosVivants.get(rand.nextInt(herosVivants.size()));
    }

    return null; // Aucun héros vivant trouvé
    }

    private Monster trouverCibleMonstre() {
        List<Monster> monstresVivants = new ArrayList<>();
        for (combattant c : combattants) {
            if (c instanceof Monster && c.getPv() > 0) {
                monstresVivants.add((Monster) c);
            }
        }
    
        if (monstresVivants.isEmpty()) {
            System.out.println("Il n'y a plus de monstres vivants.");
            return null; // Aucun monstre vivant trouvé
        }
    
        // Affichage des monstres vivants pour que le joueur puisse choisir
        System.out.println("Choisissez un monstre à attaquer:");
        for (int i = 0; i < monstresVivants.size(); i++) {
            System.out.println(i + 1 + ". " + monstresVivants.get(i).getClass().getSimpleName() + " (PV: " + monstresVivants.get(i).getPv() + ")");
        }
    
        // Lire le choix du joueur
        int choix = lireChoixJoueur(monstresVivants.size());
        return monstresVivants.get(choix - 1);
    }
    
    private int lireChoixJoueur(int max) {
    Scanner scanner = new Scanner(System.in);
    int choix = 0;
    boolean choixValide = false;

    while (!choixValide) {
        try {
            System.out.print("Entrez votre choix (1-" + max + "): ");
            choix = scanner.nextInt();

            // Vérifier si le choix est dans la plage valide
            if (choix >= 1 && choix <= max) {
                choixValide = true;
            } else {
                System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } catch (Exception e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            scanner.nextLine(); // Pour consommer l'entrée incorrecte et éviter une boucle infinie
        }
    }
        return choix; // Retourne un choix valide
    }
}
