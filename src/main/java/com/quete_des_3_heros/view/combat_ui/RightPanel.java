package main.java.com.quete_des_3_heros.view.combat_ui;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.com.quete_des_3_heros.element.Hero;
import main.java.com.quete_des_3_heros.view.Constants;
import main.java.com.quete_des_3_heros.view.components.GameButton;
import main.java.com.quete_des_3_heros.view.components.Profile;


/**
 * Panel on the right of the combat UI. Contains the player's characters' profiles, actions buttons and rewind move button.
 */
public class RightPanel extends JPanel{
    private JPanel profilesPanel; // Panel containing profiles of heroes
    private JPanel buttonPanel; // Panel containing actions buttons

    // Actions buttons :
    private GameButton attack;
    private GameButton defend;
    private GameButton skill;
    private GameButton item;

    private JButton rewind_button;

    private Profile[] profiles;

    public RightPanel(ArrayList<Hero> heroes){
        // Whole panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(0, 10)));

        // Profiles Panel
        profilesPanel = new JPanel();
        profilesPanel.setLayout(new BoxLayout(profilesPanel, BoxLayout.Y_AXIS));
        profiles = new Profile[heroes.size()];
        int i = 0;
        for (Hero hero : heroes) {
            Profile tmp = new Profile(hero.getName(), hero.getHealth(), hero.getMana(), hero.getSprite());
            profiles[i] = tmp;
            profilesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            profilesPanel.add(tmp);
            i++;
        }
        add(profilesPanel);
        add(Box.createVerticalGlue());

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
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);

        buttonPanel.setMinimumSize(new Dimension(Constants.RIGHTPANEL_WIDTH, buttonPanel.getHeight()));

        add(buttonPanel);
        add(Box.createVerticalGlue());

        // Rewind Move button
        rewind_button = new GameButton("<html><p>Annuler de dernier déplacement</p></html>");
        rewind_button.setPreferredSize(new Dimension(Constants.RIGHTPANEL_WIDTH - 30, 40));
        rewind_button.setFont(new JLabel().getFont());
        rewind_button.setIcon(new ImageIcon(new ImageIcon("src/main/java/com/quete_des_3_heros/ressources/icons/white_refresh.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        rewind_button.setIconTextGap(20);
        rewind_button.setBorder(new EmptyBorder(10, 10, 10, 10));

        add(rewind_button);
        add(Box.createRigidArea(new Dimension(0,15)));
    } 

    public void updateProfiles(ArrayList<Hero> heroes) {
        Hero hero;
        for (int i = 0; i < profiles.length; i++) {
            hero = heroes.get(i);
            profiles[i].updateProfile(hero.getHealth(), hero.getMana());
        }
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

    public JButton getRewind_button() {
        return rewind_button;
    }
}
