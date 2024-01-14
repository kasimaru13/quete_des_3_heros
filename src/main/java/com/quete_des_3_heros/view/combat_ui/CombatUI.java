package main.java.com.quete_des_3_heros.view.combat_ui;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import main.java.com.quete_des_3_heros.controller.CombatController;
import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.Monster;
import main.java.com.quete_des_3_heros.inventory.Item;
import main.java.com.quete_des_3_heros.inventory.potions.PotionItem;
import main.java.com.quete_des_3_heros.inventory.potions.potion_decorator.ConcretePotion;
import main.java.com.quete_des_3_heros.inventory.potions.potion_decorator.HealingPotion;
import main.java.com.quete_des_3_heros.inventory.potions.potion_decorator.ManaPotion;
import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.UI;
import main.java.com.quete_des_3_heros.view.combat_ui.zones.Zone;
import main.java.com.quete_des_3_heros.view.components.InventoryButton;
import main.java.com.quete_des_3_heros.view.components.Profile;


/**
 * Panel for the combat phase. Contains two sides panels displaying information for the player, and in the center of the
 * panel the board.
 */
public class CombatUI extends JPanel implements ActionListener, MouseListener {
    private Board board;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;


    private CombatController combatController;

    Zone current_zone;

    private List<Profile> profile_queue;

    public CombatUI(int phase_number){
        combatController = new CombatController(this);
        
        current_zone = new Zone(phase_number);

        // Add the entities in their own list and add all the entities in the priority list
        initListEntities();

        // Initiate CombatUI preferences
        initCombatUI();

        SwingUtilities.invokeLater(this::startCombat);
    }

