package main.java.com.quete_des_3_heros.controller;


import java.util.*;
import java.util.List;
import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.Monster;
import main.java.com.quete_des_3_heros.element.heros.skills.Skill;
import main.java.com.quete_des_3_heros.element.Element;
import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.UI;
import main.java.com.quete_des_3_heros.view.combat_ui.CombatUI;

import static java.awt.geom.Point2D.distance;


public class CombatController {
    private CombatUI combatUI;
    private ArrayList<Entity> entitiesPriorityList;
    private Entity entityPlaying;
    private volatile boolean hasSkipped;
    private boolean isMoving;
    private boolean hasMoved;
    private boolean isAttacking;
    private boolean hasAttacked;
    private boolean hasDefended;
    private ArrayList<Hero> heroes; // List of all heroes
    private ArrayList<Monster> monsters; // List of all monsters on map
    private Skill current_skill; // skill currently in use

    /**
     * Constructs a CombatController associated with a specific CombatUI.
     *
     * @param combatUI The CombatUI instance associated with this controller.
     */
    public CombatController(CombatUI combatUI) {
        this.combatUI = combatUI;
        this.entitiesPriorityList = new ArrayList<>();
        this.heroes = new ArrayList<>();
        this.monsters = new ArrayList<>();
    }

    /**
     * Adds an entity to the game board at the specified coordinates.
     *
     * @param entity The entity to be added to the game board.
     * @param x      The X-coordinate for the entity's initial position.
     * @param y      The Y-coordinate for the entity's initial position.
     */
    public void addEntity(Entity entity, int x, int y) {
        // Add the entity to the game board at the specified coordinates
        combatUI.getBoard().addEntity(entity, x, y);
    }


    /**
     * Shows possible movements for an entity on the game board within its movement range.
     *
     * @param entity The entity for which possible movements are to be displayed.
     */
    public void showEntityMovements(Entity entity) {
        // Get the maximum movement distance for the entity
        int maxDistance = entity.getMovementRange();

        // Create a starting point for pathfinding based on the entity's current position
        PathfindingController.Point start = new PathfindingController.Point(entity.getX(), entity.getY(), null);

        // Find all paths within the movement range and set them as possible movements on the game board
        combatUI.getBoard().setPossibleMoves(PathfindingController.FindAllPaths(combatUI.getBoard().getGrid(), start, maxDistance, false));

        // Enable the move step on the game board to visualize possible movements
        combatUI.getBoard().setStep(1);
        setIsMoving(true);
        combatUI.revalidate();
        combatUI.repaint();
    }

    public void showEntityAttackRange(Entity entity, int attackRange){
        PathfindingController.Point start = new PathfindingController.Point(entity.getX(), entity.getY(), null);

        // Find all paths within the attack range and set them as possible moves on the game board
        combatUI.getBoard().setPossibleMoves(PathfindingController.FindAllPaths(combatUI.getBoard().getGrid(), start, attackRange, true));

        // Enable the attack move on the game board to visualize possible movements
        combatUI.getBoard().setStep(2);
        setIsAttacking(true);
        combatUI.revalidate();
        combatUI.repaint();
    }

    public void showEntitySkillRange(Entity entity, Skill skill){
        current_skill = skill;

        PathfindingController.Point start = new PathfindingController.Point(entity.getX(), entity.getY(), null);

        // Find all paths within the attack range and set them as possible moves on the game board
        combatUI.getBoard().setPossibleMoves(PathfindingController.FindAllPaths(combatUI.getBoard().getGrid(), start, skill.getRange(), true));

        // Enable the attack move on the game board to visualize possible movements
        combatUI.getBoard().setStep(3);
        setIsAttacking(true);
        combatUI.revalidate();
        combatUI.repaint();
    }


    /**
     * Moves an entity on a path to a target location.
     *
     * @param entity    The entity to be moved.
     * @param targetX   The target X-coordinate of the destination.
     * @param targetY   The target Y-coordinate of the destination.
     */
    public void moveOnPathEntity(Entity entity, int targetX, int targetY) {
        // Create start and end points for pathfinding
        PathfindingController.Point start = new PathfindingController.Point(entity.getX(), entity.getY(), null);
        PathfindingController.Point end = new PathfindingController.Point(targetX, targetY, null);
        List<PathfindingController.Point> path;

        // Check if the entity is a Monster
        if (entity instanceof Monster) {
            // Find the path using the Monster-specific pathfinding method
            path = PathfindingController.FindPathMonster(combatUI.getBoard().getGrid(), start, end, ((Monster) entity).getRangeAttack(), false);

            // Move the entity along the path if a valid path is found
            if (path != null) {
                int maxDistance = entity.getMovementRange();
                if (path.size() > maxDistance) {
                    // Move to the furthest reachable point within the movement range
                    combatUI.getBoard().moveEntity(entity, path.get(maxDistance).x, path.get(maxDistance).y);
                } else {
                    // Move to the last point in the path
                    combatUI.getBoard().moveEntity(entity, path.get(path.size() - 1).x, path.get(path.size() - 1).y);
                }
            }
        } else if (entity instanceof Hero) {
            combatUI.getBoard().setStep(0);

            // Move the Hero directly to the possible target location
            combatUI.getBoard().moveEntity(entity, targetX, targetY);
            hasMoved = true;
        }
        combatUI.revalidate();
        combatUI.repaint();
    }


