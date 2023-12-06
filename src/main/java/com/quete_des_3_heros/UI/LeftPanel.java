package main.java.com.quete_des_3_heros.UI;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.java.com.quete_des_3_heros.UI.components.Profile;

public class LeftPanel extends JPanel{
    public LeftPanel(){
        Image sprite;
        Profile profile;
        try {
            sprite = ImageIO.read(new File("src/main/java/com/quete_des_3_heros/ressources/warrior.png"));
            profile = new Profile("Guerrier", 15, 3, sprite);
            add(profile);
        } catch (IOException e) {
            System.out.println("Erreur dans image profil");
            System.exit(0);
        };
    }
}
