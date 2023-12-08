package main.java.com.quete_des_3_heros.UI;

import java.awt.Dimension;
import javax.swing.JFrame;

import main.java.com.quete_des_3_heros.UI.combat_ui.CombatUI;
import main.java.com.quete_des_3_heros.UI.title_screen.TitleScreen;

public class UI extends JFrame {
    static UI instance;

    private UI(){
        TitleScreen titleScreen = new TitleScreen();
        add(titleScreen);
        
        setTitle("Gnir's Needle");
        
        startGame(); // A ENLEVER, PERMET DE SKIP L'ECRAN D'ACCUEIL

        // Pour donner une taille constante à la fenêtre
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        pack();

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static UI getInstance(){
        if (instance == null){
            instance = new UI();
        }
        return instance;
    }

    public void startGame(){
        getContentPane().removeAll();

        add(new CombatUI());

        revalidate();

        repaint();
    }
}