    /**
     * Retrieves the list of entities in the combat with their priority order.
     *
     * @return An ArrayList containing entities sorted by their priority in the combat.
     */
    public ArrayList<Entity> getEntitiesPriorityList() {
        return entitiesPriorityList;
    }

    /**
     * Sets the list of entities in the combat with their priority order based on speed.
     * Dead entities are excluded from the priority list.
     */
    public void setEntitiesPriorityList() {
        // Clear the existing priority list
        entitiesPriorityList.clear();

        // Add alive heroes to the priority list
        entitiesPriorityList.addAll(heroes.stream().filter(Hero::isAlive).toList());

        // Add alive monsters to the priority list
        entitiesPriorityList.addAll(monsters.stream().filter(Monster::isAlive).toList());

        // Sort the priority list based on the entities' speed in descending order
        entitiesPriorityList.sort((entity1, entity2) -> entity2.getSpeed() - entity1.getSpeed());
    }


    public void startCombat(){
        while(heroesStillAlive() && monstersStillAlive()){
            // System.out.println("TOUR " + tour);
            for(int i = 0; i<entitiesPriorityList.size(); i++) {
                entityPlaying = entitiesPriorityList.get(i);
                if(entityPlaying instanceof Hero){
                    entityPlaying.resetResistance();
                }
                giveEntityTurn(entityPlaying);
                checkAliveState();
                combatUI.getRightPanel().hideRewindButton();
                combatUI.updatePriorityQueue(entitiesPriorityList);
                combatUI.updateProfiles(heroes);
                combatUI.revalidate();
                combatUI.repaint();

                if (!heroesStillAlive() || !monstersStillAlive()) break;
            }

            setEntitiesPriorityList();
        }
        if(heroesStillAlive()){
            // System.out.println("Vous avez gagné le combat ! Le combat a duré " + tour + " tours !");
            UI.getInstance().nextStep(Constants.PHASES + 1, "Win");
        } else if(monstersStillAlive()){
            // System.out.println("Vous avez perdu le combat ! Le combat a duré " + tour + " tours !");
            UI.getInstance().nextStep(-1, "Game Over");
        } else {
            System.err.println("Erreur !");
        }
    }

    /**
     * Checks if there are still living heroes in the combat.
     *
     * @return True if there is at least one living hero; false otherwise.
     */
    public boolean heroesStillAlive() {
        return this.heroes.stream().anyMatch(Entity::isAlive);
    }

    /**
     * Checks if there are still living monsters in the combat.
     *
     * @return True if there is at least one living monster; false otherwise.
     */
    public boolean monstersStillAlive() {
        return this.monsters.stream().anyMatch(Entity::isAlive);
    }

    private void checkAliveState() {
        Element[][] grid = combatUI.getBoard().getGrid();
        Element cur_element;

        for (int i = 0; i < Constants.NUMBER_OF_SQUARES; i++){
            for (int j = 0; j < Constants.NUMBER_OF_SQUARES; j++) {
                cur_element = grid[i][j];
                if (cur_element != null && cur_element instanceof Entity && !((Entity)cur_element).isAlive()) {
                    grid[i][j] = null;
                    entitiesPriorityList.remove(cur_element);
                }
            }
        }
    }


    /**
     * Gives the turn to a specific entity in the combat, allowing it to perform actions.
     * Depending on the entity type, it may show possible movements for heroes or move monsters towards heroes.
     *
     * @param entity The entity to receive the turn.
     */
    private void giveEntityTurn(Entity entity) {
        // Check if the entity is a hero or a monster
        if (entity instanceof Hero) {
            setHasSkipped(false);
            setIsMoving(false);
            setHasMoved(false);
            setIsAttacking(false);
            setHasAttacked(false);
            setHasDefended(false);

            boolean movesShown = false;

            // Loop until the hero has skipped
            while (!hasSkipped) {
                if(!hasMoved) {
                    if (!movesShown) { // Show the possible movements of the entity until he performed a movement
                        showEntityMovements(entity);
                        if (isMoving) {
                            movesShown = true;
                            setHasMoved(false);
                        }
                    }
                }
            }
        } else if (entity instanceof Monster) {
            // Find the closest hero and move the monster towards the target
            Hero target = targetClosestHero((Monster) entity);
            moveOnPathEntity(entity, target.getX(), target.getY());
            int distanceToTarget = (int) distance(entity.getX(), entity.getY(), target.getX(), target.getY());
            if(distanceToTarget <= ((Monster) entity).getRangeAttack()){
                entityAttackOnTarget(entity, target.getX(), target.getY());
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("Erreur dans l'attente de l'ia");
            }
        }
    }


