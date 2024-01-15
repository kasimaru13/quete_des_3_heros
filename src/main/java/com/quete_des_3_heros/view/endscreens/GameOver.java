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

import main.java.com.quete_des_3_heros.view.UI;
import main.java.com.quete_des_3_heros.view.components.GameButton;

public class GameOver extends JPanel implements ActionListener {
    private JLabel title;
    private GameButton restart, leave;
    
    public GameOver() {
        setFocusable(true);

        setBackground(Color.BLACK);

        // Title
        title = new JLabel("Vous avez perdu.");
        title.setFont(new Font("Serif", Font.BOLD, 96));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.RED);

        // Buttons
        restart = new GameButton("Menu Principal");
        leave = new GameButton("Quitter");
        restart.addActionListener(this);
        leave.addActionListener(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));   // Layout for vertical buttons

        // Add every element to the panel
        add(Box.createRigidArea(new Dimension(0, 100))); // Space between buttons
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100))); // Space between buttons
        add(restart);
        add(Box.createRigidArea(new Dimension(0, 100))); // Space between buttons
        add(leave);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restart){ // Start button
            UI.getInstance().nextStep(0, "Title");
        }
        else if(e.getSource() == leave){ // Leave game button
            System.exit(0);
        }
    }
}
