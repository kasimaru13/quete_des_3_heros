package main.java.com.quete_des_3_heros.UI;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen extends JPanel {
    JLabel title;

    public TitleScreen(){
        setFocusable(true);
        title = new JLabel("La Quête des 3 Héros");
        title.setFont(new Font("Serif", Font.PLAIN, 45));
        add(title);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        
    }
}
