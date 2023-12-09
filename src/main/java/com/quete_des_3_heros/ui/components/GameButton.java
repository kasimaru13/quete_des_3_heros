package main.java.com.quete_des_3_heros.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class GameButton extends JButton {
    public GameButton(String text){
        super(text);

        setFocusable(false);
        setSize(new Dimension(500, 80));
        setFont(new Font("Arial", Font.PLAIN, 18));
        setBackground(Color.gray);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(250, 60));
        setMaximumSize(new Dimension(250, 60));
        setBorder(new LineBorder(Color.lightGray));
        setAlignmentX(CENTER_ALIGNMENT);
    }
}
