package main.java.com.quete_des_3_heros.view.combat_ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import main.java.com.quete_des_3_heros.controller.CombatController;
import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.element.Monster;
import main.java.com.quete_des_3_heros.element.heros.Mage;
import main.java.com.quete_des_3_heros.element.heros.Thief;
import main.java.com.quete_des_3_heros.element.heros.Warrior;
import main.java.com.quete_des_3_heros.element.monsters.Dragon;
import main.java.com.quete_des_3_heros.element.monsters.Goblin;
import main.java.com.quete_des_3_heros.element.monsters.Skeleton;
import main.java.com.quete_des_3_heros.view.Constants;


/**
 * Panel for the combat phase. Contains two sides panels displaying information for the player, and in the center of the
 * panel the board.
 */
public class CombatUI extends JPanel implements ActionListener {
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

    public CombatUI(){
        // Add the entities in their own list and add all the entities in the priority list
        initListEntities();

        // Initiate CombatUI preferences
        initCombatUI(combatController.getEntitiesPriorityList());

        // Add Entity on the grid
        combatController.addEntity(warrior, 8, 2);
        combatController.addEntity(mage, 9, 2);
        combatController.addEntity(thief, 10, 2);
        combatController.addEntity(goblin, 8, 13);
        combatController.addEntity(skeleton, 9, 13);
        combatController.addEntity(dragon, 10, 13);

        updateCombatUI();
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
        rightPanel = new RightPanel();

        // ActionListner on RightPanel's Buttons
        rightPanel.getAttackButton().addActionListener(this);
        rightPanel.getDefendButton().addActionListener(this);
        rightPanel.getSkillButton().addActionListener(this);
        rightPanel.getItemButton().addActionListener(this);

        // Set size of the panels
        setLayout(null);
        leftPanel.setBounds(0, 0, Constants.LEFTPANEL_WIDTH, Constants.WINDOW_HEIGHT);
        board.setBounds(280, 0, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
        rightPanel.setBounds(280 + Constants.BOARD_SIZE, 0, Constants.RIGHTPANEL_WIDTH, Constants.WINDOW_HEIGHT);

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
        if (e.getSource() == rightPanel.getDefendButton()){
            System.out.println("Defend");
        }
        if (e.getSource() == rightPanel.getSkillButton()){
            System.out.println("Skill");
        }
        if (e.getSource() == rightPanel.getItemButton()){
            System.out.println("Item");
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
