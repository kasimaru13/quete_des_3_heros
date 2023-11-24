package main.java.com.quete_des_3_heros.UI;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import main.java.com.quete_des_3_heros.elements.Element;

public class Board extends JPanel{
    private Element[] board;
    private BufferedImage backgroundImage;
    private BufferedImage warrior;

    public Board(){
        board = new Element[256];

        try {
            backgroundImage = ImageIO.read(new File("src/main/java/com/quete_des_3_heros/ressources/grass.png")).getSubimage(0, 0, 720, 720);
            warrior = ImageIO.read(new File("src/main/java/com/quete_des_3_heros/ressources/warrior.png"));
        } catch (IOException e) {
            System.out.println("Erreur dans la lecture de l'image d'arrière plan");
            System.exit(0);
        }
        setBackground(Color.red);
    }

    public Element[] getBoard() {
        return board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(warrior, 672, 0, null);
    }
}
