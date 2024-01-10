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
import main.java.com.quete_des_3_heros.element.heros.Mage;
import main.java.com.quete_des_3_heros.element.heros.Thief;
import main.java.com.quete_des_3_heros.element.heros.Warrior;
import main.java.com.quete_des_3_heros.element.monsters.Dragon;
import main.java.com.quete_des_3_heros.element.monsters.Goblin;
import main.java.com.quete_des_3_heros.element.monsters.Skeleton;
import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.components.Profile;


/**
 * Panel for the combat phase. Contains two sides panels displaying information for the player, and in the center of the
 * panel the board.
 */
public class CombatUI extends JPanel implements ActionListener, MouseListener {
    private Board board;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;


    private CombatController combatController = new CombatController(this);

    private Warrior warrior = new Warrior();
    private Mage mage = new Mage();
    private Thief thief = new Thief();
    private Goblin goblin = new Goblin();
    private Skeleton skeleton = new Skeleton();
    private Dragon dragon = new Dragon();

    private List<Profile> profile_queue;

    public CombatUI(){
        // Add the entities in their own list and add all the entities in the priority list
        initListEntities();

        // Initiate CombatUI preferences
        initCombatUI(combatController.getEntitiesPriorityList());

        // Add Entity on the grid
        addEntitiesToGrid();

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
                // Mettez à jour l'UI ou effectuez d'autres opérations après la fin du combat ici
            }
        };

        swingWorker.execute();
    }

    /**
     * Initiate Combat Interface
     */
    private void initCombatUI(ArrayList<Entity> entities){
        setBackground(Color.blue);
        setFocusable(true);

        // Initialise different panels
        board = new Board(16, 16, entities);
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
        updatePriorityQueue(entities);

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
        combatController.getHeroes().add(warrior);
        combatController.getHeroes().add(mage);
        combatController.getHeroes().add(thief);

        // ArrayList of Monsters
        combatController.getMonsters().add(goblin);
        combatController.getMonsters().add(skeleton);
        combatController.getMonsters().add(dragon);

        // ArrayList of all Entities for the Priority Order
        combatController.setEntitiesPriorityList();
    }

    private void addEntitiesToGrid() {
        combatController.addEntity(warrior, 7, 2);
        combatController.addEntity(mage, 8, 2);
        combatController.addEntity(thief, 9, 2);
        combatController.addEntity(goblin, 7, 13);
        combatController.addEntity(skeleton, 8, 13);
        combatController.addEntity(dragon, 9, 13);
    }

    /**
     * Update Combat Textual Interface for the moment
     */
    private void updateCombatUI() {
        for (int i = 0; i < board.getBoardLength(); i++) {
            for (int j = 0; j < board.getBoardWidth(); j++) {
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


    @Override
    public void actionPerformed(ActionEvent e) {
        // Actions when pressing right panel's buttons
        if (e.getSource() == rightPanel.getAttackButton()){
            System.out.println("Attack");
        }
        else if (e.getSource() == rightPanel.getDefendButton()){
            System.out.println("Defend");
        }
        else if (e.getSource() == rightPanel.getSkillButton()){
            System.out.println("Skill");

            // Get skills names in backend

            ArrayList<String> testNames = new ArrayList<>(); // TO GET RID OF, CALL THE BACKEND
            testNames.add("Technique 1"); // TO GET RID OF, CALL THE BACKEND
            testNames.add("Technique 2"); // TO GET RID OF, CALL THE BACKEND

            // Show the skills on the interface
            showSkills(testNames);
        }
        else if (e.getSource() == rightPanel.getItemButton()){
            System.out.println("Item");
        }
        else if (e.getSource() == rightPanel.getRewind_button()){
            System.out.println("Rewind");
        }

        // Skill/Inventory buttons
        else if (rightPanel.getAlternativeButtons().contains(e.getSource())) {
            int index = rightPanel.getAlternativeButtons().indexOf(e.getSource());
            if (index == rightPanel.getAlternativeButtons().size() - 1) rightPanel.actionButtonsToPanel(); // Return
            else System.out.println(rightPanel.getAlternativeButtons().get(index).getText());// Do something
        }
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

    public void updatePriorityQueue(ArrayList<Entity> entities){
        if (!entities.isEmpty() && profile_queue != null && leftPanel != null){
            for (Entity entity : entities){
                profile_queue.add(new Profile(entity.getName(), entity.getHealth(), entity.getMana(), entity.getSprite()));
            }

            leftPanel.setPriority_queue(profile_queue);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() % Constants.SPRITE_SIZE; // x in pixel -> x in squares
        int y = e.getY() / Constants.SPRITE_SIZE;

        // Do something with it
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