    /**
     * En utilisant un SwingWorker, la méthode startCombat() sera exécutée dans un thread distinct,
     * ce qui permettra à l'interface utilisateur de rester réactive pendant le combat.
     * Vous pouvez également utiliser la méthode done() du SwingWorker pour effectuer des opérations
     * supplémentaires après la fin du combat, comme la mise à jour de l'interface utilisateur.
     */
    private void startCombat() {
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Exécutez la logique de combat dans ce thread
                combatController.startCombat();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("fin du thread");
                // Mettez à jour l'UI ou effectuez d'autres opérations après la fin du combat ici
            }
        };

        swingWorker.execute();
    }

    /**
     * Initiate Combat Interface
     */
    private void initCombatUI(){
        setBackground(Color.blue);
        setFocusable(true);

        // Initialise different panels
        board = new Board(current_zone);
        leftPanel = new LeftPanel();
        rightPanel = new RightPanel(combatController.getHeroes());

        // ActionListner on RightPanel's Buttons
        rightPanel.getAttackButton().addActionListener(this);
        rightPanel.getDefendButton().addActionListener(this);
        rightPanel.getSkillButton().addActionListener(this);
        rightPanel.getItemButton().addActionListener(this);
        rightPanel.getRewind_button().addActionListener(this);

        // Board mouse click listener
        board.addMouseListener(this);

        // Set size of the panels
        setLayout(null);
        leftPanel.setBounds(0, 0, Constants.LEFTPANEL_WIDTH, Constants.WINDOW_HEIGHT);
        board.setBounds(280, 0, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
        rightPanel.setBounds(280 + Constants.BOARD_SIZE, 0, Constants.RIGHTPANEL_WIDTH, Constants.WINDOW_HEIGHT);

        profile_queue = new ArrayList<Profile>();
        updatePriorityQueue(combatController.getEntitiesPriorityList());

        // Add the panels to the UI
        add(leftPanel);
        add(board);
        add(rightPanel);
        setVisible(true);
    }

    /**
     * Initiate ArrayList of all entities
     */
    private void initListEntities(){
        // ArrayList of Heroes
        for (Hero hero : UI.getInstance().getHeroes()) {
            combatController.getHeroes().add(hero);
        }

        // ArrayList of Monsters
        for (Monster monster : current_zone.getMonsters()) {
            combatController.getMonsters().add(monster);
        }

        // ArrayList of all Entities for the Priority Order
        combatController.setEntitiesPriorityList();
    }

    /**
     * Update Combat Textual Interface for the moment
     */
    public void updateCombatUI() {
        for (int i = 0; i < Constants.NUMBER_OF_SQUARES; i++) {
            for (int j = 0; j < Constants.NUMBER_OF_SQUARES; j++) {
                if (board.getEntity(i, j) != null) {
                    System.out.print(" " + board.getEntity(i, j).getClass().getSimpleName().charAt(0) + " ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Shows skill buttons on UI (and sets action listeners on them)
     * @param skillsName List of skills as strings
     */
    private void showSkills(ArrayList<String> skillsName) {
        rightPanel.skillButtonsToPanel(skillsName);

        for (int i = 0; i < rightPanel.getAlternativeButtons().size(); i++) {
            rightPanel.getAlternativeButtons().get(i).addActionListener(this);
        }
    }

    /**
     * Shows skill buttons on UI (and sets action listeners on them)
     * @param items Inventory as a list of items
     */
    private void showInventory(ArrayList<Item> items) {
        rightPanel.itemsButtonsToPanel(items);

        for (int i = 0; i < rightPanel.getAlternativeButtons().size(); i++) {
            rightPanel.getAlternativeButtons().get(i).addActionListener(this);
        }
    }

    /**
     * Update priority queue profiles of leftpanel
     * @param entities Entity list in order of play
     */
    public void updatePriorityQueue(ArrayList<Entity> entities){
        if (!entities.isEmpty() && profile_queue != null && leftPanel != null){
            for (Entity entity : entities){
                profile_queue.add(new Profile(entity.getName(), entity.getHealth(), entity.getMana(), entity.getSprite()));
            }

            leftPanel.setPriority_queue(profile_queue);
        }
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public void updateProfiles(ArrayList<Hero> heroes){
        rightPanel.updateProfiles(heroes);
    }



    

    // Buttons listener -------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        // Actions when pressing right panel's buttons

        // Attack
        if (e.getSource() == rightPanel.getAttackButton()){
            if(!combatController.hasAttacked()) combatController.showEntityAttackRange(combatController.getEntityPlaying(), 2);
        }

        // Defend
        else if (e.getSource() == rightPanel.getDefendButton()){
            if(!combatController.hasDefended()) {
                combatController.entityDefense(combatController.getEntityPlaying());
                combatController.setHasSkipped(true);
            }
        }

        // Skills
        else if (e.getSource() == rightPanel.getSkillButton()){// Get skills names in backend

            ArrayList<String> testNames = new ArrayList<>(); // TO GET RID OF, CALL THE BACKEND
            testNames.add("Technique 1"); // TO GET RID OF, CALL THE BACKEND
            testNames.add("Technique 2"); // TO GET RID OF, CALL THE BACKEND

            // Show the skills on the interface
            showSkills(testNames);
        }

        // Items
        else if (e.getSource() == rightPanel.getItemButton()){
            // Get inventory in backend

            ArrayList<Item> testItems = new ArrayList<>();
            PotionItem healingPotion = new PotionItem(
                "Potion de soin basique", 
                "Potion soignant peu de HP", 
                new ImageIcon("src/main/java/com/quete_des_3_heros/ressources/items/potions/healing_potion.png").getImage(), 
                new HealingPotion(new ConcretePotion(), 10)
                );
            PotionItem manaPotion = new PotionItem(
                "Potion de mana basique", 
                "Potion soignant peu de MP", 
                new ImageIcon("src/main/java/com/quete_des_3_heros/ressources/items/potions/mana_potion.png").getImage(),
                new ManaPotion(new ConcretePotion(), 10)
                );
            testItems.add(healingPotion);
            testItems.add(manaPotion);

            // Show items on UI
            showInventory(testItems);
        }

        // Rewind
        else if (e.getSource() == rightPanel.getRewind_button()){
            System.out.println("Rewind");
        }


        // Skill/Inventory sub-buttons
        else if (rightPanel.getAlternativeButtons().contains(e.getSource())) {
            int index = rightPanel.getAlternativeButtons().indexOf(e.getSource());

            if (index == rightPanel.getAlternativeButtons().size() - 1) rightPanel.actionButtonsToPanel(); // Back
            else {
                if (rightPanel.getAlternativeButtons().get(0) instanceof InventoryButton) {
                    // Inventory button
                    // DO SOMETHING WITH NAME
                    System.out.println(((InventoryButton)(rightPanel.getAlternativeButtons().get(index))).getItemName());
                }
                else {
                    // Skill button
                    // DO SOMETHING WITH SKILL NAME (TEXT OF BUTTON)
                    System.out.println(rightPanel.getAlternativeButtons().get(index).getText());
                }
            }
        }
    }


    // Mouse listener ------------------------------------------------------------------------------

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / Constants.SPRITE_SIZE; // x in pixel -> x in squares
        int y = e.getY() / Constants.SPRITE_SIZE;

        if(getBoard().getStep() == 1){
            Entity entity = combatController.getEntityPlaying();
            for(int[] i : getBoard().getPossibleMoves()){
                if(i[0] == x && i[1] == y){
                    combatController.moveOnPathEntity(entity, x , y);
                    if(combatController.hasAttacked()) combatController.setHasSkipped(true);
                    break;
                }
            }
            
        } else if(getBoard().getStep() == 2){
            Entity entity = combatController.getEntityPlaying();
            for(int[] i : getBoard().getPossibleMoves()){
                if(i[0] == x && i[1] == y) {
                    if (combatController.entityAttackOnTarget(entity, x, y)){
                        combatController.setHasSkipped(true);
                    }
                    else {
                        if(!combatController.hasMoved()){
                            combatController.showEntityMovements(entity);
                        } else {
                            getBoard().setStep(0);
                        }
                        combatController.setIsAttacking(false);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // DO NOTHING
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // DO NOTHING
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // DO NOTHING
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // DO NOTHING
    }
}
