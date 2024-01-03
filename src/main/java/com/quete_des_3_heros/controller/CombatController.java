package main.java.com.quete_des_3_heros.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.Monster;
import main.java.com.quete_des_3_heros.view.combat_ui.Board;


import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.view.combat_ui.CombatUI;

import static java.awt.geom.Point2D.distance;


public class CombatController {
    private CombatUI combatUI;
    private ArrayList<Entity> entitiesPriorityList;
    private ArrayList<Hero> heroes;
    private ArrayList<Monster> monsters;

    public CombatController(CombatUI combatUI){
        this.combatUI = combatUI;
        this.entitiesPriorityList = new ArrayList<>();
        this.heroes = new ArrayList<>();
        this.monsters = new ArrayList<>();
    }

    public void addEntity(Entity entity, int x, int y){
        combatUI.getBoard().addEntity(entity, x, y);
    }

    public void moveEntity(Entity entity, int newX, int newY){
        PathfindingController.Point start = new PathfindingController.Point(entity.getX(), entity.getY(), null);
        PathfindingController.Point end = new PathfindingController.Point(newX, newY, null);
        List<PathfindingController.Point> path = PathfindingController.FindPath(combatUI.getBoard().getGrid(), start, end);
        if (path != null) {
            for(int i = 0; i < entity.getMovementRange(); i++){ // A revoir
                if(path.get(i) == null) return;
                combatUI.getBoard().moveEntity(entity, path.get(i).x, path.get(i).y);
            }
        }
    }

    public ArrayList<Entity> getEntitiesPriorityList(){
        return entitiesPriorityList;
    }
    public void setEntitiesPriorityList(){
        entitiesPriorityList.clear();
        entitiesPriorityList.addAll(heroes.stream().filter(Hero::isAlive).toList());
        entitiesPriorityList.addAll(monsters.stream().filter(Monster::isAlive).toList());

        // Sorting by the speed of the entities
        entitiesPriorityList.sort((c1, c2) -> c2.getSpeed() - c1.getSpeed());

        for (Entity e : entitiesPriorityList) {
            String typeEntity = e instanceof Hero ? "Héros" : "Monstre";
            System.out.println(typeEntity + " - " + e.getClass().getSimpleName() + " (Vitesse: " + e.getSpeed() + ")");
        }

        combatUI.updatePriorityQueue(entitiesPriorityList);
    }

    public void startCombat(){
        int tour = 1;

        while(heroesStillAlive() && monstersStillAlive()){
            System.out.println("TOUR " + tour);
            for(int i = 0; i<4; i++)  giveEntityTurn(entitiesPriorityList.get(0));

            tour += 1;

            setEntitiesPriorityList();
            return;
        }
        if(heroesStillAlive()){
            System.out.println("Vous avez gagné le combat ! Le combat a duré " + tour + " tours !");
        } else if(monstersStillAlive()){
            System.out.println("Vous avez perdu le combat ! Le combat a duré " + tour + " tours !");
        } else {
            System.out.println("Erreur !");
        }
    }

    public boolean heroesStillAlive(){
        return this.heroes.stream().anyMatch(Entity::isAlive);
    }

    public boolean monstersStillAlive(){
        return this.monsters.stream().anyMatch(Entity::isAlive);
    }

    private void giveEntityTurn(Entity entity){
        if(entity instanceof Hero){
            return;
        } else if (entity instanceof Monster){
            Hero target = targetClosestHero((Monster) entity);
            moveEntity(entity, target.getX(), target.getY());

        }
    }

    private Hero targetClosestHero(Monster monster){
        ArrayList<Hero> heroesStillAlive = new ArrayList<>();
        for(Entity e : entitiesPriorityList){
            if(e instanceof Hero) heroesStillAlive.add((Hero) e);
        }
        Hero closestHero = null;
        int closestDistance = Integer.MAX_VALUE;

        for(Hero hero : heroesStillAlive){
            int distance = (int) distance(monster.getX(), monster.getY(), hero.getX(), hero.getY());
            if(distance < closestDistance){
                closestDistance = distance;
                closestHero = hero;
            }
        }
        return closestHero;
    }

    /*
    // Exécuter le tour d'un combattant
    private void executerTourCombattant(Entity Entity) {

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

    public ArrayList<Hero> getHeroes(){
        return heroes;
    }
    public void setHeroes(ArrayList<Hero> heroes){
        this.heroes = heroes;
    }

    public ArrayList<Monster> getMonsters(){
        return monsters;
    }
    public void setMonsters(ArrayList<Monster> monsters){
        this.monsters = monsters;
    }
}
