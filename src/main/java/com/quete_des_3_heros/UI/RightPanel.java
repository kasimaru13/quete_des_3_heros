package main.java.com.quete_des_3_heros.UI;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.java.com.quete_des_3_heros.UI.components.GameButton;

public class RightPanel extends JPanel{
    private JPanel buttonPanel;
    private GameButton attack;
    private GameButton defend;
    private GameButton skill;
    private GameButton item;

    public RightPanel(){
        // Whole panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, Constants.WINDOW_HEIGHT/4)));

        // Container of the buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));   // Layout for vertical buttons

        // Creation of the buttons
        attack = new GameButton("Attaquer");
        defend = new GameButton("Défense");
        skill = new GameButton("Technique");
        item = new GameButton("Objet");

        // Adding buttons to the container
        buttonPanel.add(attack);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Space between buttons
        buttonPanel.add(defend);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Space between buttons
        buttonPanel.add(skill);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Space between buttons
        buttonPanel.add(item);

        add(buttonPanel);
    }

    public GameButton getAttackButton() {
        return attack;
    }

    public GameButton getDefendButton() {
        return defend;
    }

    public GameButton getSkillButton() {
        return skill;
    }

    public GameButton getItemButton() {
        return item;
    }

    
}
