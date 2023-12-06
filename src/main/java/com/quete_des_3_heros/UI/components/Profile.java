package main.java.com.quete_des_3_heros.UI.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.quete_des_3_heros.UI.Constants;

public class Profile extends JPanel {
    private JLabel description;
    private JLabel sprite;
    private String name;
    private String total_hp;
    private String total_mp;
    
    
    public Profile(String character_name, int hp, int mp, Image character_sprite){
        setBackground(new Color(215,215,215));
        setPreferredSize(new Dimension(Constants.LEFTPANEL_WIDTH * 65/100, 75));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentY(CENTER_ALIGNMENT);

        total_hp = String.valueOf(hp);
        total_mp = String.valueOf(mp);
        name = character_name;

        // Setting labels
        sprite = new JLabel(new ImageIcon(character_sprite));
        sprite.setBorder(BorderFactory.createLineBorder(Color.black));
        description = new JLabel(
            "<html>" + name + " :<br/>" +
            "&nbsp;&nbsp;HP: " + total_hp + "/" + total_hp + "<br/>" +
            "&nbsp;&nbsp;MP: " + total_mp + "/" + total_mp
        );
        

        // Adding labels to panel
        add(Box.createRigidArea(new Dimension(20, 0)));
        add(sprite);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(description);
    }

    public void updateProfile(int hp, int mp){
        description.setText(
            "<html>" + name + " :<br/>" +
            "&nbsp;&nbsp;HP: " + hp + "/" + total_hp + "<br/>" +
            "&nbsp;&nbsp;MP: " + mp + "/" + total_mp
        );
    }

    public void changeBorderColor(Color border_color){
        sprite.setBorder(BorderFactory.createLineBorder(border_color));
    }
}
