package main.java.com.quete_des_3_heros.view.endscreens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.quete_des_3_heros.view.components.GameButton;

public class WinningScreen extends JPanel implements ActionListener {
    private JLabel title;
    private GameButton leave;

    public WinningScreen() {
        setFocusable(true);

        setBackground(Color.white);

        // Title
        title = new JLabel("Vous avez gagné !");
        title.setFont(new Font("Serif", Font.BOLD, 96));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.GREEN);

        // Buttons
        leave = new GameButton("Quitter");
        leave.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));   // Layout for vertical buttons

        // Add every element to the panel
        add(Box.createRigidArea(new Dimension(0, 100))); // Space between buttons
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100))); // Space between buttons
        add(leave);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == leave){ // Leave game button
            System.exit(0);
        }
    }
}