    /**
     * Finds the closest living hero to a given monster.
     *
     * @param monster The monster for which the closest hero is to be determined.
     * @return The closest living hero to the monster, or null if no living heroes are present.
     */
    private Hero targetClosestHero(Monster monster) {
        ArrayList<Hero> heroesStillAlive = new ArrayList<>();

        // Filter living heroes from the priority list
        for (Entity e : entitiesPriorityList) {
            if (e instanceof Hero) heroesStillAlive.add((Hero) e);
        }

        Hero closestHero = null;
        int closestDistance = Integer.MAX_VALUE;

        // Iterate through living heroes to find the closest one
        for (Hero hero : heroesStillAlive) {
            int distance = (int) distance(monster.getX(), monster.getY(), hero.getX(), hero.getY());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestHero = hero;
            }
        }
        return closestHero;
    }

    public boolean entityAttackOnTarget(Entity entity, int x, int y){
        int damage = entity.attack(combatUI.getBoard(), x , y);
        if (damage >= 0){
            if(entity instanceof Hero){
                combatUI.getBoard().setStep(0);
                setIsAttacking(false);
                setHasAttacked(true);
                setHasSkipped(true);
                setHasMoved(true);
            }
            else {
                combatUI.getRightPanel().updateText(
                    "<html><p>" + entity.getName() + " a attaqué " + ((Entity)combatUI.getBoard().getEntity(x, y)).getName()
                    + " et a infligé " + damage + " dégats</p></html>"
                );
            }
            combatUI.revalidate();
            combatUI.repaint();
            return true;
        }
        if(!hasMoved()){
            showEntityMovements(entity);
        }
        return false;
    }

    public boolean entitySkillOnTarget(Entity entity, int x, int y) {
        int damage = entity.useSkill(current_skill, combatUI.getBoard(), x , y);
        if (damage != -1){
            if(entity instanceof Hero){
                combatUI.getBoard().setStep(0);
                setIsAttacking(false);
                setHasAttacked(true);
                setHasSkipped(true);
                setHasMoved(true);
            }
            else {
                combatUI.getRightPanel().updateText(
                    "<html><p>" + entity.getName() + " a utilisé " + current_skill + " sur " + ((Entity)combatUI.getBoard().getEntity(x, y)).getName()
                    + " et a infligé " + damage + " dégats</p></html>"
                );
            }
            combatUI.revalidate();
            combatUI.repaint();
            return true;
        }
        if(!hasMoved()){
            showEntityMovements(entity);
        }
        return false;
    }

    public void entityDefense(Entity entity){
        entity.defend();
        setHasDefended(true);
    }

    /**
     * Get skills of the current playing entity if it is a Hero
     * @return list of the playing hero's skills
     */
    public ArrayList<Skill> getSkills() {
        if (entityPlaying instanceof Hero) {
            return ((Hero)entityPlaying).getSkills();
        }
        else {
            return null;
        }
    }

    /**
     * Retrieves the currently playing entity in the combat.
     *
     * @return The entity currently taking its turn in the combat.
     */
    public Entity getEntityPlaying() {
        return entityPlaying;
    }

    /**
     * Sets the currently playing entity in the combat.
     *
     * @param entityPlaying The entity to be set as the currently playing entity.
     */
    public void setEntityPlaying(Entity entityPlaying) {
        this.entityPlaying = entityPlaying;
    }

    /**
     * Checks if the currently playing entity has skipped its turn.
     *
     * @return True if the entity has skipped its turn; false otherwise.
     */
    public boolean hasSkipped() {
        return hasSkipped;
    }

    /**
     * Sets whether the currently playing entity has skipped its turn.
     *
     * @param hasSkipped True if the entity has skipped its turn; false otherwise.
     */
    public void setHasSkipped(boolean hasSkipped) {
        this.hasSkipped = hasSkipped;
    }

    /**
     * Checks if the currently playing entity has moved.
     *
     * @return True if the entity has moved; false otherwise.
     */
    public boolean isMoving() {
        return isMoving;
    }

    /**
     * Sets whether the currently playing entity is moving.
     *
     * @param isMoving True if the entity is moving; false otherwise.
     */
    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }

    public boolean isAttacking(){
        return isAttacking;
    }

    public void setIsAttacking(boolean isAttacking){
        this.isAttacking = isAttacking;
    }

    public boolean hasAttacked(){
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public boolean hasDefended(){
        return hasDefended;
    }

    public void setHasDefended(boolean hasDefended){
        this.hasDefended = hasDefended;
    }

    /**
     * Retrieves the list of heroes in the combat.
     *
     * @return An ArrayList containing the heroes participating in the combat.
     */
    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    /**
     * Sets the list of heroes in the combat.
     *
     * @param heroes The ArrayList containing the heroes to be set in the combat.
     */
    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    /**
     * Retrieves the list of monsters in the combat.
     *
     * @return An ArrayList containing the monsters participating in the combat.
     */
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    /**
     * Sets the list of monsters in the combat.
     *
     * @param monsters The ArrayList containing the monsters to be set in the combat.
     */
    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

}
