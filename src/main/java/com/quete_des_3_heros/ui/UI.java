package main.java.com.quete_des_3_heros.ui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import main.java.com.quete_des_3_heros.ui.combat_ui.CombatUI;
import main.java.com.quete_des_3_heros.ui.dialogues.Dialogue;
import main.java.com.quete_des_3_heros.ui.title_screen.TitleScreen;

public class UI extends JFrame {
    static UI instance;

    private UI(){
        TitleScreen titleScreen = new TitleScreen();
        add(titleScreen);
        
        setTitle("Gnir's Needle");

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

    public void nextStep(int next_phase_number, String type_of_phase){
        getContentPane().removeAll();

        if (type_of_phase == "Dialogue"){
            add(new Dialogue(next_phase_number));
        }
        else if (type_of_phase == "Combat"){
            add(new CombatUI());
        }
        else{
            System.err.println("Erreur dans le changement de phase");
            System.exit(0);
        }

        revalidate();

        repaint();
    }
}
