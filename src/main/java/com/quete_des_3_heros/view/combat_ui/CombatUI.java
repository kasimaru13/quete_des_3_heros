package main.java.com.quete_des_3_heros.view.combat_ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import main.java.com.quete_des_3_heros.view.Constants;


/**
 * Panel for the combat phase. Contains two sides panels displaying information for the player, and in the center of the
 * panel the board.
 */
public class CombatUI extends JPanel implements ActionListener {
    Board board;
    LeftPanel leftPanel;
    RightPanel rightPanel;
    

    public CombatUI(){
        setBackground(Color.blue);
        setFocusable(true);
        
        // Initialise different panels
        board = new Board(16, 16);
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
}
