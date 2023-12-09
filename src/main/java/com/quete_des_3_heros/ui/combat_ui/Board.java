package main.java.com.quete_des_3_heros.ui.combat_ui;


import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import main.java.com.quete_des_3_heros.elements.Element;
import main.java.com.quete_des_3_heros.ui.Constants;

public class Board extends JPanel{
    private Element[] board;
    private Image backgroundImage;
    private Image warrior;

    public Board(){
        board = new Element[256];

        try {
            backgroundImage = ImageIO.read(new File("src/main/java/com/quete_des_3_heros/ressources/grass.png")).getSubimage(0, 0, Constants.BOARD_SIZE, Constants.BOARD_SIZE);
            warrior = ImageIO.read(new File("src/main/java/com/quete_des_3_heros/ressources/warrior.png"));
        } catch (IOException e) {
            System.out.println("Erreur dans la lecture des sprites du jeu");
            System.exit(0);
        }
    }

    public Element[] getBoard() {
        return board;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(backgroundImage, 0, 0, null);

        // Draw grid
        if (Constants.GRID != 0){
            g.setColor(new Color(0, 0, 0, 50));
            for (int i = Constants.SPRITE_SIZE; i < Constants.BOARD_SIZE; i += Constants.SPRITE_SIZE + 1){
                g.drawLine(0, i, Constants.BOARD_SIZE, i);
                g.drawLine(i, 0, i, Constants.BOARD_SIZE);
            }
        }

        g.drawImage(warrior, 672+14, 0, null);
    }
}
