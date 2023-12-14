package main.java.com.quete_des_3_heros.ui.title_screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.com.quete_des_3_heros.ui.Constants;
import main.java.com.quete_des_3_heros.ui.UI;
import main.java.com.quete_des_3_heros.ui.components.GameButton;

public class TitleScreen extends JPanel implements ActionListener {
    private JLabel title;
    private Image backgroundImage;
    private GameButton start, leave;

    public TitleScreen(){
        // Loading images
        try {
            backgroundImage = ImageIO.read(new File("src/main/java/com/quete_des_3_heros/ressources/backgrounds/landscape.jpg"));
            // Scale the image so it fills the whole window
            backgroundImage = backgroundImage.getScaledInstance(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            System.out.println("Erreur dans la lecture de l'image de fond de l'écran titre");
            System.exit(0);
        }

        setFocusable(true);

        // Title
        title = new JLabel("La Quête des 3 Héros");
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.WHITE);

        // Buttons
        start = new GameButton("Commencer");
        leave = new GameButton("Quitter");
        start.addActionListener(this);
        leave.addActionListener(this);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));   // Layout for vertical buttons

        add(Box.createRigidArea(new Dimension(0, 100))); // Space between buttons
        add(title);
        add(Box.createRigidArea(new Dimension(0, 100))); // Space between buttons
        add(start);
        add(Box.createRigidArea(new Dimension(0, 100))); // Space between buttons
        add(leave);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start){ // Start button
            UI.getInstance().nextStep(1, "Dialogue");
        }
        else if(e.getSource() == leave){ // Leave game button
            System.exit(0);
        }
    }
}
