package main.java.com.quete_des_3_heros.UI.components;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

// public class GameButton extends JPanel {
//     public GameButton(String text){
//         super();

//         JButton button = new JButton(text);
//         button.setFocusable(false);
//         button.setBackground(Color.gray);

//         add(button);
//         setPreferredSize(new Dimension(800,500));
//     }
// }

public class GameButton extends JButton {
    public GameButton(String text){
        super(text);

        setFocusable(false);
        setSize(new Dimension(500, 80));
        setFont(new Font("Arial", Font.PLAIN, 18));
        setBackground(Color.gray);
        setForeground(Color.WHITE);
        setMaximumSize(new Dimension(250, 60));
        setAlignmentX(CENTER_ALIGNMENT);
    }
}
