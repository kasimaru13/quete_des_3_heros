package main.java.com.quete_des_3_heros.view.combat_ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import main.java.com.quete_des_3_heros.controller.CombatController;
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

    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Monster> monsters = new ArrayList<>();
    private CombatController combatController = new CombatController(this);

    private Warrior warrior;
    private Mage mage;
    private Thief thief;
    private Goblin goblin;
    private Skeleton skeleton;
    private Dragon dragon;

    public CombatUI(){
        // Heroes
        warrior = new Warrior();
        mage = new Mage();
        thief = new Thief();
        // ArrayList of Heroes
        heroes.add(warrior);
        heroes.add(mage);
        heroes.add(thief);

        // Monsters
        goblin = new Goblin();
        skeleton = new Skeleton();
        dragon = new Dragon();
        // ArrayList of Monsters
        monsters.add(goblin);
        monsters.add(skeleton);
        monsters.add(dragon);

        combatController.setEntities(heroes, monsters);

        // Initialise CombatUI preferences
        initCombatUI();

        combatController.addEntity(warrior, 8, 2);
        combatController.addEntity(mage, 9, 2);
        combatController.addEntity(thief, 10, 2);
        combatController.addEntity(goblin, 8, 13);
        combatController.addEntity(skeleton, 9, 13);
        combatController.addEntity(dragon, 10, 13);

        updateCombatUI();

        combatController.moveEntity(dragon, 12, 13);
    }

    private void initCombatUI(){
        setBackground(Color.blue);
        setFocusable(true);

        // Initialise different panels
        board = new Board(16, 16, combatController.getEntities());
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
