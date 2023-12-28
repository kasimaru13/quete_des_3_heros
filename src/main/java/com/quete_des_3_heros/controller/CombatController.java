package main.java.com.quete_des_3_heros.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.Monster;
import main.java.com.quete_des_3_heros.view.combat_ui.Board;


import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.view.combat_ui.CombatUI;


public class CombatController {
    private CombatUI combatUI;
    private ArrayList<Entity> priorityList;

    public CombatController(CombatUI combatUI){
        this.combatUI = combatUI;
        this.priorityList = new ArrayList<>();
    }

    public void addEntity(Entity entity, int x, int y){
        combatUI.getBoard().addEntity(combatUI.getGraphics(), entity, x, y);
    }

    public void moveEntity(Entity entity, int newX, int newY){
        combatUI.getBoard().moveEntity(entity, newX, newY);
    }

    public ArrayList<Entity> getPriorityList(){
        return priorityList;
    }
    public void setPriorityList(ArrayList<Hero> heroes, ArrayList<Monster> monsters){
        priorityList.clear();
        priorityList.addAll(heroes);
        priorityList.addAll(monsters);
        priorityList.sort((c1, c2) -> c2.getSpeed() - c1.getSpeed());
        for (Entity e : priorityList) {
            String typeEntity = e instanceof Hero ? "Héros" : "Monstre";
            System.out.println(typeEntity + " - " + e.getClass().getSimpleName() + " (Vitesse: " + e.getSpeed() + ")");
        }
    }

    /*


    // Constructeur
    public CombatController(List<Hero> heros, List<Monster> monstres) {
        // Fusionner les héros et les monstres en une seule liste
        Entities = new ArrayList<>();
        Entities.addAll(heros);
        Entities.addAll(monstres);

        // Trier les combattants par leur vitesse
        Collections.sort(Entities, (c1, c2) -> c2.getVitesse() - c1.getVitesse());
        Collections.sort(Entities, (c1, c2) -> c2.getVitesse() - c1.getVitesse());

        System.out.println("Ordre des combattants après le tri par vitesse :");
        for (Entity c : Entities) {
        String typeCombattant = c instanceof Hero ? "Héros" : "Monstre";
        System.out.println(typeCombattant + " - " + c.getClass().getSimpleName() + " (Vitesse: " + c.getVitesse() + ")");
        }

    }

    // Méthode pour démarrer le combat
    public void demarrerCombat() {
        // Boucle du combat
        while (!combatTermine()) {
            for (Entity Entity : Entities) {
                executerTourCombattant(Entity);
            }
            System.out.println("\nÉtat actuel des combattants:");
            for (Entity c : Entities) {
            String type = c instanceof Hero ? "Héros" : "Monstre";
            System.out.println(type + " - " + c.getClass().getSimpleName() + " (PV: " + c.getPv() + ")");
            }
        }
    }

    // Vérifie si le combat est terminé
    private boolean combatTermine() {
        boolean tousLesHerosVaincus = Entities.stream().filter(c -> c instanceof Hero).allMatch(h -> h.getPv() <= 0);
        boolean tousLesMonstresVaincus = Entities.stream().filter(c -> c instanceof Monster).allMatch(m -> m.getPv() <= 0);
    
        return tousLesHerosVaincus || tousLesMonstresVaincus;
    }
    

    // Exécuter le tour d'un combattant
    private void executerTourCombattant(Entity Entity) {
        if (Entity instanceof Hero) {
            Hero hero = (Hero) Entity;
            
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
    
        } else if (Entity instanceof Monster) {
            // Logique pour un monstre
            Monster monstre = (Monster) Entity;
            monstre.attaquer(trouverCibleHero());
        }
    }
    

    private Hero trouverCibleHero() {
    List<Hero> herosVivants = new ArrayList<>();
    for (Entity c : Entities) {
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
        for (Entity c : Entities) {
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
    */
}
