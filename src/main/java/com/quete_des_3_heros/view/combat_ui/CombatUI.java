package main.java.com.quete_des_3_heros.view.combat_ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

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
    LeftPanel leftPanel;
    RightPanel rightPanel;

    ArrayList<Hero> heroes = new ArrayList<>();
    ArrayList<Monster> monsters = new ArrayList<>();
    CombatController combatController = new CombatController(this);

    private Warrior warrior;
    private Mage mage;
    private Thief thief;
    private Goblin goblin;
    private Skeleton skeleton;
    private Dragon dragon;


    

    public CombatUI(){
        setBackground(Color.blue);
        setFocusable(true);

        // Initialise different panels
        setBoard(new Board(16, 16));
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
        getBoard().setBounds(280, 0, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
        rightPanel.setBounds(280 + Constants.BOARD_SIZE, 0, Constants.RIGHTPANEL_WIDTH, Constants.WINDOW_HEIGHT);

        // Add the panels to the UI
        add(leftPanel);
        add(getBoard());
        add(rightPanel);
        setVisible(true);

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

        combatController.setPriorityList(heroes, monsters);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Here, 'g' is the Graphics object that you can use for custom painting
        // Perform custom drawing/painting using 'g' here
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
