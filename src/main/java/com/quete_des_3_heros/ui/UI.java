package main.java.com.quete_des_3_heros.ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import main.java.com.quete_des_3_heros.ui.combat_ui.CombatUI;
import main.java.com.quete_des_3_heros.ui.dialogues.Dialogue;
import main.java.com.quete_des_3_heros.ui.title_screen.TitleScreen;

/**
 * Singleton frame containing every UI logic.
 */
public class UI extends JFrame {
    static UI instance;

    private UI(){
        TitleScreen titleScreen = new TitleScreen();
        add(titleScreen);
        setTitle("La quête des 3 héros");

        // Constant size of the window
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        pack();

        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Returns the instance of the singleton UI, or create one if none exist.
     */
    public static UI getInstance(){
        if (instance == null){
            instance = new UI();
        }
        return instance;
    }

    /**
     * Proceed to the next step of the game
     * @param next_phase_number Number of the next phase (0 is the titlescreen)
     * @param type_of_phase Type of the phase (Dialogue or Combat)
     */
    public void nextStep(int next_phase_number, String type_of_phase){
        getContentPane().removeAll(); // Clean the frame

        // Creates the next phase and adds it to the frame
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

        // Repaint the frame
        revalidate();
        repaint();
    }
}
