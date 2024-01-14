package main.java.com.quete_des_3_heros.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.quete_des_3_heros.element.Entity;
import main.java.com.quete_des_3_heros.view.Constants;


/**
 * Component to display that describes a character (or a monster) for the player. It contains its sprite,, its name,
 * its health points and its mana points.
 */
public class Profile extends JPanel {
    private JLabel description; // The text to display, with the name, hp and mp
    private JLabel sprite; // Sprite to display

    // Characteristics of the character:
    private String name;
    private String total_hp;
    private String total_mp;

    public Profile(Entity entity) {
        setBackground(new Color(215,215,215));
        setMinimumSize(new Dimension(Constants.LEFTPANEL_WIDTH * 65/100, 75));
        setPreferredSize(new Dimension(Constants.LEFTPANEL_WIDTH * 65/100, 75));
        setMaximumSize(new Dimension(Constants.LEFTPANEL_WIDTH * 65/100, 75));

        // Layout
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentY(CENTER_ALIGNMENT);

        total_hp = ((Integer)entity.getMaxHealth()).toString();
        total_mp = ((Integer)entity.getMaxMana()).toString();
        name = entity.getName();

        // Setting labels
        sprite = new JLabel(new ImageIcon(entity.getSprite()));
        sprite.setBorder(BorderFactory.createLineBorder(Color.black));
        description = new JLabel(
            "<html>" + name + " :<br/>" +
            "&nbsp;&nbsp;HP: " + entity.getHealth() + "/" + total_hp + "<br/>" +
            "&nbsp;&nbsp;MP: " + entity.getMana() + "/" + total_mp
        );
        

        // Adding labels to panel
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(sprite);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(description);
    }

    /**
     * Changes the characteristics of the profile
     * @param hp New HP value
     * @param mp New MP value
     */
    public void updateProfile(int hp, int mp){
        description.setText(
            "<html>" + name + " :<br/>" +
            "&nbsp;&nbsp;HP: " + hp + "/" + total_hp + "<br/>" +
            "&nbsp;&nbsp;MP: " + mp + "/" + total_mp + "</html>"
        );
    }

    /**
     * Changes the color of the sprite's label's border. Allows to show whose turn it is, or if a character is dead
     * to the player.
     * @param border_color Color of the new border
     */
    public void changeBorderColor(Color border_color){
        sprite.setBorder(BorderFactory.createLineBorder(border_color));
    }
}
